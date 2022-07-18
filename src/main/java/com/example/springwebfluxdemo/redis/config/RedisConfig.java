package com.example.springwebfluxdemo.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.ReactiveRedisSessionRepository;
import org.springframework.web.server.WebSession;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class RedisConfig {
    @Bean
    public String findSessionById(ReactiveRedisConnectionFactory factory) {

//        1.
        // keySerializer
        StringRedisSerializer keySerializer = new StringRedisSerializer();

        // valueSerializer
        Jackson2JsonRedisSerializer<Object> valueSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // create builder using keySerializer
        RedisSerializationContext.RedisSerializationContextBuilder<String, Object> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);

        // create context using builder (uses keySerializer) and valueSerializer
//        RedisSerializationContext<String, Object> context = builder.value(valueSerializer).build();
//        RedisSerializationContext<String, Object> context = builder.value(new JdkSerializationRedisSerializer()).build();


//        2.
//        RedisSerializationContext<String, Object> context =
//                RedisSerializationContext.<String, Object>newSerializationContext(new JdkSerializationRedisSerializer())
//                        .build();

//        1.1
//        ReactiveRedisTemplate<String, Object> redisTemplate = new ReactiveRedisTemplate<>(factory, context);
//
//        ReactiveSessionRepository<? extends Session> repository = new ReactiveRedisSessionRepository(redisTemplate);
//
//        repository.findById("b3985697-30db-4846-a98d-ab8f034eaf2f").subscribe((session)->
//                System.out.println("\n\n ---------- FOUND")
//        );


//        WebSession;

        return "DUMMY_STRING";
    }
}
