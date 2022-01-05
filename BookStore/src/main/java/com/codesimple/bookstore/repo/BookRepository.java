package com.codesimple.bookstore.repo;

import com.codesimple.bookstore.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book,Long>,BookRepositoryCustom {
//    List<Book> findAllByYearOfPublication(Integer yop);
//    List<Book> findAllByYearOfPublicationIn(Set<Integer> yop);
      List<Book> findAllByYearOfPublicationInAndBookType(Set<Integer> yop,String bookType);

//      String rawQuery = "select * from book where year_of_publication in ?1";

//      @Query(nativeQuery = true,value = rawQuery)
//      @Query(nativeQuery = true,value = "select * from book where year_of_publication in ?1")
      @Query(nativeQuery = true,value = "select * from book where year_of_publication in :yop")
      List<Book> findAllByYearOfPublicationIn(@Param("yop") Set<Integer> yop);
}
