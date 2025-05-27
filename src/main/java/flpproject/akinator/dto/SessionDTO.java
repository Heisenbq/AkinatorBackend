package flpproject.akinator.dto;

import java.time.LocalDateTime;

public class SessionDTO {
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public SessionDTO() {}

    public SessionDTO(Long userId, LocalDateTime startTime, LocalDateTime endTime) {
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
