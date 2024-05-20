package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {
    private Long id;
    private String name;
    private String age;
    private Double salary;
    private Boolean company;
//    private List<String> company;
}
