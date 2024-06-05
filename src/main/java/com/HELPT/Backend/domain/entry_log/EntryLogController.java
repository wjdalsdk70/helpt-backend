package com.HELPT.Backend.domain.entry_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entry-logs")
public class EntryLogController {

    @Autowired
    private EntryLogService entryLogService;

    @GetMapping
    public List<EntryLog> getAllEntries() {
        return entryLogService.getAllEntries();
    }
}
