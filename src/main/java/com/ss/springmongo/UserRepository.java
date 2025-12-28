// Define the package this interface belongs to
package com.ss.springmongo;

// Import Spring Data repository for paging and sorting functionality
import org.springframework.data.repository.PagingAndSortingRepository;

// This interface provides CRUD operations and paging/sorting capabilities for User documents
// It extends PagingAndSortingRepository with the entity type User and ID type String
public interface UserRepository extends PagingAndSortingRepository<User, String> {
    // No additional methods are needed here because PagingAndSortingRepository
    // already provides methods like:
    // save(), findById(), findAll(), delete(), count(), and findAll(Pageable pageable)
}
