package flpproject.akinator.dto;

public class AliasGameDTO {
    private Long userId;
    private Long sessionId;
    private String answers;

    private String gameName;



    public AliasGameDTO() {}

    public AliasGameDTO(Long userId, Long sessionId, String answers, String gameName) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.answers = answers;
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSessionId() {
        return sessionId;
    }
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getAnswers() {
        return answers;
    }
    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
