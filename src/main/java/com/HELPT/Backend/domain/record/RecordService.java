package com.HELPT.Backend.domain.record;

import com.HELPT.Backend.domain.product.Product;
import com.HELPT.Backend.domain.product.ProductRepository;
import com.HELPT.Backend.domain.product.dto.ProductResponse;
import com.HELPT.Backend.domain.record.dto.RecordRequest;
import com.HELPT.Backend.domain.record.dto.RecordResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordResponse saveRecord(Long userId, RecordRequest recordRequest) {

        Record saveRecord = Record.builder()
                .userId(userId)
                .equipmentId(recordRequest.getEquipmentId())
                .count(recordRequest.getCount())
                .setNumber(recordRequest.getSetNumber())
                .weight(recordRequest.getWeight())
                .recordDate(recordRequest.getRecordDate())
                .successRate(recordRequest.getSuccessRate())
                .build();

        recordRepository.save(saveRecord);


        return new RecordResponse(saveRecord);
    }
}
