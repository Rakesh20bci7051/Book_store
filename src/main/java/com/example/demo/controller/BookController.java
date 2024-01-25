package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.payloads.ApiResponse;
import com.example.demo.payloads.BookDto;

import com.example.demo.services.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = { "*" })
public class BookController {

	
	
	@Autowired
	private BookService userService;
	 
	//POST -create user
	 @PostMapping("/")
	 public ResponseEntity<BookDto>addNewBook(@Valid @RequestBody BookDto userDto)
	 {
		 BookDto createUserDto=this.userService.registerNewBook(userDto);
		 return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	 }
	 
	
	//PUT  -update user
	 @PutMapping("/{bookId}")
	 public ResponseEntity<BookDto>updateBook(@Valid @RequestBody BookDto userDto,@PathVariable("bookId") Integer uid)
	 {
		BookDto updatedUser= this.userService.updateBook(userDto,uid);
		return ResponseEntity.ok(updatedUser);
		 
	 }
	 
	
	 @DeleteMapping("/{bookId}")
	public ResponseEntity<?>deleteBook(@PathVariable("bookId")Integer uid)
	{
		
		 this.userService.deleteBook(uid);
		 return new ResponseEntity<ApiResponse>(new ApiResponse("Book  deleted success",true),HttpStatus.OK);
	}

	
	//GET - get book
		 @GetMapping("/{bookId}") //the URI is mentioned inside the mapping
		 public ResponseEntity<BookDto>getSingleUser(@PathVariable Integer bookId)
		 {
			 return ResponseEntity.ok(this.userService.getBookById(bookId));
		 }
		 
		 
			//GET - all book
		 @GetMapping("/") //the URI is mentioned inside the mapping
		 public ResponseEntity<List<BookDto>>getAllBooks()
		 {
			 return ResponseEntity.ok(this.userService.getAllBooks());
		 }
		
	
	
}
