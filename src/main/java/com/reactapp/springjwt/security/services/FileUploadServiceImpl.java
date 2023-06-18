package com.reactapp.springjwt.security.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.core.io.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.reactapp.springjwt.models.FileUpload;
import com.reactapp.springjwt.payload.response.FileUploadResponse;
import com.reactapp.springjwt.repository.FileUploadRepository;


@Service
public class FileUploadServiceImpl implements FileUploadService {
	
	public FileUploadServiceImpl() throws IOException {}

	  @Autowired
	  private FileUploadRepository fileUploadRepository;

	  /*private final Path UPLOAD_PATH =
	      Paths.get(new ClassPathResource("").getFile().getAbsolutePath() + File.separator + "static"  + File.separator + "image");*/
	  
	  
	  /*"D:\\React_Projects\\Images";*/
	  /*C:\Users\AlexPowerUser\git\spring-boot-spring-security-jwt-react\target\classes\static\image\30161129052023_forest.jpg*/
	  
	  private final Path UPLOAD_PATH =
		      Paths.get("D:\\\\React_Projects\\\\Images" + File.separator + "static"  + File.separator + "image");
		  

	  @Override
	  public FileUploadResponse uploadFile(MultipartFile file, String uploaderName, int routeid, int pointid) throws IOException {
		  
		 Path myAddedPath = Paths.get(UPLOAD_PATH + File.separator + Integer.toString(routeid) + File.separator + Integer.toString(pointid));  
		 System.out.println(" New path is: "+myAddedPath);
		 
	    /*if (!Files.exists(UPLOAD_PATH)) {
	      Files.createDirectories(UPLOAD_PATH);
	    }*/
		if (!Files.exists(myAddedPath)) {
		      Files.createDirectories(myAddedPath);
		}      
		      
        System.out.println(" Inside uploadFile and it is : "+file.getContentType());
	    // file format validation
	    if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
	      throw new FileNotFoundException("only .jpeg and .png images are " + "supported");
	    }

	    String timeStampedFileName = new SimpleDateFormat("ssmmHHddMMyyyy")
	        .format(new Date()) + "_" + file.getOriginalFilename();

	    //Path filePath = UPLOAD_PATH.resolve(timeStampedFileName);
	    
	    Path filePath = myAddedPath.resolve(timeStampedFileName);
	    
	    System.out.println(" The path of the file is: "+filePath.toString());
	    Files.copy(file.getInputStream(), filePath);

	    String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	        .path("/image/").path(timeStampedFileName).toUriString();

	   /* String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	        .path("/file/download/").path(timeStampedFileName).toUriString();*/
	    
	    String fileDownloadUri = filePath.toString();

	    FileUpload fileUpload = new FileUpload(file.getOriginalFilename(), fileUri, fileDownloadUri, file.getSize(), uploaderName, routeid, pointid);

	    this.fileUploadRepository.save(fileUpload);

	    FileUploadResponse fileUploadResponse =
	        new FileUploadResponse(fileUpload.getId(),
	            file.getOriginalFilename(), fileUri, fileDownloadUri,
	            file.getSize(),
	            uploaderName);

	    return fileUploadResponse;
	  }

	  @Override
	  public Resource fetchFileAsResource(String fileName, int routeid, int pointid) throws FileNotFoundException {
		  
	   Path myAddedPath = Paths.get(UPLOAD_PATH + File.separator + Integer.toString(routeid) + File.separator + Integer.toString(pointid));  
	   System.out.println(" New path is: "+myAddedPath);  

	    try {
	    /* Path filePath = UPLOAD_PATH.resolve(fileName).normalize();
	      Resource resource = new UrlResource(filePath.toUri());*/
	    	Path filePath = myAddedPath.resolve(fileName).normalize();
		      Resource resource = new UrlResource(filePath.toUri()); 	
		      
	      if ((resource).exists()) {
	        return resource;
	      } else {
	        throw new FileNotFoundException("File not found " + fileName);
	      }
	    } catch (MalformedURLException ex) {
	      throw new FileNotFoundException("File not found " + fileName);
	    }
	  }

	  @Override
	  public List<FileUpload> getAllFiles() {
	    return this.fileUploadRepository.findAll();
	  }
	  
	  @Override
	  public ResponseEntity<InputStreamResource> downloadFileAsInputStream(String fileName) throws FileNotFoundException{
		  
		  System.out.println(" DownloadFileAsInputStream The file is: "+fileName);
		  
		  ResponseEntity<InputStreamResource> myResponse = null;
		  
		  //Path myAddedPath = Paths.get(UPLOAD_PATH + File.separator + Integer.toString(routeid) + File.separator + Integer.toString(pointid));  
		   //System.out.println(" New path is: "+myAddedPath);
		   //D:\React_Projects\Images\static\image\1\1\50040902062023_IMG_20220430_130114.jpg
		   //String myAddedPathNew = "D:\\React_Projects\\Images\\static\\image\\1\\1\\50040902062023_IMG_20220430_130114.jpg";
		   String myAddedPathNew = fileName;
		   
		  //String myAddedPathToStr = "D:\\React_Projects\\Images\\static\\image\\1\\1\\";
		  //Path myAddedPath = Paths.get(myAddedPath);
		  //System.out.println(" DownloadFileAsInputStream The string is: "+myAddedPathToStr);
		  //System.out.println(" DownloadFileAsInputStream The path is: "+myAddedPath.toString());
		   
		 try { 
		   //File file = new File(myAddedPath +"\\"+ fileName);
		   File file = new File(myAddedPathNew);
		   System.out.println(" DownloadFileAsInputStream File is: "+file.toString());
		   System.out.println(" DownloadFileAsInputStream File is: "+file.getName());
		   
	        HttpHeaders headers = new HttpHeaders();
	        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

	        myResponse = ResponseEntity
	                .ok()
	                .headers(headers)
	                .contentType(MediaType.MULTIPART_FORM_DATA)
	                .body(new InputStreamResource(new FileInputStream(file)));
		 } catch (Exception e) {
			 System.out.println(" DownloadFileAsInputStream and the error is: "+e.getLocalizedMessage());
		 }
		  
		  return myResponse;
	  }
	  
	 /* @Override
	    public Resource load(String filename,int routeid, int pointid) {
	        try {
	        	Path path = Paths.get(UPLOAD_PATH + File.separator + Integer.toString(routeid) + File.separator + Integer.toString(pointid)+ File.separator);
	            System.out.println("Path is:"+path.toString());
	        	Resource resource = new UrlResource(path.toUri());
	            if (resource.exists() || resource.isReadable()) {
	            	System.out.println(" Resource exists"+resource.getInputStream().toString());
	                return resource;
	                
	            } else {
	                throw new RuntimeException("Error ");
	            }
	            
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    }*/
	  
	  public FileUpload findByRouteIdPointId(int routeid,int pointid) {
		  
		  try {
			  System.out.println("Value of routeid is :"+routeid+" and pointid is: "+pointid);
			  return fileUploadRepository.findFirstByRouteidAndPointid(routeid,pointid);
		  } catch(Exception e) {
			  System.out.println(" Error is: "+e.getMessage());
			  return null;
		  }
		  
		  
	  }
	  

}
