package request;

import jakarta.validation.constraints.NotBlank;

public class UpdateUserRequest {

    @NotBlank(message = "Имя не может быть пустым")
    String fullName;

    String groupName;

    String status;
}


