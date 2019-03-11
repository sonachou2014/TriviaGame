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
    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/scoreboard")
    public List scoreboard() {
        List<User> onlineUsers = usersRepository.findByIsOnlineTrue();
//        List<User> scoreb = (List)scoreRepository.findAll();

        List<UserScore> userscore = new ArrayList<>();

        for (User user : onlineUsers) {
            Score score = scoreRepository.findByUserId(user.getId());
            userscore.add(new UserScore(user.getName(), score.getCurrentScore(), score.getTotalScore()));
        }
        return userscore;
    }
}
