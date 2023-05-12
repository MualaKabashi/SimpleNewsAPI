package com.example.mualakabashi_simplenewsapi.dto.userDto;


import com.example.mualakabashi_simplenewsapi.models.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequestDto {
    private  String email;
    private  String password;
    private String firstName;

    @Enumerated(EnumType.STRING)
    private Role role;
}
