package com.reactapp.springjwt.security.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.reactapp.springjwt.models.FileUpload;
import com.reactapp.springjwt.payload.response.FileUploadResponse;

public interface FileUploadService {
	
	public FileUploadResponse uploadFile(MultipartFile file, String uploaderName,int routeid, int pointid) throws IOException;
	public Resource fetchFileAsResource(String fileName, int routeid, int pointid) throws FileNotFoundException;
	public List<FileUpload> getAllFiles();
	//public Resource load(String filename,int routeid, int pointid);
	//public ResponseEntity<InputStreamResource> downloadFileAsInputStream(String fileName, int routeid, int pointid) throws FileNotFoundException;
	public ResponseEntity<InputStreamResource> downloadFileAsInputStream(String fileName) throws FileNotFoundException;
	public FileUpload findByRouteIdPointId(int routeid,int pointid); 
}
