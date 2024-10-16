package com.cpe.springboot.card.controller;

import org.springframework.data.repository.CrudRepository;

import com.cpe.springboot.card.model.CardReference;

public interface CardRefRepository extends CrudRepository<CardReference, Integer> {

}
