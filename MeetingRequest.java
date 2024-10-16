package com.calendar;

import java.time.LocalDateTime;

public class MeetingRequest {
    private Long ownerId;
    private LocalDateTime startTime;
    private long durationMinutes;

    public Long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public long getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(long durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

    
}
