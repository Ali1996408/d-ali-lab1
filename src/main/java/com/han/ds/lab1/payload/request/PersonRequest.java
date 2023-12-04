package com.han.ds.lab1.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    @NotBlank
    private String name;

    private int age;

    private String address;

    private String work;
}
