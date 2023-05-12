package com.example.mualakabashi_simplenewsapi.controllers;

import com.example.mualakabashi_simplenewsapi.models.ResponseModel;
import com.example.mualakabashi_simplenewsapi.models.ScheduleEmailRequest;
import com.example.mualakabashi_simplenewsapi.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/scheduleEmail")
    public ResponseEntity<ResponseModel<String>> scheduleEmail(@RequestBody ScheduleEmailRequest scheduleEmailRequest) {
        return ResponseModel.onSuccess(mailService.scheduleEmail(scheduleEmailRequest), 200);
    }

}
