package ru.tentech.userservice.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

    public class CreateStudentRequest{
            @NotBlank(message = "Имя не может быть пустым")
            String fullName;

            @NotBlank(message = "Email не может быть пустым")
            @Email(message = "Некорректный формат email")
            String email;

            @NotBlank(message = "Пароль не может быть пустым")
            @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
            String password;

            @NotBlank(message = "Имя группы обязательно для студента")
            String groupName;


}
