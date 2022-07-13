package test.resttaskapplication.dto.response;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDate dateOfCreation;
    private Long columnId;
}
