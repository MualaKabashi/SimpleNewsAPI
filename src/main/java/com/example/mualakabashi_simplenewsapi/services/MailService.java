package com.example.mualakabashi_simplenewsapi.services;


import com.example.mualakabashi_simplenewsapi.exceptions.BadRequestException;
import com.example.mualakabashi_simplenewsapi.exceptions.ExceptionType;
import com.example.mualakabashi_simplenewsapi.models.ScheduleEmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.quartz.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

import static com.example.mualakabashi_simplenewsapi.utils.Helpers.buildJobDetail;
import static com.example.mualakabashi_simplenewsapi.utils.Helpers.buildJobTrigger;


@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final Scheduler scheduler;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("test@no-reply.com");
        mailSender.send(message);
    }

    @SneakyThrows
    public String scheduleEmail(ScheduleEmailRequest scheduleEmailRequest) {
        ZonedDateTime dateTime = ZonedDateTime.of(scheduleEmailRequest.getDateTime(), scheduleEmailRequest.getTimeZone());
        if (dateTime.isBefore(ZonedDateTime.now()))
            throw new BadRequestException(ExceptionType.INVALID_SCHEDULED_DATE.getValue());
        JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
        Trigger trigger = buildJobTrigger(jobDetail, dateTime);
        scheduler.scheduleJob(jobDetail, trigger);
        return "Email scheduled successfully!";
    }
}
