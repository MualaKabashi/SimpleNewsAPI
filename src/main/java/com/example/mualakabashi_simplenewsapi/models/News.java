package com.example.mualakabashi_simplenewsapi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private LocalDate publicationDate;

    private String language;

    private String category;

    @JsonBackReference
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "author_id")
    private User author;


}
