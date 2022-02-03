package edu.greenriver.sdev.myspringproject.data;

import edu.greenriver.sdev.myspringproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for the User model that creates a repository with CRUD access to User database records
 * @author Ryan Rivera
 * @version 1.0
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Integer>
{
    /**
     * Returns a User object with a username matching the given String
     * @param username String username
     * @return User object matching the given String
     */
    User findUserByUsername(String username);

    /**
     * Returns a User object with a user id matching the given int
     * @param id int user id
     * @return User object matching the given String
     */
    User findUserByUserId(int id);
}