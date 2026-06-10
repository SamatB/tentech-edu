package ru.tentech.userservice;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

     @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String groupName;

    @Column(nullable = false)
    private LocalDateTime registeredAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;

    @PrePersist
    public void prePersist() {
        this.registeredAt = LocalDateTime.now();
    }


    public void setUsername(String username) {

    }

    public void setName(@NotBlank(message = "Имя не может быть пустое") String name) {
    }


}
