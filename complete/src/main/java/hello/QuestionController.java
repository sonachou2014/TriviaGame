package hello;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@Controller
public class QuestionController {

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedRate = 10000)
    public void greeting() throws InterruptedException {
        Thread.sleep(1000);

        final String uri = "https://opentdb.com/api.php?amount=1";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JSONObject obj = new JSONObject(result);
        String question = obj.getJSONArray("results").getJSONObject(0).getString("question");

        this.template.convertAndSend("/topic/main", "{\"name\":\"Trivia\",\"text\":\"" + question + "\"}");
    }
}
