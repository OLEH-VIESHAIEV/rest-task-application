package test.resttaskapplication.dto.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TaskRequestDto {
    private String name;
    private String description;
    private LocalDate dateOfCreation;
    private Long columnId;
}
