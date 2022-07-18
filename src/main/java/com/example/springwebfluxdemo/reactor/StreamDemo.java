package com.example.springwebfluxdemo.reactor;

import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        // create stream
        Stream<Integer> integerStream = Stream.of(1).map(integer -> {
            System.out.println("Processing stream");
            // 1s delay
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Processing stream DONE. Returning");
            return integer * 2;
        });

        // you will see immediate output with just object reference as an output
        // because stream is lazy unless you connect the terminal operator nothing will work
        // when we say stream is lazy it will not be executed until it is being connected
        // with terminal operator
//        System.out.println(integerStream);

        // let's connect with terminal operator `forEach` to the stream pipeline
        // now if you observe the output then it is blocking for 1s, and then it prints the output 2
        integerStream.forEach(System.out::println);

        // NOTE: java.util.stream.Stream is blocking and lazy in nature
    }
}
