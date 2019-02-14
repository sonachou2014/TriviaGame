package hello;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepo {

    private List<User> users;

    public UserRepo() {
        users = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            users.add(new User("user"+i+"@gmail.com","passw"+i));
        }

    }

    public String validateUser(String userName, String password) {
        String isUserValid = "userNotValid";
        for (User user : users) {
            if ((user.getUserId()).equals(userName) && (user.getPassword()).equals(password)) {
               isUserValid = "userValid";
               break;
            }

        }
       return isUserValid;

    }

}
