//package trivia;
//
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserRepo {
//
//    private List<User> users;
//
//    public UserRepo() {
//        users = new ArrayList<>();
//
//        users.add(new User("linus@gmail.com", "linus", 2, "Linus"));
//        users.add(new User("luc@gmail.com", "luc", 3, "Luc"));
//        users.add(new User("anatoli@gmail.com", "anatoli", 1, "Anatoli"));
//        users.add(new User("sonam@gmail.com", "sonam", 4, "Sonam"));
//
//    }
//
//    public String validateUser(String userName, String password) {
//        String isUserValid = "userNotValid";
//        for (User user : users) {
//            if ((user.getEmail()).equals(userName) && (user.getPassword()).equals(password)) {
//               isUserValid = "userValid";
//               break;
//            }
//
//        }
//       return isUserValid;
//
//    }
//
//    public int getScore(String userName){
//        int score = 0;
//        for (User user : users) {
//            if ((user.getEmail()).equals(userName)) {
//                score = user.getScore();
//            }
//        }
//        return score;
//    }
//
//    public void addUser(String userName, String password, String name) {
//        users.add(new User(userName,password,0, name));
//    }
//
//    public User getUser(String userId){
//        for (User user : users) {
//            if (user.getEmail().equals(userId)) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public User getUserByName(String name){
//        for (User user : users) {
//            if (user.getName().equals(name)) {
//                return user;
//            }
//        }
//        return null;
//    }
//
//    public List<User> getUsers(int page, int pageSize) {
//        int from = Math.max(0,page*pageSize);
//        int to = Math.min(users.size(),(page+1)*pageSize);
//
//        return users.subList(from, to);
//    }
//}
