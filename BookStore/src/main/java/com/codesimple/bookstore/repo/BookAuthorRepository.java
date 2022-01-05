package com.codesimple.bookstore.repo;

import com.codesimple.bookstore.entity.BookAuthor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAuthorRepository extends CrudRepository<BookAuthor,Long> {

    List<BookAuthor> findAllByBookId(Long id);
}
