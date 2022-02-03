package edu.greenriver.sdev.myspringproject.service;

import edu.greenriver.sdev.myspringproject.data.IUserRepository;
import edu.greenriver.sdev.myspringproject.models.Permissions;
import edu.greenriver.sdev.myspringproject.models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service layer for User class objects
 * @author Ryan Rivera
 * @version 1.0
 */
@Service
public class UserService
{
    private IUserRepository userRepository;
    private ApplicationContext context;

    /**
     * Constructor
     * @param userRepository User Repo
     * @param context Application Context
     */
    public UserService(IUserRepository userRepository, ApplicationContext context)
    {
        this.userRepository = userRepository;
        this.context = context;
    }

    /**
     * Returns a list of all Users in the repository
     * @return List<User> list of all Users in the repository
     */
    public List<User> allUsers()
    {
        return userRepository.findAll();
    }

    /**
     * Returns an User object from the repo if it matches the id
     * @param id int User id
     * @return User object matching id param
     */
    public User findUserById(int id)
    {
        return userRepository.findUserByUserId(id);
    }

    /**
     * Returns a boolean true if a result is found matching the id, false otherwise
     * @param id String id
     * @return Boolean true if User is found, false otherwise
     */
    public boolean userExists(int id)
    {
        return userRepository.findUserByUserId(id) != null;
    }

    /**
     * Returns a boolean true if a result is found matching the username, false otherwise
     * @param username String username
     * @return Boolean true if User is found, false otherwise
     */
    public boolean userExists(String username)
    {
        return userRepository.findUserByUsername(username) != null;
    }

    /**
     * Saves the User parameter to the database
     * @param user User to be saved to the database
     * @return User that was saved
     */
    public User saveUser(User user)
    {
        if(user == null){
            throw new IllegalArgumentException("User object cannot be null");
        }

        BCryptPasswordEncoder encoder = context.getBean(BCryptPasswordEncoder.class);
        user.setPassword(encoder.encode(user.getPassword()));

        Permissions role = new Permissions(0, "ROLE_USER", user);
        user.setPermissions(new ArrayList<>());
        user.getPermissions().add(role);

        return userRepository.save(user);
    }

    /**
     * Edits an existing User in the database if it exists, otherwise an exception is thrown
     * @param user User with edits
     * @return the User with edits
     */
    public User editUser(User user)
    {
        if(!userExists(user.getUserId())){
            throw new NoSuchElementException("Missing user");
        }
        return userRepository.save(user);
    }

    /**
     * Deletes a User from the database
     * @param id int user id
     */
    public void deleteUser(int id)
    {
        if(!userExists(id)){
            throw new NoSuchElementException("Profile not found");
        }
        userRepository.deleteById(id);
    }
}
