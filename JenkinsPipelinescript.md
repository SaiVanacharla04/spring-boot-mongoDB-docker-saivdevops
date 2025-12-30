pipeline {
    // Use any available Jenkins agent to run the pipeline
    agent any

    // Define the tools needed for this pipeline
    tools {
        // Use Maven version configured in Jenkins Global Tool Configuration
        maven 'maven3.9.9'
    }

    // Define environment variables used throughout the pipeline
    environment {
        NEXUS_CONTAINER = 'nexus'           // Name or ID of the running Nexus Docker container
        BACKUP_DIR = '/tmp/nexus-backup'    // Directory on the Jenkins agent to store backups
        DATE = "${new Date().format('yyyyMMdd-HHmmss')}"  // Timestamp to create unique backup names
    }

    stages {
        // Stage 1: Checkout the source code from GitHub
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/SaiVanacharla04/spring-boot-mongoDB-docker-saivdevops.git'
            }
        }

        // Stage 2: Build the project using Maven
        stage('Build') {
            steps {
                // Compile and package the project; skip running tests to speed up the build
                sh 'mvn clean package -DskipTests'
            }
        }

        // Stage 3: Run SonarQube analysis
        stage('SonarQube') {
            steps {
                // Inject SonarQube server environment variables
                withSonarQubeEnv('SonarQube') {
                    sh """
                        # Run Maven SonarQube plugin to analyze code quality
                        mvn verify \
                        org.sonarsource.scanner.maven:sonar-maven-plugin:sonar \
                        -Dsonar.projectKey=spring-boot-mongo \
                        -Dsonar.projectName="Spring Boot Mongo Project"
                    """
                }
            }
        }

        // Stage 4: Build Docker image
        stage('Build & Tag Docker Image') {
            steps {
                // Authenticate with Docker registry using Jenkins credentials
                withDockerRegistry(
                    credentialsId: 'docker',                // ID of Docker Hub credentials in Jenkins
                    url: 'https://index.docker.io/v1/'     // Docker Hub registry URL
                ) {
                    // Build Docker image and tag it
                    sh 'docker build -t saivanacharla/mongospring:latest .'
                }
            }
        }

        // Stage 5: Push Docker image to registry
        stage('Push Docker Image') {
            steps {
                withDockerRegistry(
                    credentialsId: 'docker',
                    url: 'https://index.docker.io/v1/'
                ) {
                    // Push the previously built Docker image to Docker Hub
                    sh 'docker push saivanacharla/mongospring:latest'
                }
            }
        }

        // Stage 6: Backup Nexus container
        stage('Nexus Backup') {
            steps {
                script {
                    sh """
                        # Create backup directory if it doesn't exist
                        mkdir -p ${BACKUP_DIR}

                        echo "Starting Nexus backup from container ${NEXUS_CONTAINER}..."

                        # Copy Nexus data folder from container to Jenkins agent
                        docker cp ${NEXUS_CONTAINER}:/nexus-data ${BACKUP_DIR}/nexus-data-${DATE}

                        # Compress the copied data to a tar.gz archive
                        tar -czf ${BACKUP_DIR}/nexus-backup-${DATE}.tar.gz -C ${BACKUP_DIR} nexus-data-${DATE}

                        echo "Nexus backup completed: ${BACKUP_DIR}/nexus-backup-${DATE}.tar.gz"
                    """
                }
            }
        }
    }

    // Post-build actions
    post {
        always {
            // Archive the Nexus backup artifact in Jenkins UI for easy download
            archiveArtifacts artifacts: '/tmp/nexus-backup/*.tar.gz', allowEmptyArchive: true
            echo 'Pipeline finished, Nexus backup archived.'
        }
    }
}
