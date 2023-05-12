package com.example.mualakabashi_simplenewsapi.utils;


import com.example.mualakabashi_simplenewsapi.dto.newsDto.NewsDto;
import com.example.mualakabashi_simplenewsapi.models.News;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface NewsResponseMapper extends Convertable<NewsDto, News>{
}
