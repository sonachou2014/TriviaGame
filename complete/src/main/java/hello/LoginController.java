package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String verifyLogin(@RequestParam String username, HttpSession session) {
        session.setAttribute("username", username);
        return "login";
    }
    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }
}
