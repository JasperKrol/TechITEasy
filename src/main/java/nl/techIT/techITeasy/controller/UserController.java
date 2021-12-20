package nl.techIT.techITeasy.controller;

import nl.techIT.techITeasy.controller.dto.UserPostRequestDto;
import nl.techIT.techITeasy.model.User;
import nl.techIT.techITeasy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<Object> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Object> getUser(@PathVariable("username") String username){
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@RequestBody UserPostRequestDto user){
        String newUsername = userService.createUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUsername)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userService.getAuthorities(username));
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @RequestBody User user){
        userService.updateUser(username, user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity deleteUser (@PathVariable("username") String username){
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authorityString){
        userService.removeAuthority(username,authorityString);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{username}/password")
    public ResponseEntity<Object> setPassword(@PathVariable("username") String username, @RequestBody String userPassword){
        userService.setPassword(username,userPassword);
        return ResponseEntity.noContent().build();
    }
}
