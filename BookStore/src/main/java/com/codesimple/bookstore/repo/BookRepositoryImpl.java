package com.codesimple.bookstore.repo;

import com.codesimple.bookstore.dto.BookQueryDslDTO;
import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.entity.QBook;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    public static QBook qBook = QBook.book;

//    @Override
//    public List<Book> getAllBooksByQueryDsl(Integer year) {
//        // query dsl
//        JPAQuery<Book> jpaQuery = new JPAQuery<>(entityManager);
//        //select * from book where year_of_publication = 2010;
//        //return
//        return jpaQuery.from(qBook)
//                .where(qBook.yearOfPublication.eq(year))
//                .fetch();
//    }

    @Override
    public List<Book> getAllBooksByQueryDsl(Integer year) {
        // query dsl
        JPAQuery<Book> jpaQuery = new JPAQuery<>(entityManager);
        //select id,bookType from book where year_of_publication = 2010;
//                          METHOD:1 using tuple
//        List<Tuple> tuples = jpaQuery
//                .select(qBook.bookType,qBook.id)
//                .from(qBook)
//                .where(qBook.yearOfPublication.eq(year))
//                .fetch();
//
//        List<Book> books = new ArrayList<>();
//
//        for(Tuple eachTuple : tuples){
//            Book book = new Book();
//            book.setId(eachTuple.get(qBook.id));
//            book.setBookType(eachTuple.get(qBook.bookType));
//
//            books.add(book);
////            System.out.println(eachTuple.get(qBook.id));
////            System.out.println(eachTuple.get(qBook.bookType));
//        }

        //               METHOD:2 Using Projection
        QBean<Book> qBean = Projections.bean(Book.class,
                qBook.id,
                qBook.bookType
        );
        List<Book> books = jpaQuery
                .select(qBean)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();
        //return
        return books;
    }

    @Override
    public List<BookQueryDslDTO> getAllBooksByQueryDslDto(Integer year) {

        // query dsl
        JPAQuery<BookQueryDslDTO> jpaQuery = new JPAQuery<>(entityManager);

        QBean<BookQueryDslDTO> dslDTOQBean = Projections.bean(BookQueryDslDTO.class,
                qBook.id,
                qBook.bookType.as("type")
        );

        List<BookQueryDslDTO> books = jpaQuery.
                select(dslDTOQBean)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();

        return books;
    }
}
