package com.example.mualakabashi_simplenewsapi.dto.newsDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {

    private Integer id;

    private String title;

    private String description;

    private LocalDate publicationDate;

    private String language;

    private String category;

}
