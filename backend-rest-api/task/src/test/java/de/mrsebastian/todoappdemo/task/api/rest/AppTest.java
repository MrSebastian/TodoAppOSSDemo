package de.mrsebastian.todoappdemo.task.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskAssigneeDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskCreateDTO;
import de.mrsebastian.todoappdemo.task.api.rest.model.TaskUpdateDTO;
import de.mrsebastian.todoappdemo.task.api.rest.util.TestApplication;
import java.time.LocalDate;
import java.util.UUID;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class AppTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskAPI taskAPIMock;

    @InjectMocks
    private TaskAPIController taskAPIController;

    @Nested
    class VerifyDefaultPaths {
        @Test
        void getTasks() throws Exception {
            val request = MockMvcRequestBuilders.get("/api/tasks");
            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            Mockito.verify(taskAPIMock).getTasks();
        }

        @Test
        void createTask() throws Exception {
            val requestBody = new TaskCreateDTO("title", "description", LocalDate.now(), UUID.randomUUID());
            val content = MockMvcRequestBuilders.post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody));

            val result = mockMvc.perform(content).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
            Mockito.verify(taskAPIMock).createTask(requestBody);
        }

        @Test
        void deleteTask() throws Exception {
            val taskId = UUID.randomUUID();

            val result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/tasks/" + taskId)).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
            Mockito.verify(taskAPIMock).deleteTask(taskId);
        }

        @Test
        void updateTask() throws Exception {
            val taskId = UUID.randomUUID();
            val updateTaskData = new TaskUpdateDTO("New Title", "New Description", LocalDate.now());
            val request = MockMvcRequestBuilders.put("/api/tasks/" + taskId).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateTaskData));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
            Mockito.verify(taskAPIMock).updateTask(taskId, updateTaskData);
        }

        @Test
        void updateAssignee() throws Exception {
            val taskId = UUID.randomUUID();
            val personId = UUID.randomUUID();
            val taskAssigneeDto = new TaskAssigneeDTO(personId);
            val request = MockMvcRequestBuilders.put("/api/tasks/" + taskId + "/assignee").contentType(MediaType.APPLICATION_JSON).content(
                    objectMapper.writeValueAsString(taskAssigneeDto));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
            Mockito.verify(taskAPIMock).updateAssignee(taskId, taskAssigneeDto);
        }

        @Test
        void removeAssignee() throws Exception {
            val taskId = UUID.randomUUID();
            val request = MockMvcRequestBuilders.delete("/api/tasks/" + taskId + "/assignee");

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
            Mockito.verify(taskAPIMock).removeAssignee(taskId);
        }
    }

    @Nested
    class RequestBodyAreValidated {
        @Test
        void createTask() throws Exception {
            val requestBody = new TaskCreateDTO(null, null, null, null);
            val request = MockMvcRequestBuilders.post("/api/tasks").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
            Mockito.verify(taskAPIMock, Mockito.times(0)).createTask(Mockito.any());
        }

        @Test
        void updateTask() throws Exception {
            val taskId = UUID.randomUUID();
            val updateTaskData = new TaskUpdateDTO(null, null, null);
            val request = MockMvcRequestBuilders.put("/api/tasks/" + taskId).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(updateTaskData));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
            Mockito.verify(taskAPIMock, Mockito.times(0)).updateTask(Mockito.any(), Mockito.any());
        }

        @Test
        void updateAssignee() throws Exception {
            val taskId = UUID.randomUUID();
            val taskAssigneeDto = new TaskAssigneeDTO(null);
            val request = MockMvcRequestBuilders.put("/api/tasks/" + taskId).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(taskAssigneeDto));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
            Mockito.verify(taskAPIMock, Mockito.times(0)).updateAssignee(Mockito.any(), Mockito.any());
        }
    }
}
