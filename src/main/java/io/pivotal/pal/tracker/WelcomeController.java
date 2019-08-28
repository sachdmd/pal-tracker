package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {


    private String sayHello;

   // @Autowired
    public WelcomeController(@Value("${welcome.message}") String sayHello){
        this.sayHello=sayHello;

    }
    @GetMapping("/")
    public String sayHello() {
        return sayHello;
    }
}


