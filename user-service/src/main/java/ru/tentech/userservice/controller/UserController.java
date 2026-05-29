package ru.tentech.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import request.CreateAdminRequest;
import request.CreateStudentRequest;
import request.UpdateUserRequest;

import response.UserResponse;
import response.UserShortResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createStudent(
            @RequestBody CreateStudentRequest request
    ) {

        return new UserResponse();
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createAdmin(
            @RequestBody CreateAdminRequest request
    ) {

        return new UserResponse();
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {

        return new UserResponse();
    }

    @GetMapping
    public List<UserShortResponse> getAllUsers() {

        return List.of();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(
            @PathVariable Long id
    ) {

        return new UserResponse();
    }

    @PatchMapping("/{id}/status")
    public String updateUserStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {

        return "Status updated";
    }
}
