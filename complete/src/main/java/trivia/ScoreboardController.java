package trivia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScoreboardController {

    @Autowired
    private UserRepo repository;

    @GetMapping("/scoreboard")
    public List scoreboard(@RequestParam(required=false, defaultValue="0") int page, @RequestParam(required=false, defaultValue="20") int size) {
        List<User> list = repository.getUsers(page, size);
        List<User> onlineUsers = new ArrayList<>();

        for(User user : list) {
            if (user.isOnline()) {
                onlineUsers.add(user);
            }
        }

        return onlineUsers;
    }

    @GetMapping("/changeScore")
    public void changeScore(@RequestParam String userId) {
        System.out.println(userId);
        User user = repository.getUser(userId);
        user.setScore(user.getScore() + 1);
    }
}
