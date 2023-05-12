package com.example.mualakabashi_simplenewsapi.services;


import com.example.mualakabashi_simplenewsapi.configuration.JwtService;
import com.example.mualakabashi_simplenewsapi.dto.userDto.AuthenticationRequestDto;
import com.example.mualakabashi_simplenewsapi.dto.userDto.AuthenticationResponseDto;
import com.example.mualakabashi_simplenewsapi.dto.userDto.LoginRequestDto;
import com.example.mualakabashi_simplenewsapi.dto.userDto.ResetPasswordDto;
import com.example.mualakabashi_simplenewsapi.exceptions.*;
import com.example.mualakabashi_simplenewsapi.models.User;
import com.example.mualakabashi_simplenewsapi.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.mualakabashi_simplenewsapi.utils.Helpers.getEmailFromToken;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;


    public AuthenticationResponseDto register(AuthenticationRequestDto dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new BadRequestException(ExceptionType.USER_ALREADY_EXISTS.getValue());
        var user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .firstName(dto.getFirstName())
                .role(dto.getRole())
                .verificationCode(RandomString.make(64))
                .build();
        user = userRepository.save(user);
        sendRegistrationEmail(user.getEmail());
        return AuthenticationResponseDto.builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .build();
    }


    public AuthenticationResponseDto login(LoginRequestDto dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        var user = userRepository.findByEmail(dto.getEmail()).orElse(null);
        if (user == null)
            throw new NotFoundException(ExceptionType.USER_DOES_NOT_EXIST.getValue());
        return AuthenticationResponseDto.builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .build();
    }

    private void sendRegistrationEmail(String email) {
        mailService.sendEmail(email, "Succesfully registered!", "Welcome to our platform!");
    }


    public String forgetPassword(HttpServletRequest request, @RequestParam String email) {
        if (email.isEmpty()) throw new BadRequestException(ExceptionType.EMPTY_EMAIL_FIELD.getValue());
        String emailFromToken = getEmailFromToken(request, jwtService);
        if (emailFromToken.isEmpty())
            throw new InvalidTokenException(ExceptionType.BAD_REQUEST.getValue());
        if (!email.equals(emailFromToken))
            throw new InvalidTokenException(ExceptionType.BAD_REQUEST.getValue());
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            throw new NotFoundException(ExceptionType.ENTITY_NOT_FOUND.getValue());
        String verificationCode = user.getVerificationCode();
        mailService.sendEmail(email, "Password reset email", "Here is your confirmation code:  " + verificationCode);
        return "Check your email , confirmation code is there";
    }


    public String resetPassword(@RequestBody ResetPasswordDto dto) {
        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);
        if (user == null)
            throw new NotFoundException(ExceptionType.ENTITY_NOT_FOUND.getValue());
        if (!user.getVerificationCode().equals(dto.getVerificationCode()))
            throw new WrongVerificationCodeException(ExceptionType.WRONG_CREDENTIALS.getValue());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        mailService.sendEmail(user.getEmail(), "Password reset!", "Password reset succesfully");
        return "Password changed successfully";
    }


}
