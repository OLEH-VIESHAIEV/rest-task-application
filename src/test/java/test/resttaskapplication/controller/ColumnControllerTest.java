package test.resttaskapplication.controller;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.willDoNothing;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import test.resttaskapplication.dto.request.ColumnRequestDto;
import test.resttaskapplication.model.Column;
import test.resttaskapplication.service.ColumnService;
import test.resttaskapplication.service.SortService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ColumnControllerTest {
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    @MockBean
    private ColumnService columnService;
    @MockBean
    private SortService sortService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    public void shouldCreateColumn() {
        Column columnToSave = new Column();
        columnToSave.setName("BACKLOG");
        Mockito.when(columnService.save(columnToSave))
                .thenReturn(new Column(4L, "BACKLOG"));

        ColumnRequestDto columnRequestDto = new ColumnRequestDto();
        columnRequestDto.setName(columnToSave.getName());

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(columnRequestDto)
                .when()
                .post("/columns")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(4))
                .body("name", Matchers.equalTo("BACKLOG"));
    }

    @Test
    public void shouldDeleteColumn() throws Exception {
        Column columnToDelete = new Column();
        columnToDelete.setId(2L);
        columnToDelete.setName("DOING");
        willDoNothing().given(columnService).delete(columnToDelete.getId());
        ResultActions response = mockMvc.perform(delete("/columns/{id}", columnToDelete.getId()));
        response.andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateColumnName() throws Exception {
        Column COLUMN_1 = new Column(1L,"TODO");
        Column columnToUpdate = new Column();
        columnToUpdate.setName("updatedName");

        ColumnRequestDto columnRequestDto = new ColumnRequestDto();
        columnRequestDto.setName(columnToUpdate.getName());
        Mockito.when(columnService.getById(COLUMN_1.getId())).thenReturn(COLUMN_1);
        Mockito.when(columnService.save(columnToUpdate))
                .thenReturn(new Column(1L, "updateName"));

        String updatedContent = objectWriter.writeValueAsString(columnToUpdate);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/columns")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updatedContent);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateOrderColumns() {
        List<Column> mockColumnList = List.of(
                new Column(1L,"TODO"),
                new Column(2L,"IN PROGRESS"),
                new Column(3L,"DONE"));
        String sortBy = "name";
        Sort sort = Sort.by(sortService.parseSortedOrders(sortBy));
        PageRequest pageRequest = PageRequest.of(0, 20, sort);

        Mockito.when(columnService.getAll(pageRequest))
                .thenReturn(List.of(
                        new Column(3L,"DONE"),
                        new Column(2L,"IN PROGRESS"),
                        new Column(1L,"TODO")));

        RestAssuredMockMvc
                .given()
                .queryParam("sortBy", sortBy)
                .when()
                .get("/columns")
                .then()
                .statusCode(200)
                .body("[0].id", Matchers.equalTo(3))
                .body("[0].name", Matchers.equalTo("DONE"))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].name", Matchers.equalTo("IN PROGRESS"))
                .body("[2].id", Matchers.equalTo(1))
                .body("[2].name", Matchers.equalTo("TODO"));
    }
}
