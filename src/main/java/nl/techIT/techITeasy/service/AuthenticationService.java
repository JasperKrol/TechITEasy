package nl.techIT.techITeasy.service;

import nl.techIT.techITeasy.controller.dto.AuthenticationRequestDto;
import nl.techIT.techITeasy.controller.dto.AuthenticationResponseDto;
import nl.techIT.techITeasy.exceptions.UserNotFoundException;
import nl.techIT.techITeasy.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtl;

    public AuthenticationResponseDto authenticateUser(AuthenticationRequestDto authenticationRequestDto) {

        //Get password and username
        String username = authenticationRequestDto.getUsername();
        String password = authenticationRequestDto.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException exception) {
            throw new UserNotFoundException("Incorrect username or password");
        }

        //Get userDetails and create and token based on username and password to be authenticated
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtl.generateToken(userDetails);

        //give response back with token for frontend to get
        return new AuthenticationResponseDto(jwt);
    }
}
