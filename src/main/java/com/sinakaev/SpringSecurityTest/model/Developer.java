package com.sinakaev.SpringSecurityTest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 1) POJO Developer
 * 2) LOMBOK
 *  Lombok позволяет не писать геттеры и сеттеры как и конструкторы всего лишь при помощи
 *  аннотации @Data
 *
 *  Lombok это круто. Думай как Lombot
 *
 * @author Mark Sinakaev
 * @version 1.0
 */

@Data
@AllArgsConstructor
public class Developer {
    private Long id;
    private String firstName;
    private String lastName;

}
