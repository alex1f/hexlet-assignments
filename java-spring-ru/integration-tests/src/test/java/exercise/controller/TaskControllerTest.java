package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

// END
@SpringBootTest
@AutoConfigureMockMvc
// BEGIN
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testCreate() throws Exception {
        Task task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> "task1")
                .supply(Select.field(Task::getDescription), () -> "study Java")
                .create();

        MockHttpServletRequestBuilder request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("task1")))
                .andExpect(jsonPath("$.description", is("study Java")));
    }

    @Test
    public void testShow() throws Exception {
        Task task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> "task1")
                .supply(Select.field(Task::getDescription), () -> "study Java")
                .create();

        task = taskRepository.save(task);

        MockHttpServletRequestBuilder request = get("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("task1")))
                .andExpect(jsonPath("$.description", is("study Java")));
    }

    @Test
    public void testUpdate() throws Exception {
        Task task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> "task1")
                .supply(Select.field(Task::getDescription), () -> "study Java")
                .create();

        task = taskRepository.save(task);

        Map<String, String> updatedInfo = new HashMap<>();
        updatedInfo.put("title", "task1");
        updatedInfo.put("description", "explore jSON");

        MockHttpServletRequestBuilder request = put("/tasks/" + task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updatedInfo));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("task1")))
                .andExpect(jsonPath("$.description", is("explore jSON")));
    }

    @Test
    public void testDelete() throws Exception {
        Task task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .ignore(Select.field(Task::getCreatedAt))
                .ignore(Select.field(Task::getUpdatedAt))
                .supply(Select.field(Task::getTitle), () -> "task1")
                .supply(Select.field(Task::getDescription), () -> "study Java")
                .create();

        task = taskRepository.save(task);

        MockHttpServletRequestBuilder request = delete("/tasks/" + task.getId());

        mockMvc.perform(request)
                .andExpect(status().isOk());
        assertThat(taskRepository.findById(task.getId()).isEmpty());
    }
}
    // END
