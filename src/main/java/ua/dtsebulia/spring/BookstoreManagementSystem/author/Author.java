package ua.dtsebulia.spring.BookstoreManagementSystem.author;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.dtsebulia.spring.BookstoreManagementSystem.book.Book;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private Integer id;
    private String name;
    private int age;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Book> books;
}
