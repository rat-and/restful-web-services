package edu.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/dummy-link")
    public User dummyMethod() {
        return null;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userDaoService.find(id);
        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        EntityModel<User> model = EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        WebMvcLinkBuilder linkToDummy = linkTo(methodOn(this.getClass()).dummyMethod());

        model.add(linkToUsers.withRel("all-users"));
        model.add(linkToDummy.withRel("dummy"));

        return model;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        User deletedUser = userDaoService.deleteById(id);
        if (deletedUser == null) {
            throw new UserNotFoundException("id - " + id);
        }

        return ResponseEntity.noContent().build();
    }
}
