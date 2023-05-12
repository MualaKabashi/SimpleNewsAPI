package com.example.mualakabashi_simplenewsapi.utils;


import com.example.mualakabashi_simplenewsapi.configuration.JwtService;
import com.example.mualakabashi_simplenewsapi.job.EmailJob;
import com.example.mualakabashi_simplenewsapi.models.ScheduleEmailRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.quartz.*;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class Helpers {
    public static String getEmailFromToken(HttpServletRequest request, JwtService jwtService) {
        String authToken = request.getHeader("Authorization");
        String token = authToken.substring("Bearer ".length());
        return jwtService.extractEmail(token);
    }

    public static JobDetail buildJobDetail(ScheduleEmailRequest scheduleEmailRequest) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", scheduleEmailRequest.getEmail());
        jobDataMap.put("subject", scheduleEmailRequest.getSubject());
        jobDataMap.put("body", scheduleEmailRequest.getBody());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public static Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
