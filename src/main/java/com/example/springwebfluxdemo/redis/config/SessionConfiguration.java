package com.example.springwebfluxdemo.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

//@EnableRedisWebSession
public class SessionConfiguration {

//    @Bean
//    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(ReactiveRedisConnectionFactory factory) {
//        return factory;
//    }

}