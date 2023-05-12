package com.example.mualakabashi_simplenewsapi.repository;




import com.example.mualakabashi_simplenewsapi.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Integer> {
    Optional<News> findById(Integer id);
}
