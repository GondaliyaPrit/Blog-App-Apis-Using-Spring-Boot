package com.blog.apis.services;


import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface Fileservice  {
	

	String Uplodefile(String path , MultipartFile file)  ;
	InputStream getresorce(String path , String filename); 

}
