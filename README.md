# spring-boot-mongoDB-docker-saivdevops

# 🚀 Spring Boot + MongoDB + Docker + Kubernetes

**Enterprise DevOps Reference Project**

**Project Name:** `spring-boot-mongo-docker-saivdevops`
**Version:** `1.0.1`
**Author:** Sai Swaroop Vanacharla
**Training:** DevOps Online Training – Hyderabad
🌐 [http://saivdevops.com/](http://saivdevops.com/)
**Email**: saivswaroop001@gmail.com

---

## 📌 Overview

This project is a **production-style, enterprise-grade reference application** demonstrating how to build, containerize, and deploy a **Spring Boot application backed by MongoDB** using **Docker and Kubernetes**.

It follows **real corporate DevOps standards**, including:

* Clean layered architecture
* RESTful APIs
* UI integration
* Secure configuration management
* Containerization
* Kubernetes orchestration

---

## 🧱 Technology Stack

| Layer            | Technology                    |
| ---------------- | ----------------------------- |
| Backend          | Spring Boot 2.1.5             |
| Database         | MongoDB                       |
| UI               | Thymeleaf, Bootstrap, jQuery  |
| Build Tool       | Maven                         |
| Containerization | Docker                        |
| Orchestration    | Kubernetes                    |
| Logging          | Logback + Spring Cloud Sleuth |

---

## 🏗️ High-Level Architecture

### 🔹 Application Architecture

```
+-------------+
|   Browser   |
| (HTML / UI) |
+------+------+
       |
       | HTTP (Form Submit / REST)
       v
+------------------------+
| Spring Boot Application|
|------------------------|
| - MVC Controller       |
| - REST APIs            |
| - Mongo Repository     |
+-----------+------------+
            |
            | Spring Data
            v
+------------------------+
|        MongoDB         |
|  (users collection)   |
+------------------------+
```

---

### 🔹 Kubernetes Architecture

```
User
 |
 | NodePort
 v
+----------------------+
| Spring App Service   |
+----------+-----------+
           |
           v
+------------------------------+
| Spring Boot Pods (Replicas)  |
| - Pod 1                      |
| - Pod 2                      |
| - Pod 3                      |
| - Pod 4                      |
+--------------+---------------+
               |
               v
        +--------------+
        | Mongo Service|
        +------+-------+
               |
               v
        +--------------+
        | MongoDB Pod  |
        | + PVC        |
        +--------------+
```

---

## 📂 Project Structure

```
spring-boot-mongo-docker-saivdevops
│
├── src/main/java/com/ss/springmongo
│   ├── DemoApp.java
│   ├── User.java
│   ├── UserController.java
│   ├── UserResource.java
│   ├── UserRepository.java
│   └── WebConfig.java
│
├── src/main/resources
│   ├── static/
│   ├── templates/index.html
│   ├── application.yml
│   ├── bootstrap.yml
│   └── logback-spring.xml
│
├── Dockerfile
├── pom.xml
├── k8s/deploy.yaml
└── README.md
```

---

🧰 Prerequisites
Install the following:
•	Java 8+
•	Maven 3.6+
•	Docker 20+
•	Kubernetes (Minikube / EKS / AKS)
•	kubectl CLI
•	Git

🌐 Project Overview

This project is a simple CRUD application:
•	Save users with firstName, lastName, and email.
•	View all saved users on the UI or via REST API.
•	MongoDB serves as a NoSQL database.
•	Deployed on Kubernetes with persistent storage and environment separation.

**File Map**

| File                     | Purpose                                                                                                     |
| ------------------------ | ----------------------------------------------------------------------------------------------------------- |
| **DemoApp.java**         | Entry-point; boots Spring, maps `/` → `index.html`.                                                         |
| **User.java**            | Mongo document (`@Document`) holding user fields + equals/hashCode.                                         |
| **UserController.java**  | Form POST handler (`/save`) – persists new users via repository.                                            |
| **UserResource.java**    | REST controller (`/api/users`) – lists/fetches users as JSON.                                               |
| **UserRepository.java**  | Spring-Data interface; gives CRUD + paging on `users` collection.                                           |
| **WebConfig.java**       | Static-resource routing (`/css/**`, `/js/**`, `/webjars/**`).                                               |
| **index.html**           | Thymeleaf UI – bootstrap form + AJAX table showing saved users.                                             |
| **application.yml**      | Runtime config – Mongo credentials/host/port via environment variables.                                      |
| **bootstrap.yml**        | Early-boot properties (app name, default port 8080).                                                        |
| **logback-spring.xml**   | JSON console logs with trace/span IDs (Sleuth + Logstash encoder).                                          |
| **pom.xml**              | Maven coordinates & dependencies: Spring Boot 2.1.5, MongoDB, Thymeleaf, WebJars, Sleuth.                  |
| **Dockerfile**           | Multi-stage build: Maven → JAR → Alpine JDK image, exposes 8080.                                            |
| **springBootMongo.yaml** | Complete Kubernetes manifest – ConfigMap, Secret, PV, PVC, Mongo ReplicaSet, Spring Deployment + NodePort Service. |
| **k8s/deploy.yaml**      | Same as above (duplicated) – ready for `kubectl apply -f k8s/deploy.yaml`.                                  |

________________________________________
🌱 Environment Variables

| Variable          | Description                      |
| ----------------- | -------------------------------- |
| MONGO_DB_HOSTNAME | MongoDB hostname or service name |
| MONGO_DB_USERNAME | MongoDB username                 |
| MONGO_DB_PASSWORD | MongoDB password                 |

These values are injected using Kubernetes ConfigMaps and Secrets.
________________________________________
🔌 REST API Endpoints
Exposes REST APIs for external systems or UI JavaScript calls.

| Method | Endpoint          | Description            |
| ------ | ----------------- | ---------------------- |
| GET    | `/api/users`      | Fetch all users        |
| GET    | `/api/users/{id}` | Fetch user by ID       |
| POST   | `/save`           | Save user from UI form |

All APIs return JSON responses.
Usage:
•	UI uses AJAX to load users
•	APIs can be consumed by mobile apps or other services
Standards:
•	RESTful design
•	JSON responses
•	Logging for monitoring

## 🧩 Application Components Explained

### 🔹 DemoApp.java

* Entry point of the Spring Boot application
* Bootstraps Spring context
* Maps `/` to `index.html`

---

### 🔹 User.java (Domain Model)

* Represents MongoDB `users` collection
* Fields: `id`, `firstName`, `lastName`, `email`
* Serializable and production-ready

---

### 🔹 UserRepository.java

* Spring Data MongoDB repository
* Provides CRUD, pagination, and sorting
* No implementation code required

---

### 🔹 UserController.java (MVC Controller)

* Handles form submission (`/save`)
* Saves user data into MongoDB
* Redirects back to home page

---

### 🔹 UserResource.java (REST API)

* `GET /api/users` → Fetch all users
* `GET /api/users/{id}` → Fetch user by ID
* Used by AJAX and external clients

---

### 🔹 WebConfig.java

* Configures static resources
* Enables CSS, JS, images, WebJars

---

## 🎨 UI Flow (index.html)

1. User fills the form
2. Form submits to `/save`
3. Data stored in MongoDB
4. AJAX call to `/api/users`
5. User list displayed dynamically

---

## ⚙️ Configuration Management

### application.yml

* MongoDB configuration
* Uses environment variables
* Cloud & Kubernetes friendly

### bootstrap.yml

* Application name
* Early startup configuration

---

## 📊 Logging & Observability

* Structured logs
* Async logging
* Trace & Span IDs
* Production-ready log format
* Compatible with ELK / EFK stacks

---

🧑‍💻 Local Development
1.	Start MongoDB locally:
docker run --name mongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=springapp -e MONGO_INITDB_ROOT_PASSWORD=devdb@123 -d mongo
2.	Update src/main/resources/application.yml with your MongoDB hostname, username, and password.
3.	Run the application:
mvn spring-boot:run
4.	Access UI at: http://localhost:8080


## 🐳 Docker Build & Run

### 🔹 Build Docker Image

```bash
docker build -t spring-boot-mongo .
```

### 🔹 Run Container Locally

```bash
docker run -p 8080:8080 spring-boot-mongo
```

Access UI:

```
http://localhost:8080
```

---
**Kubernetes Diagram**

<img width="290" height="207" alt="image" src="https://github.com/user-attachments/assets/c8fc8fe8-0574-48a2-8870-99750b76465e" />


## ☸️ Kubernetes Deployment Steps

### 🔹 Prerequisites

* Kubernetes cluster (Minikube / EKS / AKS)
* kubectl installed
* Docker image pushed to registry

---

### 🔹 Step 1: Apply Kubernetes Resources

```bash
kubectl apply -f k8s/deploy.yaml
```

---

### 🔹 Step 2: Verify Pods

```bash
kubectl get pods
```

---

### 🔹 Step 3: Verify Services

```bash
kubectl get svc
```

---

### 🔹 Step 4: Access Application

For **Minikube**:

```bash
minikube service springapp
```

For **Cloud Kubernetes**:

```bash
http://<NodeIP>:<NodePort>
```


Layered Flow Description
1. User Interaction / UI Layer
•	The user opens the application via a browser.
•	The index.html page is served by Spring Boot.
•	User fills a registration form with first name, last name, and email.
•	On form submission, the data is sent as a POST request to /save.
2. Spring Boot Controller Layer
•	UserController receives POST requests from UI.
•	Validates user input and creates a new User object.
•	Calls UserRepository.save(user) to persist the user.
•	Redirects user back to home page after successful save.
3. Repository & Database Layer
•	UserRepository is a Spring Data MongoDB repository, providing CRUD operations.
•	MongoDB stores the user data in the users collection.
•	AJAX GET request to /api/users fetches all users dynamically.
•	UserResource returns JSON data for frontend rendering.
4. Dockerization
•	Dockerfile uses a multi-stage build:
1.	Maven container builds the JAR file.
2.	JAR copied to lightweight Alpine JDK image.
•	Exposes port 8080 for container access.
•	Container can be run locally for development/testing.
5. Kubernetes Orchestration
•	Spring Boot container deployed as Pods in a Kubernetes Deployment.
•	Multiple replicas ensure high availability and horizontal scaling.
•	NodePort Service exposes the application externally.
•	MongoDB deployed in a Pod with Persistent Volume (PVC) for data durability.
•	ClusterIP Service exposes MongoDB internally for Spring Boot Pods.
________________________________________
Key Features of Flow
•	Stateless application pods (Spring Boot) allow horizontal scaling.
•	Persistent storage ensures MongoDB data survives pod restarts.
•	AJAX-based UI dynamically updates without page reloads.
•	Structured logging with Sleuth & Logback for traceability.
•	Kubernetes Secrets manage credentials securely.
________________________________________
✅ Summary
This flowchart and detailed explanation show the end-to-end lifecycle of your project:
User → UI → Controller → Repository → Database → Docker → Kubernetes → UI

🛠 Troubleshooting
Application Not Starting
•	Check MongoDB connection.
•	Verify environment variables in K8s.
•	View logs:
kubectl logs <pod-name>
MongoDB Connection Issues
•	Check MONGO_DB_HOSTNAME matches service name.
•	Confirm secrets and configmap values.

---

## 🔐 Security Best Practices Used

* Credentials stored in Kubernetes Secrets
* No hardcoded passwords
* Environment-based configuration
* Persistent storage for MongoDB

---

## 📈 Scalability & Production Readiness

* Stateless Spring Boot pods
* Horizontally scalable
* MongoDB persistence via PVC
* Rolling updates supported
* Logging & tracing enabled

---

## 🏁 Conclusion

This project demonstrates **end-to-end enterprise application delivery**, from **code → container → Kubernetes**.

📄 License
This project is for training and learning purposes.
