package com.jack.rest.api.demoApi.services;

import com.jack.rest.api.demoApi.documents.UserPrincipal;
import com.jack.rest.api.demoApi.documents.Users;
import com.jack.rest.api.demoApi.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository repo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = null;
        List<Users> users = repo.findAll();
        for (Users usr : users){
            if (s.equalsIgnoreCase(usr.getEmail())){
                user = usr;
            }
        }
        if(user == null)
            throw new UsernameNotFoundException("user with this name is not present");

        return new UserPrincipal(user);

    }
}
