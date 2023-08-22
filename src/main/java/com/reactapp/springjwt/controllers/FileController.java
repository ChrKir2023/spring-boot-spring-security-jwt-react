package com.reactapp.springjwt.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reactapp.springjwt.models.Allroute;
import com.reactapp.springjwt.models.FileUpload;
import com.reactapp.springjwt.payload.request.RouteRequest;
import com.reactapp.springjwt.payload.response.FileUploadResponse;
import com.reactapp.springjwt.payload.response.MessageResponse;
import com.reactapp.springjwt.security.services.FileUploadService;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping(value = "/file")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {
	
	@Autowired
	FileUploadService fileUploadService;
	
	@PostMapping(value = "/upload")
	//public ResponseEntity<Object> uploadFiles(@RequestParam("name") String name ,@RequestParam("files") MultipartFile[] files , consumes = MediaType.MULTIPART_FORM_DATA_VALUE) {
	public ResponseEntity<?> uploadFiles(@RequestParam("name") String name,
			                                  @RequestParam("routeid") String routeid, 
			                                  @RequestParam("pointid") String pointid,
			                                  @RequestPart("files") MultipartFile[] files,
			                                  RedirectAttributes redirectAttributes) {
	   System.out.println("Name is:"+name);
	   System.out.println("Route id is:"+routeid);
	   System.out.println("Point id is:"+pointid);
		try {
	    List<FileUploadResponse> fileUploadResponses =
	        Arrays.stream(files).map(file -> {
	          try {
	            return fileUploadService.uploadFile(file, name, Integer.parseInt(routeid),Integer.parseInt(pointid));
	          } catch (IOException e) {
	            throw new UncheckedIOException(e);
	          }
	        }).collect(Collectors.toList());

	   // return new ResponseEntity<>(fileUploadResponses, HttpStatus.OK);
	    return ResponseEntity.ok(new MessageResponse("Επιτυχής ανέβασμα αρχείου!"));
	  } catch (UncheckedIOException e) {
		  return ResponseEntity.badRequest().body(new MessageResponse("Πρόβλημα με το ανέβασμα του αρχείου!Παρακαλώ επικοινωνήστε με τον διαχειριστή"));
	 /* } catch (FileNotSupportedException e) {
	    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);*/
	  }
		
	}
	
	  @GetMapping
	  public List<FileUpload> getAllFiles() {
	    return this.fileUploadService.getAllFiles();
	  }
	  
	  //@GetMapping("/download/{fileName:.+}")
	  /*@GetMapping("/download/{fileName}")
	  public ResponseEntity<Object> downloadFile(@PathVariable String fileName, 
			                                     HttpServletRequest request) {
		  
		System.out.println(" Method downloadFile is: ");  

	    try {
	      Resource resource = this.fileUploadService.fetchFileAsResource(fileName,1,1);
	      String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

	      if (contentType == null) {
	        contentType = "application/octet-stream";
	      }

	      return ResponseEntity.ok()
	          .contentType(MediaType.parseMediaType(contentType))
	          .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	          .body(resource);

	    } catch (IOException ex) {
	      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }
	  }*/
	  
	//@GetMapping("/download/{fileName}")
	@GetMapping("/download")
	//public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String fileName) throws IOException {
	public ResponseEntity<InputStreamResource> downloadFile(@RequestParam("downloadUri") String downloadUri) throws IOException {	
        // Assuming the files are stored in a "files" directory
		System.out.println(" The file is: "+downloadUri);
		
		//File file = new File(fileName);
		System.out.println("File is: "+downloadUri);
        //HttpHeaders headers = new HttpHeaders();
        //headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=");

       // ResponseEntity<InputStreamResource> myResponse = this.fileUploadService.downloadFileAsInputStream(fileName,1,1);
        ResponseEntity<InputStreamResource> myResponse = this.fileUploadService.downloadFileAsInputStream(downloadUri);
		
		System.out.println("downloadFile and response is:"+myResponse.getHeaders());
        
        /*return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(new FileInputStream(file)));*/
		return myResponse;
		
		//return this.fileUploadService.downloadFileAsInputStream(fileName,1,1);
		
		
    }
	
	/*@GetMapping("/download/{filename}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) throws IOException {
		System.out.println(" Response Entity method and filename is: "+filename);
        Resource file = fileUploadService.load(filename,1,1);
        System.out.println(" Response Entity method and filename name is: "+file.getFilename());
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\"" + file.getFilename() + "\"").body(file);

    }
	  */
	  
	/* Method used to retrieve data from table file_upload using as parameters routeid and pointid */
	
	@GetMapping("/findbyrouteidpointid")
	public FileUpload findByRouteIdAndPointId(@RequestParam("routeid") String routeid,@RequestParam("pointid") String pointid) throws IOException {
	
		System.out.println(" findByRouteIdAndPointId and routeid "+routeid+" and pointid "+pointid);
		FileUpload fileUpload = fileUploadService.findByRouteIdPointId(Integer.parseInt(routeid), Integer.parseInt(pointid));
		
		System.out.println(" File upload is: "+fileUpload.getFileDownloadUri());
		
		return fileUpload;
		
		
	}

}
