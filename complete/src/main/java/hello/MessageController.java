package hello;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.HtmlUtils;


@Controller
@EnableScheduling
public class MessageController {
    private String correctAnswer = null;
    Boolean isAnswered = true;
    int counter = 0;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private UserRepo repository;

    @MessageMapping("/msg")
    @SendTo("/topic/main")
    public void message(Message message) throws Exception {
        Thread.sleep(100);
        this.template.convertAndSend("/topic/main", "{\"name\":\"" +message.getName() + "\",\"text\":\"" + message.getText() + "\"}");
        if (message.getText().equals(correctAnswer)){
            User user = repository.getUserByName(message.getName());
            user.setScore(user.getScore() + 1);
            isAnswered = true;
            this.template.convertAndSend("/topic/main", "{\"name\":\"Trivia\",\"text\":\"correct!\"}");
        }
    }

    @Scheduled(fixedRate = 1000)
    public void greeting() throws InterruptedException {
//        Thread.sleep(100);
        if(isAnswered || counter == 20) {
            final String uri = "https://opentdb.com/api.php?amount=1";

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);
            JSONObject obj = new JSONObject(result);
            String question = obj.getJSONArray("results").getJSONObject(0).getString("question");
            String answer = obj.getJSONArray("results").getJSONObject(0).getString("correct_answer");

            correctAnswer = answer;
            this.template.convertAndSend("/topic/main", "{\"name\":\"Trivia\",\"text\":\"" + question + " (" + answer + ")" + "\"}");
            isAnswered = false;
            counter = 0;
        } else {
            counter++;
        }
    }
}
