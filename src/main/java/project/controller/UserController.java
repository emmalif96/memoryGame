package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import project.persistence.entities.User;
import project.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static List<String> notendaVillur = new ArrayList<String>();
    private static Boolean allGood;

    private UserDetails userDetails;

    private User myUser;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method =  {RequestMethod.GET, RequestMethod.POST})
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username and password do not match";
        }
        if (logout != null) {
            errorMessage = "You have been logged out";
        }
        getUser();
        try {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String username = loggedInUser.getName();
        } catch (Exception e) {
            System.out.println(e);
        }
        return errorMessage;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public List<String> registerPost(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("passwordRepeat") String passwordRepeat,
                                     @RequestParam("name") String name,
                                     @RequestParam("role") String role,
                                     @RequestParam("emailAddress") String email
    ) {
        notendaVillur.clear();
        allGood = true;
        getErrors(username, password, passwordRepeat);
        if (allGood) {
            User newUser = new User(username, password, name, email, role
                    /*groups, event, todo, lifestyle, invite */);
            userService.save(newUser);
        }

        return notendaVillur;
    }

    private void getErrors(String notendanafn, String lykilord, String lykilordRepeat) {
        if (!lykilord.equals(lykilordRepeat)) {
            notendaVillur.add("Passwords must match");
            allGood = false;
        }
        String[] islenskirStafir = {"á", "Á", "ð", "Ð", "é", "É", "í", "Í", "ó", "Ó", "ú", "Ú", "ý", "Ý", "þ", "Þ", "æ", "Æ", "Ö", "ö", " "};
        for (String item : islenskirStafir) {
            if (notendanafn.contains(item)) {
                notendaVillur.add("Please dont use special characters or spaces");
                allGood = false;
                break;
            }
        }
        if (notendanafn.length() == 0) {
            notendaVillur.add("Please specify username");
            allGood = false;
        }
        if (lykilord.length() < 6) {
            notendaVillur.add("Password must be at least 6 characters");
            allGood = false;
        }
        for (String item : islenskirStafir) {
            if (lykilord.contains(item)) {
                notendaVillur.add("Please dont use special characters or spaces");
                allGood = false;
                break;
            }
        }
        for (Boolean item : userService.userNameExists(notendanafn)) {
            if (item) {
                notendaVillur.add("Username taken");
                allGood = false;
                break;
            }
        }
    }
    @GetMapping("/users/search/{username}")
    public List<User> searchUser(@PathVariable(value="username") String username) {
        if(username.length()>0){
            System.out.println(username);
            System.out.println(userService.searchUser(username));
            return userService.searchUser(username);
        }
        System.out.println("finna alla");
        System.out.println(username);
        return userService.findAll();

    }
    public void getUser(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        System.out.println(username);
    }
}


