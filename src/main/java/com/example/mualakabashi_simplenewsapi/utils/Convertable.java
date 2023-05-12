package com.example.mualakabashi_simplenewsapi.utils;

public interface Convertable<E, D> {
    D entityToDto(E entity);

    E dtoToEntity(D dto);
}

