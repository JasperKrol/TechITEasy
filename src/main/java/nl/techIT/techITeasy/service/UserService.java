package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.controller.dto.UserPostRequestDto;
import nl.techIT.techITeasy.exceptions.BadRequestException;
import nl.techIT.techITeasy.exceptions.UserNotFoundException;
import nl.techIT.techITeasy.model.User;
import nl.techIT.techITeasy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    //Optional to have a look if exists
    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    public String createUser(UserPostRequestDto userPostRequest){
        try {
            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());

            User user = new User();
            user.setUsername(userPostRequest.getUsername());
            user.setPassword(encryptedPassword);
            user.setEmail(userPostRequest.getEmail());
            user.setEnabled(true);
            user.addAuthority("ROLE_USER");
            for (String s : userPostRequest.getAuthorities()) {
                if (!s.startsWith("ROLE_")) {
                    s = "ROLE_" + s;
                }
                s = s.toUpperCase();
                if (!s.equals("ROLE_USER")) {
                    user.addAuthority(s);
                }
            }
            User newUser = userRepository.save(user);
            return newUser.getUsername();
        }
        catch (Exception exception){
            throw new BadRequestException("Cannot create new user");
        }
    }

    public void updateUser(String username, User user) {
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("User not found");
        } else {
            User existingUser = optionalUser.get();
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            existingUser.setEmail(user.getEmail());
            existingUser.setEnabled(user.isEnabled());
            userRepository.save(existingUser);
        }
    }
}
