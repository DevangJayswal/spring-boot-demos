package com.example.springwebfluxdemo.reactor;

import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class MonoJustDemo {
    public static void main(String[] args) {
        // Publisher (like Stream)
        Mono<Integer> mono = Mono.just(1);

        // No.1 rule of reactive programming is that
        // nothing happens until you subscribe

        // it will just print object reference. because we haven't subscribed yet
//        System.out.println(mono);

        // let's subscribe using `subscribe(Consumer<? super T> consumer)`
        // we subscribed to publisher and asked publisher to emit the data
        Consumer<Integer> integerConsumer =
                integerMono -> System.out.println("Received: " + integerMono);
        mono.subscribe(integerConsumer);

        // Publisher
        Mono<String> ball = Mono.just("ball");
        // onNext()
        Consumer<String> stringConsumer = item -> System.out.println("Received: " + item);
        // onError()
        Consumer<Throwable> throwableConsumer = err -> System.out.println(err.getMessage());
        // onComplete()
        Runnable completed = () -> System.out.println("Completed");

        ball.subscribe(stringConsumer, throwableConsumer, completed);

        // like Stream pipeline we can decorate our Mono (Publisher) pipeline
        // with more operations. e.g.
        // Mono.just(1).map()
        // Mono.just(1).filter()
        // Mono.just(1).flatMap()

        Mono<Integer> ballLength = Mono.just("ball")
                .map(ballItem -> ballItem.length())
                // would create an error
                .map(ballSize -> ballSize / 0);

        ballLength.subscribe(
                b -> System.out.println("Received Ball Size: " + b),
                err -> System.out.println("Error message: " + err.getMessage()),
                () -> System.out.println("Completed")
        );
    }
}
