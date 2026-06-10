package ru.tentech.userservice.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String fullName;
    private String email;
    private String groupName;
    private LocalDateTime registeredAt;
    private String role;
    private String status;

    public void setId(UUID id) {
    }
}