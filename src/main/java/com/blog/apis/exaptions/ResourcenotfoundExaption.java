package com.blog.apis.exaptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourcenotfoundExaption extends RuntimeException{
	
			/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
			String resorcename;
			String feildname;
			long feildvalue;
			public ResourcenotfoundExaption(String resorcename, String feildname, long feildvalue) {
				super(String.format("%S Not found With %S : %S" ,resorcename,feildname,feildvalue));
				this.resorcename = resorcename;
				this.feildname = feildname;
				this.feildvalue = feildvalue;
			}
			

}
