package trivia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/signup")
    public String userSignUp(Model model, HttpSession session, @RequestParam String name, @RequestParam String email, @RequestParam String password,@RequestParam String password_confirmation){
        if (!password.equals(password_confirmation)){
            session.setAttribute("message","Password do not match");
            return "register";
        }

        else if (email!=null && password!=null){
            User user = new User (email, name, password, false);
            Score score = new Score(user);
            usersRepository.save(user);
            scoreRepository.save(score);
            model.addAttribute("name","Thank you for creating the account "+name+"! Log in to start you Trivia Game.");
            return "login";
        } else

            return "register";
    }

    @PostMapping("/")
    public String user(Model model, HttpSession session, @RequestParam String inputEmail, @RequestParam String inputPassword){


        //test if this will add another user with same info
        if (inputPassword.equals(usersRepository.findByEmail(inputEmail).getPassword())) {
            User user = usersRepository.findByEmail(inputEmail);
            user.setOnline(true);
            usersRepository.save(user);
            session.setAttribute("id", user.getId());
//            session.setAttribute("score",user.getScore());
//            session.setAttribute("score", );
            return "chatbox";
        }
        else {
            model.addAttribute("message","Invalid user Id or password");
            return "login";
        }
    }

    @GetMapping("/chat")
    public String chat(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "chatbox";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "profilepage";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        long id = (long) session.getAttribute("id");
        User user = (User) usersRepository.findById(id).get();
        user.setOnline(false);
        Score score = scoreRepository.findByUserId(id);
        score.setCurrentScore(0);
        scoreRepository.save(score);
        usersRepository.save(user);
        session.invalidate();
        return "login";
    }

}
