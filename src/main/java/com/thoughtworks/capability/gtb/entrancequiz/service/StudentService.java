package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.thoughtworks.capability.gtb.entrancequiz.domain.ChangeTeamNameRequest;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Student;
import com.thoughtworks.capability.gtb.entrancequiz.domain.Team;
import com.thoughtworks.capability.gtb.entrancequiz.exception.TeamNameConflictException;
import com.thoughtworks.capability.gtb.entrancequiz.utils.JsonUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private List<Student> students;

    private List<Team> teams;

    public StudentService() throws IOException {
        initStudents();
        initTeams();
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

    public void changeTeamName(ChangeTeamNameRequest request) {
        checkTeamName(request.getNewName());
        for(Team team : teams) {
            if (team.getTeamName().equals(request.getOldName())) {
                team.setTeamName(request.getNewName());
                break;
            }
        }
    }

    public List<Team> getGroupStudent() {
        for(Team team : teams) {
            team.cleanTeam();
        }
        List<Student> shuffleStudents = new ArrayList<>(students);
        Collections.shuffle(shuffleStudents);
        Iterator<Student> iterator = shuffleStudents.iterator();
        int team = 0;
        while(iterator.hasNext()) {
            teams.get(team).addStudent(iterator.next());
            team = team == 5 ? 0 : team + 1;
        }
        return teams;
    }

    private void checkTeamName(String name) {
        List<Team> filterList = teams.stream().filter(team -> team.getTeamName().equals(name)).collect(Collectors.toList());
        System.out.println(filterList.size());
        if (filterList.size() > 0) {
            throw new TeamNameConflictException("Team name conflict");
        }
    }

    private void initTeams() {
        this.teams = new ArrayList<>();
        for(int i = 1; i <= 6; i++) {
            teams.add(new Team("Team " + i));
        }
    }

    private void initStudents() throws IOException {
        this.students = new ArrayList<>();
        ClassPathResource classPathResource = new ClassPathResource("studentResource/students.json");
        students = JsonUtil.unmarshal(classPathResource.getInputStream(), new TypeReference<List<Student>>() {});
    }
}
