package exercise.controller;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

// BEGIN
@RestController
public class WelcomeController {

    @Autowired
    private Daytime daytime;


    @GetMapping("/welcome")
    public String getWelcome(){
        return String.format("It is %s now! Welcome to Spring!", daytime.getName());
    }
}
// END
