package com.webclient.demoWithTomcat.webclient;

import com.webclient.demoWithTomcat.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    @GetMapping("/userlist")
    private List<User> getAllTweets() throws InterruptedException {
        Thread.sleep(2000L); // delay
        return Arrays.asList(
                new User("smedeiros",1,"Sarah"),
                new User("lholden",2,"Leah"),
                new User("htawar",3,"Hitesh")
        );
    }

}
