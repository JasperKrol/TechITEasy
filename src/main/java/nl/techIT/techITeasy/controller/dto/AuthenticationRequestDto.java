package nl.techIT.techITeasy.controller.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.Size;

public class AuthenticationRequestDto {

    @NotNull
    private String username;

    @Size(min = 8)
    private String password;

    // constructor
    public AuthenticationRequestDto(){};
    public AuthenticationRequestDto(String username, String password){
        this.username = username;
        this.password = password;
    }

    // getters setters
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
