package com.HELPT.Backend.domain.record;

import com.HELPT.Backend.domain.product.dto.ProductResponse;
import com.HELPT.Backend.domain.record.dto.RecordRequest;
import com.HELPT.Backend.domain.record.dto.RecordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.HELPT.Backend.global.auth.SecurityUtil.getCurrentUserId;

@RequiredArgsConstructor
@RestController
@RequestMapping("/records")
@Slf4j
public class RecordController {

    private final RecordService recordService;

    @PostMapping("")
    public ResponseEntity<RecordResponse> saveRecord(@RequestBody RecordRequest recordRequest) {

        Long userId = getCurrentUserId();

        return ResponseEntity.ok(recordService.saveRecord(userId,recordRequest));
    }

    @GetMapping("/detail/{recordId}")
    public ResponseEntity<RecordResponse> recordDetail(@PathVariable Long recordId) {

        return ResponseEntity.ok(recordService.detail(recordId));
    }

    @GetMapping("/calender")
    public ResponseEntity<List<RecordResponse>> recordList(@RequestParam("date") LocalDate monthDate) {

        Long userId = getCurrentUserId();

        return ResponseEntity.ok(recordService.recordList(userId,monthDate));

    }

    @PostMapping("/calender/members/{memberId}")
    public ResponseEntity<List<RecordResponse>> recordList(@PathVariable Long memberId,
                                                           @RequestParam("date") LocalDate monthDate
                                                           ) {

        return ResponseEntity.ok(recordService.recordList(memberId,monthDate));

    }
}
