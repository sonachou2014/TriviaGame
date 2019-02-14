package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
public class MessageController {
    @MessageMapping("/msg")
    @SendTo("/topic/main")
    public Message message(Message message) throws Exception {
        //String name = (String)session.getAttribute("username");
        //System.out.println(name);
        Thread.sleep(1000); // simulated delay
        return new Message(HtmlUtils.htmlEscape(message.getName()), HtmlUtils.htmlEscape(message.getText()));
    }
}
