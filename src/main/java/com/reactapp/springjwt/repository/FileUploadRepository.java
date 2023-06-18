package com.reactapp.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reactapp.springjwt.models.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Integer>{
	
	public FileUpload findFirstByRouteidAndPointid(int routeid,int pointid);
	

}
