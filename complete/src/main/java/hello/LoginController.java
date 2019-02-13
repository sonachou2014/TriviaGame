package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginForm() {
        return "login";
    }
    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
