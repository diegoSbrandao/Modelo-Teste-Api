package com.example.demo.service;

import com.example.demo.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PersonService {


    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AtomicLong idCounter = new AtomicLong();

    private static final String FILE_PATH = "/Users/diego/OneDrive/Área de Trabalho/API-EXTERNA/PRODUCER/src/main/java/com/example/demo/file/info.json";


    public Person createPersonInFile(Person person) {
        try {
            // Ler o arquivo JSON e converter para uma lista de Persons
            List<Person> persons = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Person>>() {});

            // Encontrar o maior ID existente na lista
            Optional<Long> maxId = persons.stream()
                    .map(Person::getId)
                    .max(Long::compare);

            // Atribuir um ID único ao novo Person
            long newId = maxId.orElse(0L) + 1;
            person.setId(newId);

            // Adicionar o novo Person à lista
            persons.add(person);

            // Salvar a lista atualizada de volta no arquivo JSON
            objectMapper.writeValue(new File(FILE_PATH), persons);

            return person;
        } catch (IOException e) {
            throw new RuntimeException("Failed to create person in file", e);
        }
    }

    public Person getPersonByIdFromFile(long id) {
        try {
            // Ler o arquivo JSON e converter para uma lista de Persons
            List<Person> persons = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Person>>() {});

            // Encontrar a pessoa com o ID correspondente
            Optional<Person> optionalPerson = persons.stream()
                    .filter(person -> person.getId() == id)
                    .findFirst();

            // Retornar a pessoa se encontrada, caso contrário, retornar null
            return optionalPerson.orElse(null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read person from file", e);
        }
    }
}