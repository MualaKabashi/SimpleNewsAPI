package com.example.mualakabashi_simplenewsapi.dto.newsDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequestDto {

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String language;

    @NonNull
    private String category;
}
