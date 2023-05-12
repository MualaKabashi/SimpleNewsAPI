package com.example.mualakabashi_simplenewsapi.controllers;



import com.example.mualakabashi_simplenewsapi.dto.userDto.AuthenticationRequestDto;
import com.example.mualakabashi_simplenewsapi.dto.userDto.AuthenticationResponseDto;
import com.example.mualakabashi_simplenewsapi.dto.userDto.LoginRequestDto;
import com.example.mualakabashi_simplenewsapi.dto.userDto.ResetPasswordDto;
import com.example.mualakabashi_simplenewsapi.models.ResponseModel;
import com.example.mualakabashi_simplenewsapi.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthenticationService service;

    @PostMapping("/login/")
    public ResponseEntity<ResponseModel<AuthenticationResponseDto>> login(@RequestBody LoginRequestDto dto) {
        return ResponseModel.onSuccess(service.login(dto), 200);
    }

    @PostMapping("/register/")
    public ResponseEntity<ResponseModel<AuthenticationResponseDto>> register(@RequestBody AuthenticationRequestDto dto) {
        return ResponseModel.onSuccess(service.register(dto), 200);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<ResponseModel<String>> forgetPassword(HttpServletRequest request, @RequestParam String email) {
        return ResponseModel.onSuccess(service.forgetPassword(request, email), 200);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<ResponseModel<String>> resetPassword(@RequestBody ResetPasswordDto dto) {
        return ResponseModel.onSuccess(service.resetPassword(dto), 200);
    }

}
