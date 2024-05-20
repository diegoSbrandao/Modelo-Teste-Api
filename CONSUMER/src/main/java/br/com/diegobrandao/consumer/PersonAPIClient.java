package br.com.diegobrandao.consumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class PersonAPIClient {

    private final String baseUrl;

    public PersonAPIClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Person getPersonById(long id) {
        String url = baseUrl + "/api/" + id; // Modificar a URL para incluir o ID

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Fazer uma chamada GET para a API
            ResponseEntity<Person> response = restTemplate.getForEntity(url, Person.class);

            // Verificar se a resposta foi bem-sucedida
            if (response.getStatusCode() == HttpStatus.OK) {
                // Se sim, retornar o objeto Person
                return response.getBody();
            }
        } catch (HttpClientErrorException.NotFound ex) {
            // Se a pessoa não for encontrada, lançar uma exceção ou retornar null
            return null;
        }

        return null;
    }

    public static void main(String[] args) {
        String baseUrl = "http://localhost:8000"; // URL da sua API
        PersonAPIClient client = new PersonAPIClient(baseUrl);

        // Exemplo de uso: buscar uma pessoa pelo ID
        long id = 1;
        Person result = client.getPersonById(id);
        if (result != null) {
            System.out.println("ID: " + result.getId() + ", Name: " + result.getName() + ", Company: " + result.getCompany());
        } else {
            System.out.println("Pessoa não encontrada");
        }
    }
}