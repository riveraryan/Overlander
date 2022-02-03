package edu.greenriver.sdev.myspringproject.service;

import edu.greenriver.sdev.myspringproject.data.IUserRepository;
import edu.greenriver.sdev.myspringproject.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Service layer for User class objects
 * @author Ryan Rivera
 * @version 1.0
 */
@Service
public class LoginService implements UserDetailsService
{
    private IUserRepository userRepository;

    /**
     * Constructor
     * @param userRepository User repo
     */
    public LoginService(IUserRepository userRepository)
    {
        this.userRepository = userRepository;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findUserByUsername(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("Username is not recognized");
    }
}