package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public boolean bookMeeting(Long ownerId, LocalDateTime startTime, Duration duration) {
        Employee owner = employeeRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Employee not found"));

        List<Meeting> meetings = meetingRepository.findByOwner(owner);
        if (isSlotAvailable(meetings, startTime, duration)) {
            Meeting meeting = new Meeting();
            meeting.setOwner(owner);
            meeting.setStartTime(startTime);
            meeting.setEndTime(startTime.plus(duration));
            meetingRepository.save(meeting);
            return true;
        }
        return false;
    }


    private boolean isSlotAvailable(List<Meeting> meetings, LocalDateTime startTime, Duration duration) {
        for (Meeting meeting : meetings) {
            if (startTime.isBefore(meeting.getEndTime()) && startTime.plus(duration).isAfter(meeting.getStartTime())) {
                return false; 
            }
        }
        return true;
    }
}
