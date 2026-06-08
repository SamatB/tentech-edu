package ru.tentech.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tentech.userservice.User;
import ru.tentech.userservice.UserRepository;
import ru.tentech.userservice.UserRole;
import ru.tentech.userservice.UserStatus;
import ru.tentech.userservice.request.CreateStudentRequest;
import ru.tentech.userservice.response.UserResponse;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse createStudent(CreateStudentRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setGroupName(request.getGroupName());

        user.setRole(UserRole.STUDENT);
        user.setStatus(UserStatus.ACTIVE);
        user.setRegisteredAt(LocalDateTime.now());

        User savedUser = UserRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
}
