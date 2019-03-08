package trivia;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;


@Controller
@EnableScheduling
public class MessageController {
    private String correctAnswer = null;
    Boolean isAnswered = true;
    int counter = 0;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ScoresRepository scoresRepository;

    @MessageMapping("/msg")
    @SendTo("/topic/main")
    public void message(Message message) throws Exception {
        Thread.sleep(100);
        String name = usersRepository.findById(Long.valueOf(message.getName())).get().getName();
        this.template.convertAndSend("/topic/main", "{\"name\":\"" +  name + "\",\"text\":\"" + message.getText() + "\"}");


        if (message.getText().equalsIgnoreCase(correctAnswer)){
            User user = usersRepository.findById(Long.valueOf(message.getName())).get();
            user.setScores(new Scores(user.getScores().getTotal_score()+1, user.getScores().getCurrent_score()+1));
            usersRepository.save(user);
            scoresRepository.save(user.getScores());

            isAnswered = true;
            this.template.convertAndSend("/topic/main", "{\"name\":\"Trivia\",\"text\":\"correct!\"}");
        }


    }

    @Scheduled(fixedRate = 1000)
    public void greeting() throws InterruptedException {
        int ANSWER_TIME = 20;
        if(isAnswered || counter == ANSWER_TIME) {
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
            if (counter == ANSWER_TIME / 2) {
                this.template.convertAndSend("/topic/main", "{\"name\":\"Trivia\",\"text\":\"" + ANSWER_TIME / 2 + " seconds left\"}");
            }
            counter++;
        }
    }
}
