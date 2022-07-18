package com.example.springwebfluxdemo.controller;

import com.adidas.promo.backend.domain.model.LoginInfo;
import com.adidas.promo.backend.security.token.SfdcAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@Slf4j
@RestController
public class HelloController {
    @GetMapping
    public String sayHello() {
        return "Hello from spring-webflux-demo";
    }

//    @GetMapping("/websession")
//    public Mono<String> getSession(WebSession session) {
//        return Mono.just("Hello");
//    }

    @GetMapping("/hello")
    public Mono<Map<String, String>> hello(Mono<Principal> principal) {
        return principal
                .map(Principal::getName)
                .map(this::helloMessage);
    }

    @GetMapping("/auth")
    Mono<Void> getItems() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .doOnNext(auth -> {
//                    User user = (User) auth.getPrincipal();
//                    log.info("\n-- User: {}", user);
//                    log.info("\n-- Username: {}", user.getUsername());
//                    log.info("\n-- Password: {}", user.getPassword());
//                    log.info("\n-- isAuthenticated: {}", auth.isAuthenticated());
                    log.info(String.valueOf(auth));

                    SfdcAuthenticationToken authentication = (SfdcAuthenticationToken) auth;
                    LoginInfo loginInfo = authentication.getLoginInfo();
                    log.info("\n--- loginInfo: {}", loginInfo);
                    log.info("\n-- Username: {}", loginInfo.getUsername());
                    log.info("\n-- Password: {}", auth.getCredentials());
                    log.info("\n-- isAuthenticated: {}", authentication.isAuthenticated());

                }).then();
    }

    private Map<String, String> helloMessage(String username) {
        return Collections.singletonMap("message", "Hello " + username + "!");
    }
}
