package com.cpe.springboot.producer.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Store{

    private int itemId;
    private String itemName;
    private int itemQuantity;
}