package com.blog.apis.Implimatation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.apis.services.Fileservice;


@Service
public class FileServiceimpl  implements Fileservice{

	@Override
	public String Uplodefile(String path, MultipartFile file)  {
		// TODO Auto-generated method stub
		String name = file.getOriginalFilename();
		String rendomid =UUID.randomUUID().toString();
		String filename =	rendomid.concat(name.substring(name.lastIndexOf(".")));
		// Full Path
		String filepath = path + File.separator + filename;
		// Create folder if not Created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}
		// File copy
		try {
			Files.copy(file.getInputStream(), Paths.get(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filename;
		
	}

	@Override
	public InputStream getresorce(String path, String filename) {
		// TODO Auto-generated method stub
		String fullpath = path+File.separator+filename ;
		InputStream inputStream = null  ;
		try {
			 inputStream = new  FileInputStream(fullpath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}
	


}
