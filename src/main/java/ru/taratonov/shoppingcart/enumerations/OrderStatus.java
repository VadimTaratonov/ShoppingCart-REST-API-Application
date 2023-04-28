package ru.taratonov.shoppingcart.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum OrderStatus {
    CREATED("created"),
    PROCESSING("processing"),
    CANCELLED("cancelled"),
    SHIPPED("shipped"),
    UNKNOWN("unknown");
    @JsonCreator
    static OrderStatus findValue(String findValue){
        return Arrays.stream(OrderStatus.values())
                .filter(value->value.name().equals(findValue))
                .findFirst()
                .orElse(OrderStatus.UNKNOWN);
    }

    private String title;

    OrderStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
