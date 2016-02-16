package com.snapdeal.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


public class FileBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  MultipartFile postedFile ;

	public void setPostedFile(MultipartFile postedFile) {
		this.postedFile = postedFile;
	}

	public MultipartFile getPostedFile() {
		return postedFile;
	}

		
}
