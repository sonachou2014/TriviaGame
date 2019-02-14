package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScoreboardController {

//    @Autowired
//    private BookRepository repository;
//
//    @GetMapping("/scoreboard")
//    public List scoreboard() {
//        List list = repository.getUsers();
//        return list;
//    }
//
//    @GetMapping("/changeScore")
//    public void changeScore(@RequestParam int userId) {
//        User user = repository.getUser(id);
//        user.setScore(user.getScore() + 1);
//    }
}
