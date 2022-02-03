//package edu.greenriver.sdev.myspringproject.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import javax.persistence.*;
//import java.util.List;
//
///**
// * This class is used to create Profile objects that store a user's information
// * @author Ryan Rivera
// * @version 1.0
// */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//public class Profile
//{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int profileId;
//    private String fName;
//    private String lName;
//    private String email;
//    private String phone;
//
//    //For when the events are connected to profiles
//    @ToString.Exclude
//    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<Event> events;
//}
