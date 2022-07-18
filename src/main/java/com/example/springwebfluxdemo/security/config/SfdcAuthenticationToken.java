package com.example.springwebfluxdemo.security.config;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * The purpose of creating a SfdcAuthenticationToken is that we do not have typical [GrantedAuthority authorities]
 * in our system. Instead, we are using LoginInfo object to store the information about user roles and other things
 * <p>
 * Please note that UsernamePasswordAuthenticationToken is an Authentication object internally
 * So SfdcAuthenticationToken is an Authentication object.
 * Once the user is authenticated and authorized this Authentication (SfdcAuthenticationToken) will be given back
 * to client
 */

@Getter
public class SfdcAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1429784916536508156L;
    private final LoginInfo loginInfo;

    /**
     * Typically, third argument of this constructor should be `Collection<? extends GrantedAuthority> authorities`
     * but here we are using LoginInfo object to store information about user. for e.g. access_token and other things
     * <p>
     * So [GrantedAuthority authorities] in Authentication object will be null, instead we would make use of LoginInfo
     * for that purpose
     */

    public SfdcAuthenticationToken(Object principal, Object credentials, LoginInfo loginInfo) {

        /*
           When creating an object of UsernamePasswordAuthenticationToken, it requires username, password and optional authorities
           By default, when we extend a class then super class' default constructor is invoked
           super class UsernamePasswordAuthenticationToken doesn't have default constructor
           if we do not call below UsernamePasswordAuthenticationToken constructor then it would throw an error
         */
        // we need some authorities (even null authorities) or else the token is not authenticated!
        super(principal, credentials, null);
        this.loginInfo = loginInfo;
    }
}