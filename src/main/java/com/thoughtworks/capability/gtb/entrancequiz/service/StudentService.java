package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Team;
import com.thoughtworks.capability.gtb.entrancequiz.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private List<Student> students;

    private List<Team> teams;

    public StudentService() {
        this.students = new ArrayList<>();
        this.teams = new ArrayList<>();
        initStudents();
    }

    public void addStudent(String name) {
        int id = students.size() + 1;
        students.add(new Student(id, name));
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Team> getTeams() {
        return teams;
    }

    private void initStudents() {
        String studentJson = JsonUtil.loadTestSuiteResource("students.json");
        students = JsonUtil.unmarshal(studentJson, new TypeReference<List<Student>>() {});
    }
}
