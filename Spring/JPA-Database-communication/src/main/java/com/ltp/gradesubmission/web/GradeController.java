package com.ltp.gradesubmission.web;

import com.ltp.gradesubmission.entity.Grade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/grade")
public class GradeController {
    
    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId
            ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
