//package edu.greenriver.sdev.myspringproject.controllers;
//
//import edu.greenriver.sdev.myspringproject.models.Profile;
//import edu.greenriver.sdev.myspringproject.service.ProfileService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
///**
// * Controller for the Overlander Profile API
// * @author Ryan Rivera
// * @version 1.0
// */
//@RestController
//@RequestMapping("overlander/api/v1/profiles")
//@CrossOrigin("*")
//public class ProfileApiController
//{
//    private ProfileService service;
//
//    /**
//     * Constructor
//     * @param service Profile service layer
//     */
//    public ProfileApiController(ProfileService service)
//    {
//        this.service = service;
//    }
//
//    /**
//     * API endpoint that returns all Profiles in the database
//     * @return ResponseEntity<List<Profiles>> all profiles in the database
//     */
//    @GetMapping()
//    public ResponseEntity<List<Profile>> getAllProfiles()
//    {
//        return new ResponseEntity<>(service.allProfiles(), HttpStatus.OK);
//    }
//
//    /**
//     * API endpoint that returns all Profiles in the database that match the given id
//     * @param id Profile Id
//     * @return ResponseEntity<Profile> Profile matching the id
//     */
//    @GetMapping("{id}")
//    public ResponseEntity<Profile> getProfileById(@PathVariable int id)
//    {
//        if(!service.profileExists(id)){
//            return new ResponseEntity<>(service.findProfileById(id), HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(service.findProfileById(id), HttpStatus.OK);
//    }
//
//    /**
//     * API endpoint that adds a given Profile to the database
//     * @param profile Profile object
//     * @return ResponseEntity<Profile> the Profile that was saved
//     */
//    @PostMapping
//    public ResponseEntity<Profile> addProfile(@RequestBody Profile profile)
//    {
//        if(profile == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(service.saveProfile(profile), HttpStatus.CREATED);
//    }
//
//    /**
//     * API endpoint that edits an existing Profile object in the database
//     * @param profile Profile object
//     * @return ResponseEntity<Profile> Profile with edits
//     */
//    @PutMapping
//    public ResponseEntity<Profile> editProfile(@RequestBody Profile profile)
//    {
//        if(profile == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(service.editProfile(profile), HttpStatus.OK);
//    }
//
//    /**
//     * API endpoint that deletes an existing Event object in the database
//     * @param id int id of Event to delete
//     * @return ResponseEntity object with HttpStatus code
//     */
//    @DeleteMapping("{id}")
//    public ResponseEntity<Profile> deleteProfile(@PathVariable int id)
//    {
//        if(!service.profileExists(id)){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        service.deleteProfile(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//}