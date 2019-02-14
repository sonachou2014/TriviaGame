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

    @PostMapping("/task2")
    public String user(Model model, HttpSession session, @RequestParam String inputEmail, @RequestParam String inputPassword){
        if (userRepo.validateUser(inputEmail,inputPassword).equals("userValid")){
            session.setAttribute("username",inputEmail);
            session.setAttribute("score",userRepo.getScore(inputEmail));
            return "afterLogin";
        }
        else {
            model.addAttribute("message","Invalid user Id or password");
            return "login";
        }


    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
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
            userRepo.addUser(Email,Password);
            model.addAttribute("name","Thank you for creating the account "+Firstname+"!!!!Login to start you Trivia Game");
            return "login";
        } else

        return "register";
    }

}
