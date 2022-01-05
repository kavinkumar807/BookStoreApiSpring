package com.codesimple.bookstore.controller;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.dto.BookDTO;
import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

//    @RequestMapping(value = "/books")
//    public List<Book> getBooks(){
//        return bookService.getBooks();
//    }

//    @RequestMapping(value = "/books")
//    public List<Book> getBooks(@RequestParam(value = "yearOfPublication",required = false) Integer yop){
//        return bookService.getBooks(yop);
//    }

//    @RequestMapping(value = "/books")
//    public List<Book> getBooks(@RequestParam(value = "yearOfPublications",required = false) Set<Integer> yop){
//        return bookService.getBooks(yop);
//    }

    @RequestMapping(value = "/books")
    public List<Book> getBooks(@RequestParam(value = "yearOfPublications",required = false) Set<Integer> yop,
                               @RequestParam(value = "bookType",required = false) String bookType){
        return bookService.getBooks(yop,bookType);
    }

    @RequestMapping(value = "/books",method = RequestMethod.POST)
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    //@PathVariable - /books/27
    //@PathParam - /books?id=27
//    @RequestMapping(value = "/books/{id}")
//    public Optional<Book> getBookById(@PathVariable("id") Long bookId){
//
//        return bookService.getBookById(bookId);
//    }

    @RequestMapping(value = "/books/{id}")
    public BookDTO getBookById(@PathVariable("id") Long bookId,
                               @RequestParam(value = "authorData",required = false) boolean authorData)
    {
        return bookService.getBookById(bookId,authorData);
    }

    @RequestMapping(value = "/books",method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book incomingBook){
        return bookService.updateBook(incomingBook);
    }

    @RequestMapping(value = "/books/{id}",method = RequestMethod.DELETE)
    public String deleteBookById(@PathVariable("id") Long bookId){
        return bookService.deleteById(bookId);
    }

    //@PostMapping for post
    //@DeleteMapping for delete
    //@PatchMapping for patch
//    @GetMapping("/raw/books")
//    public List<Book> getBooksByRawQuery(@RequestParam(value = "yop") Set<Integer> yop){
//        return bookService.getBooksByRawQuery(yop);
//    }

    @GetMapping("/raw/books")
    public APIResponse getBookByRawQuery(@RequestParam(value = "yop") Set<Integer> yop){
        return bookService.getBooksByRawQuery(yop);
    }

    @GetMapping("/caughtException")
    public APIResponse getCaughtException(@RequestParam(value = "number") Integer yop){
        return bookService.getCaught(yop);
    }

    @GetMapping("/queryDsl/books")
    public APIResponse getBooksByQueryDsl(@RequestParam(value = "year") Integer year){

        return bookService.getBooksByQueryDsl(year);
    }

}
