package com.webclient.demoWithTomcat;

import com.webclient.demoWithTomcat.webclient.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class DemoWithTomcatApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWithTomcatApplication.class, args);

		Client myClient = new Client();
		Mono<User> user = myClient.getUser("octocat");
		user.block();
		user.subscribe(u -> System.out.println(u.getName()));

		User myUserObj = myClient.getUserReturnUser("sarahpmedeiros");
		System.out.println(myUserObj.getName());

		Flux<User> userList = myClient.getUserList();
		userList.subscribe(myUser -> System.out.println(myUser.getName() + " " + myUser.getId()));

	}

}
