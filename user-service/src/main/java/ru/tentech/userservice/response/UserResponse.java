package ru.tentech.userservice.response;

import java.time.LocalDateTime;

public class UserResponse {
    Long id;
    String fullName;
    String email;
    String groupName;
    LocalDateTime registeredAt;
    String role;
    String status;
}

