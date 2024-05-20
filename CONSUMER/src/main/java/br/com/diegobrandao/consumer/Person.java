package br.com.diegobrandao.consumer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Person {
    private long id;
    private String name;
    private double salary;
//    private List<String> company;
    private String company;

}
