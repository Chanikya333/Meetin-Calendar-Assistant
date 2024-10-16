package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/freeslots")
public class FreeSlotController {

    @Autowired
    private FreeSlotService freeSlotService;

    @GetMapping("/find")
    public List<LocalDateTime> findFreeSlots(
            @RequestParam Long emp1Id, 
            @RequestParam Long emp2Id, 
            @RequestParam long durationMinutes) {
        
        Duration duration = Duration.ofMinutes(durationMinutes);
        return freeSlotService.findCommonFreeSlots(emp1Id, emp2Id, duration);
    }
}
