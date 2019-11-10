package project.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home() {

        return "index";
    }
    
}
