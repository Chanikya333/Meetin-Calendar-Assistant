package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class FreeSlotService {

    @Autowired
    private EmployeeRepository employeeRepository; 

    @Autowired
    private MeetingRepository meetingRepository;

    public List<LocalDateTime> findCommonFreeSlots(Long emp1Id, Long emp2Id, Duration duration) {
        Employee emp1 = employeeRepository.findById(emp1Id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + emp1Id)); 
        Employee emp2 = employeeRepository.findById(emp2Id)
                .orElseThrow(() -> new RuntimeException("Employee not found: " + emp2Id)); 

        List<Meeting> emp1Meetings = meetingRepository.findByOwner(emp1);
        List<Meeting> emp2Meetings = meetingRepository.findByOwner(emp2);

        List<LocalDateTime> freeSlots = new ArrayList<>();

        List<LocalDateTime> busySlots = new ArrayList<>();
        for (Meeting meeting : emp1Meetings) {
            busySlots.add(meeting.getStartTime());
            busySlots.add(meeting.getEndTime());
        }
        for (Meeting meeting : emp2Meetings) {
            busySlots.add(meeting.getStartTime());
            busySlots.add(meeting.getEndTime());
        }

        Collections.sort(busySlots);

        LocalDateTime lastEndTime = LocalDateTime.MIN;
        for (LocalDateTime busySlot : busySlots) {
            if (lastEndTime.plus(duration).isBefore(busySlot)) {
                freeSlots.add(lastEndTime.plus(duration));
            }
            lastEndTime = busySlot.isAfter(lastEndTime) ? busySlot : lastEndTime;
        }

        return freeSlots;
    }
}
