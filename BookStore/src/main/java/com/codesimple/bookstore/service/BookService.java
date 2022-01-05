package com.codesimple.bookstore.service;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.common.BadRequestException;
import com.codesimple.bookstore.common.Error;
import com.codesimple.bookstore.data.BookData;
import com.codesimple.bookstore.dto.AuthorDTO;
import com.codesimple.bookstore.dto.BookDTO;
import com.codesimple.bookstore.dto.BookQueryDslDTO;
import com.codesimple.bookstore.entity.Author;
import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.entity.BookAuthor;
import com.codesimple.bookstore.repo.BookAuthorRepository;
import com.codesimple.bookstore.repo.BookRepository;
import com.codesimple.bookstore.validator.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

//    static List<Book> bookList = Arrays.asList(
//            new Book(1,"2 states","desc of 2 states",2007,"fiction"),
//            new Book(2,"dictator","desc of dictator",2008,"fiction"),
//            new Book(3,"1984","desc of 1984",2004,"fiction")
//    );
    //get
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAuthorRepository bookAuthorRepository;
    @Autowired
    private BookValidator bookValidator;

//    public List<Book> getBooks(){
//        List<Book> bookList = new ArrayList<>();
//        bookRepository.findAll()
//                .forEach(book -> bookList.add(book));
//        return bookList;
//    }

//    public List<Book> getBooks(Integer yop){
//        List<Book> bookList = new ArrayList<>();
//        if(yop == null){
//            bookRepository.findAll()
//                    .forEach(book -> bookList.add(book));
//        }else{
//           return bookRepository.findAllByYearOfPublication(yop);
//        }
//        return bookList;
//    }

//    public List<Book> getBooks(Set<Integer> yop){
//        List<Book> bookList = new ArrayList<>();
//        if(yop == null){
//            bookRepository.findAll()
//                    .forEach(book -> bookList.add(book));
//        }else{
//            return bookRepository.findAllByYearOfPublicationIn(yop);
//        }
//        return bookList;
//    }

    public List<Book> getBooks(Set<Integer> yop,String bookType){
        List<Book> bookList = new ArrayList<>();
        if(yop == null){
            bookRepository.findAll()
                    .forEach(book -> bookList.add(book));
        }else{
            return bookRepository.findAllByYearOfPublicationInAndBookType(yop,bookType);
        }
        return bookList;
    }

    //Create
//    public Book createBook(Book book){
//        return bookRepository.save(book);
//    }

    public Book createBook(Book book){
        //validation
       List<Error> errors = bookValidator.validateCreateBookRequest(book);
        //if not success
        if(errors.size()>0){
            throw new BadRequestException("bad request",errors);
        }


        //if success
        return bookRepository.save(book);
    }

    //get single book
//    public Optional<Book> getBookById(Long Id){
//        Optional<Book> book = bookRepository.findById(Id);
//        return book;
//    }

    //get book and author from mutiple table
    public BookDTO getBookById(Long Id, boolean authorData){
        Book book;
        List<BookAuthor> bookAuthors = null;
        book = bookRepository.findById(Id).get();
        if(authorData){
            bookAuthors = bookAuthorRepository.findAllByBookId(Id);
        }
        //set book details
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setDesc(book.getDesc());
        bookDTO.setYearOfPublication(book.getYearOfPublication());
        bookDTO.setBookType(book.getBookType());
        //get author details
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        if(bookAuthors !=null) {
            for (BookAuthor bookAuthor : bookAuthors) {
                Author author = bookAuthor.getAuthor();
                AuthorDTO authorDTO = new AuthorDTO();
                authorDTO.setId(author.getId());
                authorDTO.setName(author.getName());
                authorDTO.setGender(author.getGender());
                authorDTOList.add(authorDTO);

            }
            //set author details
            bookDTO.setAuthors(authorDTOList);
        }

        return bookDTO;
    }


    //update
    public Book updateBook(Book incomingBook){
        return bookRepository.save(incomingBook);
    }

    //delete
    public String deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
        return "Deleted Sucessfully";
    }

    //raw query get books
//    public List<Book> getBooksByRawQuery(Set<Integer> yop) {
//
//        List<Book> bookList = bookRepository.findAllByYearOfPublicationIn(yop);
//        return bookList;
//    }

    public APIResponse getBooksByRawQuery(Set<Integer> yop){
        APIResponse apiResponse = new APIResponse();
        //db call
        List<Book> bookList = bookRepository.findAllByYearOfPublicationIn(yop);
//        Map data = new HashMap();
//        data.put("books",bookList);
        //set data
        BookData bookData = new BookData();
        bookData.setBooks(bookList);
        //set api response
        apiResponse.setData(bookData);
        return apiResponse;
    }

    public APIResponse getCaught(Integer yop) {
        int result = 100/yop;
        APIResponse response = new APIResponse();
        response.setData(result);
        return response;
    }

//    public APIResponse getBooksByQueryDsl(Integer year) {
//        APIResponse apiResponse = new APIResponse();
//        //repo to get the result
//        List<Book> bookList = bookRepository.getAllBooksByQueryDsl(year);
//        apiResponse.setData(bookList);
//        //return
//        return apiResponse;
//    }

    public APIResponse getBooksByQueryDsl(Integer year) {
        APIResponse apiResponse = new APIResponse();
        //repo to get the result
        List<BookQueryDslDTO> bookList = bookRepository.getAllBooksByQueryDslDto(year);
        apiResponse.setData(bookList);
        //return
        return apiResponse;
    }

}
