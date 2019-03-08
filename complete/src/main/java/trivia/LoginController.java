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
   private ScoresRepository scoresRepository;

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
            User user = new User (name, email, password, true);
            Scores scores = new Scores(0,0);

            scoresRepository.save(scores);
            user.setScores(scores);
            usersRepository.save(user);
            user.getScores().setUser(user);
            scoresRepository.save(user.getScores());

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
            if (user.getScores()!=null){
                session.setAttribute("score",user.getScores().getCurrent_score());
            }

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
        usersRepository.save(user);
        session.invalidate();
        return "login";
    }

}
