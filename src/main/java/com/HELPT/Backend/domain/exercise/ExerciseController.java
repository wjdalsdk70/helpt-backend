package com.HELPT.Backend.domain.exercise;

import com.HELPT.Backend.domain.exercise.dto.ExerciseRequestDto;
import com.HELPT.Backend.domain.exercise.dto.ExerciseResponseDto;
import com.HELPT.Backend.domain.exercise.service.ExerciseService;
import com.HELPT.Backend.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final S3Uploader s3Uploader;
    private final ExerciseService exerciseService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ExerciseResponseDto> uploadDescription(
            @RequestPart("description") ExerciseRequestDto exerciseRequestDto,
            @RequestPart("topImage") MultipartFile topImage
    ) {
        String uploadTopURL;
        try {
            uploadTopURL = s3Uploader.upload(topImage, "exerciseFile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(exerciseService.uploadExercise(exerciseRequestDto,uploadTopURL));
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseResponseDto> exerciseDetails(@PathVariable Long exerciseId) {
        return ResponseEntity.ok(exerciseService.findExercise(exerciseId));
    }
}
