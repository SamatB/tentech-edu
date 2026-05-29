package ru.tentech.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import ru.tentech.userservice.request.CreateAdminRequest;
import ru.tentech.userservice.request.CreateStudentRequest;
import ru.tentech.userservice.request.UpdateUserRequest;

import ru.tentech.userservice.response.UserResponse;
import ru.tentech.userservice.response.UserShortResponse;
import ru.tentech.userservice.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createStudent(
            @Valid @RequestBody CreateStudentRequest request
    ){
        return userService.createStudent(request);
    }



    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createAdmin(
            @Valid @RequestBody CreateAdminRequest request
    ) {
        return userService.createAdmin(request);
    }




    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @Valid@RequestBody UpdateUserRequest request
    ) {

        return userService.updateUser(id,request);

    }

    @GetMapping
    public List<UserShortResponse> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(
            @PathVariable Long id
    ) {

        return userService.getUserById(id);
    }

    @PatchMapping("/{id}/status")
    public String updateUserStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {

        return userService.updateUserStatus(id,status);
    }
}
