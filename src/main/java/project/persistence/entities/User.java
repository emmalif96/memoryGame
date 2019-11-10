package project.persistence.entities;

import javax.persistence.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.List;


@Entity
@Table(name = "users") // If you want to specify a table name, you can do so here
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, columnDefinition = "serial")
    private Long id;

    private String userName;
    private String password;
    private String name;
    private String email;
    private String role;

    //private Event event;
    //private Todo todo;
    @Column
    @ElementCollection
    private List<Group> groups;
    //private Lifestyle lifestyle;
    //private Invite invite;

    public User() {
    }

    public User(String userName, String password, String name, String email, String role/*, List<Group> groups ,Event event , Todo todo, Group group, Lifestyle lifestyle, Invite invite */) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.groups = groups;
        this.role = role;
        //this.event=event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(){
        this.email=email;
    }

    public String getUsername() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Group> getGroups(){
        return groups;
    }
    public String getRole() {
        return this.role;
    }

    public void setRole(String hlutverk) {
        this.role = role;
    }


}
