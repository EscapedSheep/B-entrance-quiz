package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.thoughtworks.capability.gtb.entrancequiz.domain.ChangeTeamNameRequest;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Team;
import com.thoughtworks.capability.gtb.entrancequiz.exception.TeamNameConflictException;
import com.thoughtworks.capability.gtb.entrancequiz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:1234")
public class StudentApi {

    private StudentService studentService;

    @Autowired
    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudentList() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    @GetMapping("/students/team")
    public ResponseEntity<List<Team>> getTeam() {
        return ResponseEntity.ok(studentService.getTeams());
    }

    @GetMapping("/students/group")
    public ResponseEntity<List<Team>> getGroupTeam() {
        return ResponseEntity.ok(studentService.getGroupStudent());
    }

    @PostMapping("/students")
    public ResponseEntity<List<Student>> addStudent(@RequestBody String name) {
        studentService.addStudent(name);
        return ResponseEntity.created(null).body(studentService.getStudents());
    }

    @PostMapping("/students/team")
    public ResponseEntity<List<Team>> changeTeamName(@RequestBody ChangeTeamNameRequest request) {
        studentService.changeTeamName(request);
        return ResponseEntity.ok(studentService.getTeams());
    }

    @ExceptionHandler({TeamNameConflictException.class})
    public ResponseEntity teamNameConflictHandler() {
        return ResponseEntity.status(409).build();
    }
}
