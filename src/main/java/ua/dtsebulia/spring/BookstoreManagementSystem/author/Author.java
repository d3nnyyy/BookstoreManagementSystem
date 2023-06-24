package ua.dtsebulia.spring.BookstoreManagementSystem.author;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private Integer id;
    private String name;
    private int age;

}
