package com.han.ds.lab1.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PersonResponse {
    private Long id;
    private String name;
    private int age;
    private String address;
    private String work;
}
