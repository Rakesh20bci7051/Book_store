package com.example.demo.services;


import java.util.List;


import com.example.demo.payloads.BookDto;

public interface BookService {

	//add the book
	BookDto registerNewBook(BookDto book);
	
	//get the book
	BookDto getBookById(Integer bookId);
	
	
	//add the book
	BookDto updateBook(BookDto user,Integer userId);
	
	//delete the book
	
	void deleteBook(Integer userId);
	
	
	//get all the books
	 List<BookDto>getAllBooks();
	
	
}
