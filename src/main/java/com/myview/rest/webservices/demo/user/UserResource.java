package com.myview.rest.webservices.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;
    //Retrieve all users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
        return userDaoService.findOne(id);
    }
    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@RequestBody User user){
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }
}
