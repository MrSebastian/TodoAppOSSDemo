package de.mrsebastian.todoappdemo.person.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mrsebastian.todoappdemo.person.api.rest.model.PersonCreateDTO;
import de.mrsebastian.todoappdemo.person.api.rest.model.PersonUpdateDTO;
import de.mrsebastian.todoappdemo.person.api.rest.util.TestApplication;
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
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonAPI personAPI;

    @InjectMocks
    private PersonAPIController personAPIController;

    @Nested
    class VerifyDefaultPaths {

        @Test
        void getPersonen() throws Exception {
            val request = MockMvcRequestBuilders.get("/api/person").contentType(MediaType.APPLICATION_JSON);

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
            Mockito.verify(personAPI).getPersonen();
        }

        @Test
        void createPerson() throws Exception {
            val requestBody = new PersonCreateDTO("firstname", "lastname", "a@b.de");
            val request = MockMvcRequestBuilders.post("/api/person").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
            Mockito.verify(personAPI).createPerson(requestBody);
        }

        @Test
        void deletePerson() throws Exception {
            val personId = UUID.randomUUID();
            val request = MockMvcRequestBuilders.delete("/api/person/" + personId).contentType(MediaType.APPLICATION_JSON);

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
            Mockito.verify(personAPI).deletePerson(personId);
        }

        @Test
        void updatePerson() throws Exception {
            val personId = UUID.randomUUID();
            val requestBody = new PersonUpdateDTO("firstname", "lastname", "a@b.de");
            val request = MockMvcRequestBuilders.put("/api/person/" + personId).contentType(MediaType.APPLICATION_JSON).content(
                    objectMapper.writeValueAsString(requestBody));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
            Mockito.verify(personAPI).updatePerson(personId, requestBody);
        }
    }

    @Nested
    class RequestBodyAreValidated {

        @Test
        void createPerson() throws Exception {
            val requestBody = new PersonCreateDTO(null, null, null);
            val request = MockMvcRequestBuilders.post("/api/person").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
            Mockito.verify(personAPI, Mockito.times(0)).createPerson(Mockito.any());
        }

        @Test
        void updatePerson() throws Exception {
            val personId = UUID.randomUUID();
            val requestBody = new PersonUpdateDTO(null, null, null);

            val request = MockMvcRequestBuilders.put("/api/person/" + personId).contentType(MediaType.APPLICATION_JSON).content(
                    objectMapper.writeValueAsString(requestBody));

            val result = mockMvc.perform(request).andReturn();

            Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
            Mockito.verify(personAPI, Mockito.times(0)).updatePerson(Mockito.any(), Mockito.any());
        }
    }
}
