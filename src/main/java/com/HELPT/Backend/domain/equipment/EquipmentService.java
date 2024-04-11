package com.HELPT.Backend.domain.equipment;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

//    public Response create(Request request){
//        equipmentRepository.save();
//
//    }
}
