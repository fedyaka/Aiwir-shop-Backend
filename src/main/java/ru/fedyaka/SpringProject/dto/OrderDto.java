package ru.fedyaka.SpringProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    String name;
    String surname;
    String phoneNumber;
    String address;
    String description;
    Map<Long, Integer> cart;
}
