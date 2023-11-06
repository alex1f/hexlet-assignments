package exercise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
public class TaskUpdateDTO {
    @NotNull
    private Long assigneeId;

    @NotBlank
    private JsonNullable<String> title;

    @NotBlank
    private JsonNullable<String> description;
}
