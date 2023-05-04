package com.blog.apis.Implimatation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.apis.Enitys.Category;
import com.blog.apis.exaptions.ResourcenotfoundExaption;
import com.blog.apis.playloads.CategoryDto;
import com.blog.apis.repo.Categoryrepo;
import com.blog.apis.services.Categoryservices;

@Service
public class Categoryserviceimpl implements Categoryservices {

	@Autowired(required =  true)
	private Categoryrepo categoryrepo;
	@Autowired
	private ModelMapper mapper ;

	@Override
	public CategoryDto Createcategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat = this.mapper.map(categoryDto, Category.class);
		
		Category category = this.categoryrepo.save(cat);
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto Updatecategory(CategoryDto categoryDto, Integer catid) {
		// TODO Auto-generated method stub
		Category category = this.categoryrepo.findById(catid)
				.orElseThrow(() -> new ResourcenotfoundExaption("Catregory", "Catregory id", catid));
		category.setCattittle(categoryDto.getCattittle());
		category.setCatdic(categoryDto.getCatdic());
		Category updatecate = this.categoryrepo.save(category);

		return this.mapper.map(updatecate, CategoryDto.class);
	}

	@Override
	public void Deletecategory(Integer catid) {
		// TODO Auto-generated method stub
	 this.categoryrepo.findById(catid)
				.orElseThrow(() -> new ResourcenotfoundExaption("Catregory", "Catregory id", catid));
		this.categoryrepo.deleteById(catid);

	}

	
	@Override
	public List<CategoryDto> Getallcategory() {
		// TODO Auto-generated method stub
		
		List<Category> cates = (List<Category>)this.categoryrepo.findAll();
		List<CategoryDto> catdtos = new ArrayList<>();
		
		for (Category category : cates) {
			System.out.println(category.getCatdic());
			catdtos.add(this.mapper.map(category,CategoryDto.class));
		}

		return catdtos;
	}
	
	@Override
	public CategoryDto Getcategory(Integer catid) {
		// TODO Auto-generated method stub
		Category category = this.categoryrepo.findById(catid)
				.orElseThrow(() -> new ResourcenotfoundExaption("Category", "Category id ", catid));
		return this.mapper.map(category, CategoryDto.class);
	}



}
