package edu.greenriver.sdev.myspringproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;

/**
 * Creates a Permission object that stores a user's authority roles
 * @author Ryan Rivera
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Permissions implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permissionId;
    private String role;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @Override
    public String getAuthority()
    {
        return role;
    }
}