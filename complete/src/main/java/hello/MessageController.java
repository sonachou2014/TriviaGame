package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;


@Controller
public class MessageController {
    @MessageMapping("/msg")
    @SendTo("/topic/main")
    public Message message(Message message) throws Exception {
        Thread.sleep(1000);
        return new Message(HtmlUtils.htmlEscape(message.getName()), HtmlUtils.htmlEscape(message.getText()));
    }
}
