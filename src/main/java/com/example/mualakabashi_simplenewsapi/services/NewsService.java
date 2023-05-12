package com.example.mualakabashi_simplenewsapi.services;


import com.example.mualakabashi_simplenewsapi.configuration.JwtService;
import com.example.mualakabashi_simplenewsapi.dto.newsDto.NewsDto;
import com.example.mualakabashi_simplenewsapi.dto.newsDto.NewsRequestDto;
import com.example.mualakabashi_simplenewsapi.exceptions.EmptyBodyException;
import com.example.mualakabashi_simplenewsapi.exceptions.ExceptionType;
import com.example.mualakabashi_simplenewsapi.exceptions.InvalidTokenException;
import com.example.mualakabashi_simplenewsapi.exceptions.NotFoundException;
import com.example.mualakabashi_simplenewsapi.models.News;
import com.example.mualakabashi_simplenewsapi.models.User;
import com.example.mualakabashi_simplenewsapi.repository.NewsRepository;
import com.example.mualakabashi_simplenewsapi.repository.UserRepository;
import com.example.mualakabashi_simplenewsapi.utils.NewsRequestMapper;
import com.example.mualakabashi_simplenewsapi.utils.NewsResponseMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.mualakabashi_simplenewsapi.utils.Helpers.getEmailFromToken;


@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final NewsRequestMapper newsRequestMapper;
    private final NewsResponseMapper newsResponseMapper;

    public List<NewsDto> getNews() {
        List<News> list = newsRepository.findAll();
        if (list.isEmpty()) throw new NotFoundException(ExceptionType.ENTITY_NOT_FOUND.getValue());
        return list.stream().map(newsResponseMapper::dtoToEntity).toList();
    }

    public News getNewsById(int id) {
        News news = newsRepository.findById(id).orElse(null);
        if (news == null)
            throw new NotFoundException(ExceptionType.ENTITY_NOT_FOUND.getValue());
        return news;
    }

    public String addNews(HttpServletRequest request, NewsRequestDto newsRequestDto) {
        String email = getEmailFromToken(request, jwtService);
        if (email.isEmpty())
            throw new InvalidTokenException(ExceptionType.BAD_REQUEST.getValue());
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            throw new NotFoundException(ExceptionType.ENTITY_NOT_FOUND.getValue());
        News news = newsRequestMapper.dtoToEntity(newsRequestDto);
        news.setAuthor(user);
        news.setPublicationDate(LocalDate.now());
        newsRepository.save(news);
        return "Success";

    }

    public String editNews(HttpServletRequest request, NewsRequestDto newsRequestDto, int id) {
        String email = getEmailFromToken(request, jwtService);
        User user = userRepository.findByEmail(email).orElse(null);

        if (newsRequestDto == null)
            throw new EmptyBodyException(ExceptionType.EMPTY_DATA_SET.getValue());
        News news = newsRepository.findById(id).orElse(null);
        if (news == null)
            throw new NotFoundException(ExceptionType.NEWS_NOT_FOUND.getValue());

        news = newsRequestMapper.dtoToEntity(newsRequestDto);
        news.setId(id);
        news.setAuthor(user);
        news.setPublicationDate(LocalDate.now());
        newsRepository.save(news);
        return "News edited successfully!";
    }

    public String deleteNews(int id) {
        News news = getNewsById(id);
        if (news == null)
            throw new EmptyBodyException(ExceptionType.EMPTY_DATA_SET.getValue());
        newsRepository.delete(news);
        return "News Deleted Succesfully";
    }


}
