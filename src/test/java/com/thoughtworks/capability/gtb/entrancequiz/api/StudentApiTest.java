package com.thoughtworks.capability.gtb.entrancequiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.entrancequiz.domain.ChangeTeamNameRequest;
import com.thoughtworks.capability.gtb.entrancequiz.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentApiTest {
    @Autowired
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_students_list_when_call_api() throws Exception {
        mockMvc.perform(get("/students")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(35)));
    }

    @Test
    void should_return_team_list_when_call_api() throws Exception {
        mockMvc.perform(get("/students/team")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(6)));
    }

    @Test
    void should_add_student_to_list_given_student_name() throws Exception {
        mockMvc.perform(post("/students").content("newName").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", hasSize(36)))
                .andExpect(jsonPath("$[35].name", is("newName")));
    }

    @Test
    void should_change_team_name_given_request() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        ChangeTeamNameRequest request = new ChangeTeamNameRequest("Team 1", "new Team 1");
        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/students/team").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].teamName", is("new Team 1")));
    }
}