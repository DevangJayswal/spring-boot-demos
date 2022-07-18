package com.example.springwebfluxdemo.websocket;

import lombok.Data;

@Data
public class Message {
    private String from;
    private String text;
}