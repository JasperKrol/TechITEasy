package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.controller.dto.UserPostRequestDto;
import nl.techIT.techITeasy.exceptions.BadRequestException;
import nl.techIT.techITeasy.exceptions.InvalidPasswordException;
import nl.techIT.techITeasy.exceptions.NotAuthorizedException;
import nl.techIT.techITeasy.exceptions.UserNotFoundException;
import nl.techIT.techITeasy.model.Authority;
import nl.techIT.techITeasy.model.User;
import nl.techIT.techITeasy.repository.UserRepository;
import nl.techIT.techITeasy.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

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
            String apiKey = RandomStringGenerator.generateAlphaNumeric(20);

            User user = new User();
            user.setUsername(userPostRequest.getUsername());
            user.setPassword(encryptedPassword);
            user.setApikey(apiKey);
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

    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        } else {
           throw new UserNotFoundException("user not found");
        }
    }

    public void removeAuthority(String username, String authorityString){
        Optional<User> optionalUser = userRepository.findById(username);

        if (optionalUser.isEmpty()){
            throw new UserNotFoundException("user " + username +" not found");
        }
        else {
            User existingUser = optionalUser.get();
            existingUser.removeAuthority(authorityString);
            userRepository.save(existingUser);
        }
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        return user.getAuthorities();
    }

    //Userpassword
    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    private boolean isValidPassword(String password){
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 1;
        final int MIN_LOWER = 1;
        final int MIN_UPPER = 1;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = true;
        if (password.length() < MIN_LENGTH) validPassword = false;
        if (countLower < MIN_LOWER) validPassword = false;
        if (countUpper < MIN_UPPER) validPassword = false;
        if (countDigit < MIN_DIGITS) validPassword = false;
        if (countSpecial < MIN_SPECIAL) validPassword = false;

        return validPassword;
    }

    public void setPassword(String username, String userPassword) {
        if (username.equals(getCurrentUserName())){
            if (isValidPassword(userPassword)){
                Optional<User> optionalUser = userRepository.findById(username);
                if (optionalUser.isPresent()){
                    User currentUser = optionalUser.get();
                    currentUser.setPassword(passwordEncoder.encode(userPassword));
                    userRepository.save(currentUser);
                }
                else {
                    throw new UserNotFoundException("user " + username +" not found");
                }
            }
            else {
                throw new InvalidPasswordException("Incorrect entry of password check validations");
            }
        }
        else {
            throw new NotAuthorizedException("Not authorized");
        }
    }
}
