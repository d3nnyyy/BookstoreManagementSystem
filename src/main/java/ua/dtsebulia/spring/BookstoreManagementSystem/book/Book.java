package ua.dtsebulia.spring.BookstoreManagementSystem.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.dtsebulia.spring.BookstoreManagementSystem.author.Author;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;
    private String title;
    private int year;
    private String genre;
    private int pages;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Author author;

}
