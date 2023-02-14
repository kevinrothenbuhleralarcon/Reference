package ch.kra.gradesubmission.web;

import ch.kra.gradesubmission.entity.Grade;
import ch.kra.gradesubmission.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    @GetMapping("/all")
    public ResponseEntity<List<Grade>> getGrades() {
        return new ResponseEntity<>(gradeService.getAllGrades(), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getStudentGrades(@PathVariable final Long studentId) {
        return new ResponseEntity<>(gradeService.getStudentGrades(studentId), HttpStatus.OK);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Grade>> getCourseGrades(@PathVariable final Long courseId) {
        return new ResponseEntity<>(gradeService.getCourseGrades(courseId), HttpStatus.OK);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> getGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId
    ) {
        return new ResponseEntity<>(gradeService.getGrade(studentId, courseId), HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> saveGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId,
            @RequestBody final Grade grade
    ) {
        return new ResponseEntity<>(gradeService.saveGrade(grade, studentId, courseId), HttpStatus.CREATED);
    }

    @PutMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<Grade> updateGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId,
            @RequestBody final Grade grade
    ) {
        return new ResponseEntity<>(gradeService.updateGrade(grade.getScore(), studentId, courseId), HttpStatus.OK);
    }

    @DeleteMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<HttpStatus> deleteGrade(
            @PathVariable final Long studentId,
            @PathVariable final Long courseId
    ) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
