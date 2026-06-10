package ru.tentech.userservice.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateStudentRequest {

    // Геттеры и сеттеры
    @NotBlank(message = "Username не может быть пустым")
    @Size(min = 3, max = 50, message = "Username должен быть от 3 до 50 символов")
    private String username;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Неверный формат Email")
    private String email;

    @NotBlank(message = "Password не может быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;

    @NotBlank(message = "Group name является обязательным полем") // Останавливает request, если пусто
    private String groupName;

    public @NotBlank(message = "Имя не может быть пустое") String getName() {

        return "";
    }

}

    
