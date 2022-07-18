package com.example.springwebfluxdemo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxMonoGeneratorService {

    public static void main(String[] args) {


        FluxMonoGeneratorService fluxMonoGeneratorService = new FluxMonoGeneratorService();
        fluxMonoGeneratorService.namesFlux()
                .subscribe(name -> {
                    System.out.println(name);
                });


        fluxMonoGeneratorService.nameMono()
                .subscribe(name ->
                        System.out.println(name)
                );

        System.out.println("-- map");
        fluxMonoGeneratorService.namesFlux_map()
                .subscribe(nameUpper -> System.out.println(nameUpper));

        System.out.println("-- immutability");
        // as you can see the names are not in upper case as expected because,
        // reactive streams are immutable
        fluxMonoGeneratorService.namesFlux_immutability().subscribe(System.out::println);

    }

    public Flux<String> namesFlux_immutability() {

        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "cloe"));
        // reactive streams are immutable
        // map operator won't make any changes
        namesFlux.map(String::toUpperCase);
        return namesFlux;

    }

    public Flux<String> namesFlux_map() {
        return Flux.fromIterable(List.of("alex", "ben", "cloe"))
                .map(name -> {
                    // get String -> return String
                    return name.toUpperCase();
                });
    }

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "cloe"))
                // how do we look into the events that happening behind the scenes and the communication between
                // the Publisher and Subscriber
                .log() // this is going to log each and every event that's happening between the Subscriber
                // and Publisher communication

                // request(unbounded) -> means give me all the data

                ;
    }

    public Mono<String> nameMono() {
        return Mono.just("Devang").log();
    }


}
