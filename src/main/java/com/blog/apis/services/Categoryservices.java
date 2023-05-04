package com.blog.apis.services;

import java.util.List;



import com.blog.apis.playloads.CategoryDto;

public interface Categoryservices {

	// create
	 CategoryDto Createcategory(CategoryDto categoryDto);

	// update
	 CategoryDto Updatecategory(CategoryDto categoryDto, Integer catid);

	// delete
	 void Deletecategory(Integer catid);

	// get
	 CategoryDto Getcategory(Integer catid);

	// getall
	 List<CategoryDto> Getallcategory();

}
