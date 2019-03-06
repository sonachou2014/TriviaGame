package trivia;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findUserByName(String name);

//    @Query ("SELECT u FROM USERS u WHERE u.IS_ONLINE = ?1")
    List<User> findByIsOnlineTrue ();
}
