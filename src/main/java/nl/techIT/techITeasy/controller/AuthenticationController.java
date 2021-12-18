package nl.techIT.techITeasy.controller;

import com.fasterxml.classmate.members.ResolvedParameterizedMember;
import nl.techIT.techITeasy.controller.dto.AuthenticationRequestDto;
import nl.techIT.techITeasy.controller.dto.AuthenticationResponseDto;
import nl.techIT.techITeasy.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        AuthenticationResponseDto authenticationResponseDto = authenticationService.authenticateUser(authenticationRequestDto);
        return ResponseEntity.ok(authenticationResponseDto);
    }
}
