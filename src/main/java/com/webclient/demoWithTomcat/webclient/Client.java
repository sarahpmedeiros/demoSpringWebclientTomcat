package com.webclient.demoWithTomcat.webclient;

import com.webclient.demoWithTomcat.User;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class Client {

    private static final Logger LOG = Logger.getLogger(WebClient.class.getName());
    private WebClient webClient;

    public Client() {
//        default settings for webclient object
//        this.webClient = WebClient.create();

//        default settings with base url
//        this.webClient = WebClient.create("https://api.github.com");
//        ^ doesn't allow custom filters

        this.webClient = WebClient
                .builder()
                .baseUrl("https://api.github.com")
                .filter(filterFunction)
                .filter(logRequstUrl())
                .build();
    }

    ExchangeFilterFunction filterFunction = (clientRequest, nextFilter) -> {
        LOG.info("WebClient fitler executed");
        return nextFilter.exchange(clientRequest);
    };

    private ExchangeFilterFunction logRequstUrl() {
        return (clientRequest, nextFilter) -> {
            LOG.info(String.format("Request url (%s)", clientRequest.url()));
            return nextFilter.exchange(clientRequest);
        };
    }


    public User getUserReturnUser(String username) {
        LOG.info(String.format("Calling getUser(%s) to return user obj", username));

        return webClient.get()
                .uri("/users/{username}", username)
                .retrieve()
                .bodyToMono(User.class)
                .block();

    }


    public Mono<User> getUser(String username) {
        LOG.info(String.format("Calling getUser(%s)", username));

//       Use .retrieve to just get the response body
        return webClient.get()
                .uri("/users/{username}", username)
                .retrieve()
                .bodyToMono(User.class);

//      use .exchange when you want to handle the entire client response including status codes
//        return webClient.get()
//                .uri("/users/{username}", username)
//                .exchange()
//                .doOnSuccess(clientResponse -> System.out.println("clientResponse.statusCode() = " + clientResponse.statusCode()))
//                .flatMap(clientResponse -> clientResponse.bodyToMono(User.class));
    }


    public Flux<User> getUserList() {
        LOG.info(String.format("Calling get user list"));

        return WebClient
                .builder()
                .baseUrl("http://localhost:8080/")
                .build()
                .get()
                .uri("userlist")
                .retrieve()
                .bodyToFlux(User.class);
    }

}
