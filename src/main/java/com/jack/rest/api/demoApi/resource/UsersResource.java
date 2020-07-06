package com.jack.rest.api.demoApi.resource;

import com.jack.rest.api.demoApi.documents.Login;
import com.jack.rest.api.demoApi.documents.Users;
import com.jack.rest.api.demoApi.exception.UserAlreadyExist;
import com.jack.rest.api.demoApi.exception.UserNotFoundException;
import com.jack.rest.api.demoApi.repositories.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class  UsersResource {

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    private UsersRepository usersRepository;
    public UsersResource(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    public List<Users> getAll(){
        return usersRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<Users> getUser(@PathVariable int id){
        Optional<Users> user = usersRepository.findById(id);

        if(!user.isPresent())
            throw new UserNotFoundException("User Not Found of id "+id);
        return user;
    }

    @GetMapping("/users/search/{name}")
    public List<Users> getUserByName(@PathVariable String name){
        List<Users> user = new ArrayList<Users>();
        List<Users> userPresent = usersRepository.findAll();

        for (Users users :userPresent){
            System.out.println(users.getName());
            if(users.getName().equalsIgnoreCase(name)){
                    user.add(users);
            }
        }
        if(user.size()==0)
            throw  new UserNotFoundException("No user is found with this name "+name);
        return user;
    }

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody Users users){
        List<Users> userPresent = usersRepository.findAll();
        for (Users user : userPresent){
            if (users.getEmail().equalsIgnoreCase(user.getEmail()))
                throw new UserAlreadyExist("User Already Exist with same email Id");
        }
        //users.setPassword(encoder.encode(users.getPassword()));
        usersRepository.save(users);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        Optional<Users> user = usersRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("User Not found with id "+id);
        usersRepository.deleteById(id);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody Users users, @PathVariable Integer id){
        Optional<Users> user = usersRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("User id not present with \""+id+"\" id");

        usersRepository.save(users);
    }

}
