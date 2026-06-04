package ru.tentech.userservice.service;

import org.springframework.stereotype.Component;
import response.UserResponse;
import response.UserShortResponse;
import ru.tentech.userservice.User;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();

        user(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setGroupName(user.getGroupName());
        response.setRegisteredAt(user.getRegisteredAt());

        if (user.getRole() != null) {
            response.setRole(user.getRole().name());
        }

        if (user.getStatus() != null) {
            response.setStatus(user.getStatus().name());
        }

        return response;
    }

    private void user(UUID id) {
    }

    public UserShortResponse toShortResponse(User user) {
        if (user == null) {
            return null;
        }

        UserShortResponse response = new UserShortResponse();

        user.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setGroupName(user.getGroupName());

        if (user.getRole() != null) {
            response.setRole(user.getRole().name());
        }

        if (user.getStatus() != null) {
            response.setStatus(user.getStatus().name());
        }

        return response;
    }

    public List<UserShortResponse> toShortResponseList(List<User> users) {
        return null;

    }
}