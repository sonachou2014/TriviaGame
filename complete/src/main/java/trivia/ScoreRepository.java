package trivia;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ScoreRepository extends CrudRepository<Score, Long>  {

    Score findByUserId(Long userId);

}
