package com.example.mualakabashi_simplenewsapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleEmailRequest {
    @NonNull
    private String email;

    @NonNull
    private String subject;

    @NonNull
    private String body;

    @NonNull
    private LocalDateTime dateTime;

    @NonNull
    private ZoneId timeZone;
}
