package ch.kra.gradesubmission.web;

import ch.kra.gradesubmission.entity.Grade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/grade")
public class GradeController {

    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getGrades() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable final Long studentId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable final Long courseId) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId
    ) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId,
            @RequestBody final Grade grade
    ) {
        return new ResponseEntity<>(grade, HttpStatus.CREATED);
    }

    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId,
            @RequestBody final Grade grade
    ) {
        return new ResponseEntity<>(grade, HttpStatus.OK);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId
    ) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
