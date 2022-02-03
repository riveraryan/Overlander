package edu.greenriver.sdev.myspringproject.models;

import lombok.*;
import javax.persistence.*;

/**
 * This class is used to create Event objects that store user made event
 * details
 * @author Ryan Rivera
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Event
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String date;
    private String time;
    private String address;
    private String city;
    private String state;
    private String zip;
    @Lob
    private String description;
    private String difficulty;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
}