package com.myview.rest.webservices.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

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
    public EntityModel<User> getUser(@PathVariable int id){
        User user = userDaoService.findOne(id);
        if(user==null){
            throw new UserNotFoundException("Id:-" + id);
        }
        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping ("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userDaoService.deleteById(id);
        if(user==null){
            throw new UserNotFoundException("Id:-" + id);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
       return ResponseEntity.created(location).build();
    }
}
