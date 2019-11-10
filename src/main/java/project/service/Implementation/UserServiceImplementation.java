package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.persistence.entities.*;
import project.persistence.repositories.RolesRepository;
import project.persistence.repositories.UserRepository;
import project.service.UserService;

import java.util.List;


@Service
public class UserServiceImplementation implements UserService {

    private UserRepository repository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private RolesRepository rolesRepository;

    @Autowired
    public UserServiceImplementation(UserRepository repository, RolesRepository rolesRepository) {

        this.repository = repository;
        this.rolesRepository = rolesRepository;

    }

    @Override
    public void save(User user) {
        User username = repository.findByUsername(user.getUsername());
        if(username == null)
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(user);
            Roles auth = new Roles(user.getUsername(), user.getRole());
            rolesRepository.save(auth);
        }
    }


    @Override
    public void delete(User user) {
        Roles auth = rolesRepository.findByUsername(user.getUsername());
        rolesRepository.delete(auth);
        repository.delete(user);
    }

    @Override
    public User userLogin(String username, String password) throws NullPointerException
    {
        User possibleUser = new User();
        try{
            possibleUser = repository.userLogin(username);
            if(passwordEncoder.matches(password, possibleUser.getPassword())) {
                return possibleUser;
            } else {

            }

            return possibleUser;
        } catch (NullPointerException e){

        }
        return possibleUser;
    }

    @Override
    public User getUser(String username) {
        return repository.getUser(username);
    }
    public User findOne(long id) { return repository.findOneById(id); }

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User findOne(Long id){
        return repository.findOne(id);
    }

    @Override
    public List<User>findAll(){return repository.findAll();}

    @Override
    public List<User> searchUser(String username){
        return repository.findUsers(username);
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Boolean [] userNameExists(String username){
        return repository.userNameExists(username);
    }

    public List<User> getUsersById(List<Long> userids){return repository.getUsersById(userids);	}

    public User getUsersByUsername(String username){return repository.getUsersByUsername(username);}

}

/*      + UserServiceImplementation(repository : UserRepository)
        + searchUser(String username) : List<User>
        + acceptInvite(invite : Invite) : Invite
        + declineInvite(invite : Invite) : Invite
        + getInvitesForUser(user : User) : List<Invite>
*/