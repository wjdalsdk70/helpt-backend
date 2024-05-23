package com.HELPT.Backend.domain.exercise;

import com.HELPT.Backend.domain.exercise.dto.ExerciseRequestDto;
import com.HELPT.Backend.domain.gym.dto.GymResistrationRequest;
import com.HELPT.Backend.domain.gym.dto.GymResponse;
import com.HELPT.Backend.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final S3Uploader s3Uploader;
    private final ExerciseService exerciseService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Exercise> uploadDescription(
            @RequestPart("description") ExerciseRequestDto exerciseRequestDto,
            @RequestPart("topImage") MultipartFile topImage,
            @RequestPart("bottomImage") MultipartFile bottomImage
    ) {
        String uploadTopURL;
        String uploadBottomURL;
        try {
            uploadTopURL = s3Uploader.upload(topImage, "exerciseFile");
            uploadBottomURL = s3Uploader.upload(bottomImage, "exerciseFile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(exerciseService.uploadExercise(exerciseRequestDto,uploadTopURL,uploadBottomURL));
    }
}
