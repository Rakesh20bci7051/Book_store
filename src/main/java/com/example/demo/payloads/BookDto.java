package com.example.demo.payloads;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDto {

	private int id;
	
	@NotEmpty(message="title should not be null")
	@Size(min =4,max=1000,message=" Min char=4 and max=10 for title should not be there")
	private String title;
	
	
	@NotEmpty(message="Author name should not be null")
	@Size(min =4,max=1000,message=" Min char=4 and max=10 for author should not be there")
	private String author;
	
	@NotEmpty(message="ISBN should not be null")
	@Size(min =4,max=1000,message=" Min char=4 and max=10 for ISBN should not be there")
	private String isbn;
	
	
	@Min(value = 0, message = "Price must be greater than or equal to zero")
	private Integer price;
	
	private int quantity;
}
