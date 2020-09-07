package com.thoughtworks.capability.gtb.entrancequiz.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;

    private List<Student> teamStudent;

    public Team(String teamName, List<Student> teamStudent) {
        this.teamName = teamName;
        this.teamStudent = teamStudent;
    }

    public Team(String teamName) {
        this.teamName = teamName;
        this.teamStudent = new ArrayList<>();
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Student> getTeamStudent() {
        return teamStudent;
    }

    public void setTeamStudent(List<Student> teamStudent) {
        this.teamStudent = teamStudent;
    }

    public void addStudent(Student student) {
        teamStudent.add(student);
    }

    public void cleanTeam() {
        teamStudent.clear();
    }
}
