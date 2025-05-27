package flpproject.akinator.dto;

public class AddedQuestionDTO {
    private Long sessionId;
    private String text;

    public Long getSessionId() {
        return sessionId;
    }
    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
