package hello;

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
   private UserRepo userRepo;

    @GetMapping("/")
    public String loginForm() {
        return "login";
    }

//    @PostMapping("/trivia/random")
    @PostMapping("/")
    public String user(Model model, HttpSession session, @RequestParam String inputEmail, @RequestParam String inputPassword){
        if (userRepo.validateUser(inputEmail,inputPassword).equals("userValid")){
            User user = userRepo.getUser(inputEmail);
            session.setAttribute("username", user.getName());
            session.setAttribute("score",user.getScore());
            return "chatbox";
        }
        else {
            model.addAttribute("message","Invalid user Id or password");
            return "login";
        }
    }

    @GetMapping("/chat")
    public String chat() {
        return "chatbox";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/signup")
    public String userSignUp(Model model, HttpSession session,@RequestParam String Firstname,@RequestParam String Email, @RequestParam String Password,@RequestParam String password_confirmation){
        if (!Password.equals(password_confirmation)){
            session.setAttribute("message","Password do not match");
            return "register";
        }

        else if (Email!=null && Password!=null){
            userRepo.addUser(Email,Password, Firstname);
            model.addAttribute("name","Thank you for creating the account "+Firstname+"! Log in to start you Trivia Game.");
            return "login";
        } else

        return "register";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profilepage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate(); // you could also invalidate the whole session, a new session will be created the next request
        return "login";
    }

}
