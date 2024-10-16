package com.calendar;


import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MeetingController {

    @Autowired
    private CalendarService calendarService;

    @PostMapping("/book")
    public ResponseEntity<String> bookMeeting(@RequestBody MeetingRequest request) {
        boolean isBooked = calendarService.bookMeeting(request.getOwnerId(), request.getStartTime(), Duration.ofMinutes(request.getDurationMinutes()));
        if (isBooked) {
            return ResponseEntity.ok("Meeting booked successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Time slot not available.");
        }
    }


}

