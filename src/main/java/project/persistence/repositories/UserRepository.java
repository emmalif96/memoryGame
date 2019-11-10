package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.persistence.entities.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User save(User user);

    void delete(User user);

    User userLogin(String username);

    User getUser(String username);

    List<User>findAll();

    User findByUsername(String username);

    User findOne(long id);

    Boolean [] userNameExists(String username);

    User findOne(Long id);

    List<User> findUsers(String username);

    List<User> getUsersById(@Param("ids") List<Long> userids);

    User getUsersByUsername(String username);

    User findOneByUsername(String name);

    User findOneById(long id);
}
