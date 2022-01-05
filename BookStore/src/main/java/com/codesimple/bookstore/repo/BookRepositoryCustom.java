package com.codesimple.bookstore.repo;

import com.codesimple.bookstore.dto.BookQueryDslDTO;
import com.codesimple.bookstore.entity.Book;

import java.util.List;

public interface BookRepositoryCustom {

    public List<Book> getAllBooksByQueryDsl(Integer year);

    public List<BookQueryDslDTO> getAllBooksByQueryDslDto(Integer year);
}
