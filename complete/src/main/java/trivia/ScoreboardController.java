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
    private UsersRepository usersRepository;

    @GetMapping("/scoreboard")
    public List scoreboard() {
        List<User> onlineUsers = usersRepository.findByIsOnlineTrue();
        return onlineUsers;
    }

}
