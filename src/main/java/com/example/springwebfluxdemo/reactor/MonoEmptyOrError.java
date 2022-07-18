package com.example.springwebfluxdemo.reactor;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public class MonoEmptyOrError {
    public static void main(String[] args) {

        Mono<String> monoJust = Mono.just("devang"); // String name = new String("devang");
        System.out.println(monoJust); // object will not be created unless we subscribe
        // so we would just see MonoJust in output

        Disposable subscribe = Mono.just("devang").subscribe();
        System.out.println(subscribe); // object will be created/consumed in memory
        // but we will have to provide a Consumer in order to access the data and print it on the console
        // we will see the reference of the Subscriber in output as we have just subscribed
        // but did not specify what to do once the object is created
        // We need to specify Consumer to tell what to do

        Mono<String> name = Mono.just("devang"); // Object will not be created/consumed in memory
        name.subscribe( // object will be created once we subscribe
                data -> System.out.println(data) // specified the Consumer - what to do once the object is created
                // here we are printing the String object on the console
        ); // String name = new String("devang"); System.out.println(name);

        Mono.just("jayswal").subscribe(Util.onNext());

        Mono<Object> monoEmpty = Mono.empty();
        System.out.println(monoEmpty); // String empty = null;

        Mono<Object> error_occurred = Mono.error(new RuntimeException("Error Occurred"));
        error_occurred.subscribe(
                o -> { // do nothing onNext
                },
                error -> { // onError
                    System.out.println(error.getMessage());
                }
        );

        // userId = 1 = <random_name>
        // userId = 2 = Mono.empty()
        // userId = x = ERROR: RuntimeException("Not in the allowed range")
        userRepository(1)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }

    // returns firstName of User if userId 1
    // returns empty of User if userId 2
    // returns error of User if any other userId
    private static Mono<String> userRepository(int userId) {
        if (userId == 1) {
            String firstName = Util.faker().name().firstName();
            return Mono.just(firstName);
        } else if (userId == 2) {
            // do not use `null` as it would throw NullPointerException
            return Mono.empty(); // kind of null
        } else {
            return Mono.error(new RuntimeException("Not in the allowed range")); // kind of throw new RuntimeException()
        }
    }
}
