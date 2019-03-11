package trivia;

public class UserScore {
    private String userName;
    private int currentScore;
    private int totalScore;

    public UserScore(String userName, int currentScore, int totalScore) {
        this.userName = userName;
        this.currentScore = currentScore;
        this.totalScore = totalScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
