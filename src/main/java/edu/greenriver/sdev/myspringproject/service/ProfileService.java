//package edu.greenriver.sdev.myspringproject.service;
//
//import edu.greenriver.sdev.myspringproject.data.IProfileRepository;
//import edu.greenriver.sdev.myspringproject.models.Profile;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.NoSuchElementException;
//
///**
// * Service layer for Profile class objects
// * @author Ryan Rivera
// * @version 1.0
// */
//@Service
//public class ProfileService
//{
//    private IProfileRepository repo;
//
//    /**
//     * Constructor
//     * @param repo Profile Repo
//     */
//    public ProfileService(IProfileRepository repo)
//    {
//        this.repo = repo;
//    }
//
//    /**
//     * Returns a list of all Profiles in the repository
//     * @return List<Profile> list of all Profiles in the repository
//     */
//    public List<Profile> allProfiles()
//    {
//        return repo.findAll();
//    }
//
//    /**
//     * Returns an Profile object from the repo if it matches the id
//     * @param id int Profile id
//     * @return Profile object matching id param
//     */
//    public Profile findProfileById(int id)
//    {
//        return repo.findProfileByProfileId(id);
//    }
//
//    /**
//     * Returns a boolean true if a result is found matching the id, false otherwise
//     * @param id String id
//     * @return Profile results matching the id
//     */
//    public boolean profileExists(int id)
//    {
//        return repo.findProfileByProfileId(id) != null;
//    }
//
//    /**
//     * Saves the Profile parameter to the database
//     * @param profile Profile to be saved to the database
//     * @return Profile that was saved
//     */
//    public Profile saveProfile(Profile profile)
//    {
//        return repo.save(profile);
//    }
//
//    /**
//     * Edits an existing Profile in the database if it exists, otherwise an exception is thrown
//     * @param profile Profile with edits
//     * @return the Profile with edits
//     */
//    public Profile editProfile(Profile profile)
//    {
//        if(!profileExists(profile.getProfileId())){
//            throw new NoSuchElementException("Missing profile");
//        }
//        return repo.save(profile);
//    }
//
//    /**
//     * Deletes a Profile from the database
//     * @param id int profile id
//     */
//    public void deleteProfile(int id)
//    {
//        if(!profileExists(id)){
//            throw new NoSuchElementException("Profile not found");
//        }
//        repo.deleteById(id);
//    }
//}