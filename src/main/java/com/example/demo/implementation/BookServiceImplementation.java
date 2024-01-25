package com.example.demo.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.demo.entities.Book;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.payloads.BookDto;
import com.example.demo.repositories.BookRepo;
import com.example.demo.services.BookService;

@Service
public class BookServiceImplementation implements BookService {

	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired 
	BookRepo bookRepo;
	
	@Override
	public BookDto registerNewBook(BookDto book) {
		// TODO Auto-generated method stub
		   Book obj=this.dtoToBook(book);
		      Book saved=this.bookRepo.save(obj);
		      return this.bookToDto(saved);
		
		
		
	}

	@Override
	public BookDto getBookById(Integer bookId) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
				Book user=this.bookRepo.findById(bookId).orElseThrow(()-> new ResourceNotFoundException("Book","id",bookId));
				return this.bookToDto(user);
		
	}

	@Override
	public BookDto updateBook(BookDto bookDto, Integer userId) {
		// TODO Auto-generated method stub
		Book user=this.bookRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Book","Id",userId));
		
		user.setTitle(bookDto.getTitle());
		user.setAuthor(bookDto.getAuthor());
		user.setIsbn(bookDto.getIsbn());
		user.setPrice(bookDto.getPrice());
		user.setQuantity(bookDto.getQuantity());
	    Book updatedUser=this.bookRepo.save(user);//updated user
	
         BookDto userDto1=	this.bookToDto(updatedUser);// new updated userDto
         
          return userDto1;//return the Updated UserDto
	}

	@Override
	public void deleteBook(Integer userId) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		Book user=this.bookRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Book","Id",userId));
		
this.bookRepo.delete(user);
		
	}
	
	//get all the books

	@Override
	public List<BookDto> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book>users=this.bookRepo.findAll();
		List<BookDto>userDtos=users.stream().map(user->this.bookToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	
	
	
	
	//changing BookDto to Book
		private Book dtoToBook(BookDto bookDto)
		{
			Book user=this.modelMapper.map(bookDto,Book.class); //convert directly
			/*user.setId(userDto.getId());
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setAbout(userDto.getAbout());
			user.setPassword(userDto.getPassword());*/
			return user;
		}
		
		public BookDto bookToDto(Book book)
		{
			BookDto obj=this.modelMapper.map(book, BookDto.class);
			/*obj.setId(user.getId());
			obj.setEmail(user.getEmail());
			obj.setName(user.getName());
			obj.setAbout(user.getAbout());
			obj.setPassword(user.getPassword());*/
			
			return obj;
		}

}
