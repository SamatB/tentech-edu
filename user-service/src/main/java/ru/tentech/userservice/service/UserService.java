package ru.tentech.userservice.service;
import org.springframework.stereotype.Service;
import ru.tentech.userservice.request.CreateAdminRequest;
import ru.tentech.userservice.request.CreateStudentRequest;
import ru.tentech.userservice.request.UpdateUserRequest;
import ru.tentech.userservice.response.UserResponse;
import ru.tentech.userservice.response.UserShortResponse;

import java.util.List;

@Service
public class UserService {

    public UserResponse createStudent(CreateStudentRequest request) {
        return new UserResponse();
    }

    public UserResponse createAdmin(CreateAdminRequest request) {
        return new UserResponse();
    }

    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        return new UserResponse();
    }

    public List<UserShortResponse> getAllUsers() {
        return List.of();
    }

    public UserResponse getUserById(Long id) {
        return new UserResponse();
    }

    public String updateUserStatus(Long id, String status) {
        return "Status updated";
    }
}



