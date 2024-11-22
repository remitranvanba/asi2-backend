package com.cpe.springboot.card.repository;

import com.cpe.springboot.user.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import com.cpe.springboot.card.model.CardModel;

import java.util.List;
import java.util.Optional;

public interface CardModelRepository extends CrudRepository<CardModel, Integer> {
    List<CardModel> findByUser(UserModel u);

    List<CardModel> findAllByUserId(Integer userId);
}
