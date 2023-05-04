package com.blog.apis.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.apis.playloads.CategoryDto;
import com.blog.apis.services.Categoryservices;
import com.blog.apis.utils.Responcehandler;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class Categorycontroller {

	@Autowired(required = true)
	private Categoryservices categoryservices;
	
	@PostMapping("/")
	public ResponseEntity<Object> createcategoty(@Valid @RequestBody  CategoryDto categoryDto)
	{
		CategoryDto categoryDtos =	this.categoryservices.Createcategory(categoryDto);
		return Responcehandler.generateResponse("Category successfully Added", HttpStatus.OK, categoryDtos);
	}
	
	
	@PutMapping("/{categoryid}")
	public ResponseEntity<Object> updatecategory( @Valid @RequestBody CategoryDto categoryDto , @PathVariable Integer categoryid )
	{
		CategoryDto	catDto = this.categoryservices.Getcategory(categoryid);
		catDto.setCatdic(categoryDto.getCatdic());
		catDto.setCattittle(categoryDto.getCattittle());
		
		return Responcehandler.generateResponse("Category Successfuly updated",HttpStatus.OK, catDto);
	}
	
	@DeleteMapping("/{categoryid}")
	public ResponseEntity<Object> deletecategory(@PathVariable Integer categoryid )
	{
		 	this.categoryservices.Deletecategory(categoryid);
		 	return Responcehandler.generateResponse("Category Successfully Delete", HttpStatus.OK, categoryid);
		 
	}
	
	@GetMapping("/{categoryid}")
	public ResponseEntity<Object> getcategorybyid(@PathVariable Integer categoryid)
	{
		CategoryDto categoryDto = this.categoryservices.Getcategory(categoryid);
		return Responcehandler.generateResponse("Get Category Successfully", HttpStatus.OK, categoryDto);
	}

	@PostMapping("/allcat")
	public ResponseEntity<Object> getallcategory()
	{
		List<CategoryDto> categoryDtos = this.categoryservices.Getallcategory();
		
		
		for (CategoryDto categoryDto : categoryDtos) {
			System.out.println(categoryDto.getCattittle());
			System.out.println(categoryDto.getCatdic());
			System.out.println(categoryDto.getId());
		}
		return  Responcehandler.generateResponse("Caegory LIst", HttpStatus.OK,categoryDtos);
	}
	


}
