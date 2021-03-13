package com.sinakaev.SpringSecurityTest.rest;

import com.sinakaev.SpringSecurityTest.model.Developer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Rest controller for testing
 *
 * Lombook can help you with Constructor and getters/setters
 *
 * @author Mark Sinakaev
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/developers")
public class DeveloperRestControllerV1 {

    private List<Developer> DEVELOPERS = Stream.of(
            new Developer(1L, "Ivan", "Ivanov"),
            new Developer(2L, "Sergey", "Sergeev"),
            new Developer(3L, "Petr", "Petrov"))
            .collect(Collectors.toList());

    @GetMapping
    public List<Developer> getAll(){
        return DEVELOPERS;
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable Long id){

        //
        return DEVELOPERS.stream().filter(developer -> developer.getId().equals(id))
                .findFirst().orElse(null);
    }
}
/**
 *
 * PostMan помогает сгенерировать Header на
 * основе введенных данных в формате
 * Authorization - basic auth
 *
 * PostMan интегрирует пароль и логин в пакет
 *
 */
