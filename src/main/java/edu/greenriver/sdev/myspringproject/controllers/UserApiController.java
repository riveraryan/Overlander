package edu.greenriver.sdev.myspringproject.controllers;

import edu.greenriver.sdev.myspringproject.models.User;
import edu.greenriver.sdev.myspringproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller for the Overlander User API
 * @author Ryan Rivera
 * @version 1.0
 */
@RestController
@RequestMapping("overlander/api/v1/users")
@CrossOrigin("*")
public class UserApiController
{
    private UserService service;

    /**
     * Constructor
     * @param service User service layer
     */
    public UserApiController(UserService service)
    {
        this.service = service;
    }

    /**
     * API endpoint that returns all Users in the database
     * @return ResponseEntity<List<User>> all users in the database
     */
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<>(service.allUsers(), HttpStatus.OK);
    }

    /**
     * API endpoint that returns all Users in the database that match the given id
     * @param id User Id
     * @return ResponseEntity<User> User matching the id
     */
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id)
    {
        if(!service.userExists(id)){
            return new ResponseEntity<>(service.findUserById(id), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(service.findUserById(id), HttpStatus.OK);
    }

    /**
     * API endpoint that adds a given User to the database
     * @param user User object
     * @return ResponseEntity<User> the User that was saved
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user)
    {
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED);
    }

    /**
     * API endpoint that edits an existing User object in the database
     * @param user User object
     * @return ResponseEntity<User> User with edits
     */
    @PutMapping
    public ResponseEntity<User> editUser(@RequestBody User user)
    {
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.editUser(user), HttpStatus.OK);
    }

    /**
     * API endpoint that deletes an existing Event object in the database
     * @param id int id of Event to delete
     * @return ResponseEntity object with HttpStatus code
     */
    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUser(@PathVariable int id)
    {
        if(!service.userExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}