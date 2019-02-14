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
            users.add(new User("user"+i+"@gmail.com","passw"+i,1000+i));
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

    public int getScore(String userName){
        int score = 0;
        for (User user : users) {
            if ((user.getUserId()).equals(userName)) {
                score = user.getScore();
            }
        }
        return score;
    }

    public void addUser(String userName, String password) {
        users.add(new User(userName,password,1000));
    }

    public User getUser(String userId){
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers(int page, int pageSize) {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(users.size(),(page+1)*pageSize);

        return users.subList(from, to);
    }
}
