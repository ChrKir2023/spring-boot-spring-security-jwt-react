package com.reactapp.springjwt.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;


import org.locationtech.jts.geom.Coordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

import com.reactapp.springjwt.models.Allpoint;
import com.reactapp.springjwt.models.Allroute;
import com.reactapp.springjwt.models.User;
import com.reactapp.springjwt.payload.request.PointRequest;
import com.reactapp.springjwt.payload.response.PaginationPointsResponse;
import com.reactapp.springjwt.repository.PointRepository;
import com.reactapp.springjwt.repository.RouteRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/add")
public class PointController {
	
	
	@Autowired
	PointRepository pointRepository;
	

	@Autowired
	RouteRepository routeRepository;
	
	
	//Method to save the data to the table
	/*@RequestMapping(path = "/employee", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public String saveEmployee(@ModelAttribute Employee employee) {
	    employeeService.save(employee);
	    return "employee/success";
	}*/
	/*consumes=MediaType.MULTIPART_FORM_DATA_VALUE)*/
	/*@PostMapping("/point")*/
	
	 /*@PostMapping("/point")
	  public String addPoint(@Valid @RequestBody PointRequest pointRequest,
			                  consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}) */
		
	/*@RequestMapping(value="/point",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)*/
	/*@RequestMapping(value = "/point",headers = ("content-type=multipart/*"), method = RequestMethod.POST, consumes = { "multipart/mixed", MediaType.MULTIPART_FORM_DATA_VALUE })*/
	@PostMapping("/point")
	public String addPoint(@Valid @RequestBody PointRequest pointRequest) {
        Allpoint allpoint = new Allpoint();
		allpoint.setPointName(pointRequest.getPointname());
		allpoint.setLatitude(pointRequest.getLatitude());
		allpoint.setLongitude(pointRequest.getLongitude());
		
		allpoint.setDescription(pointRequest.getDescription());
		allpoint.setUserid(pointRequest.getUserid());
		allpoint.setCategory(pointRequest.getCategory());
		//How to create the point to save it in a table
		GeometryFactory gf = new GeometryFactory();
		Point point = gf.createPoint(new Coordinate(pointRequest.getLatitude(), pointRequest.getLongitude()));
		
		
		//Add the point to the table
		allpoint.setLocation(point);
		//Add file 
		/*if (allpoint.getData() != null) {
			
			/*InputStream inputStream;
			try {
				inputStream = file.getInputStream();
			
				String originalName = file.getOriginalFilename();
				String name = file.getName();
				String contentType = file.getContentType();
				long size = file.getSize();
				System.out.println("inputStream: " + inputStream);
				System.out.println("originalName: " + originalName);
				System.out.println("name: " + name);
				System.out.println("contentType: " + contentType);
				System.out.println("size: " + size);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			
			System.out.println(" File is not null!");
		} else {
			System.out.println(" File is null!");
		}*/
		
		try {
			//find the route object
			System.out.println("addPoint Value of id is:"+pointRequest.getRouteid());
			Optional<Allroute> allrouteOptional = routeRepository.findById(Integer.valueOf(pointRequest.getRouteid()));
			allpoint.setAllroute(allrouteOptional.get());
			
			//Define the seqnum -- check the seqnum of the route
			int maxseqnum = pointRepository.findSeqByUserIdAndRoutesId(Integer.valueOf(pointRequest.getUserid()), Integer.valueOf(pointRequest.getRouteid()));
			
			allpoint.setSeqnum(maxseqnum+1);
			
		}catch(Exception e) {
			System.out.println("addPoint Error is"+e.getLocalizedMessage());
		}
			
		pointRepository.save(allpoint);

	    return "Succeded";
	  }
	
	 @GetMapping("/pointall")
	  public List<Allpoint> searchresults() {
		 
		 //return pointRepository.findNearByRadius(10, 20, 1000);
		 System.out.println("pointall Inside search results!");
		 List<Allpoint> allpoints = new ArrayList();
		 
		 try {
			 allpoints = pointRepository.findAll();
		 } catch(Exception e) {
			 System.out.println("pointall Error is:"+e.getLocalizedMessage());
		 }

		 return allpoints;
	  }
	 
	 @GetMapping("/pointallbyuserid")
	  public List<Allpoint> searchresultsbyid(String userid) {
		 
		 //return pointRepository.findNearByRadius(10, 20, 1000);
		 System.out.println("pointallbyuserid Inside search results!Id is: "+userid);
		 List<Allpoint> allpoints = new ArrayList();
		 
		 try {
			 allpoints = pointRepository.findAllByUserid(Integer.valueOf(userid));
		 } catch(Exception e) {
			 System.out.println("pointallbyuserid Error is:"+e.toString());
		 }

		 return allpoints;
	  }
	 
	 @GetMapping("/pointallbyuseridandrouteid")
	  public List<Allpoint> searchresultsbyuseridandrouteid(String userid,String routesid) {
		 
		 //return pointRepository.findNearByRadius(10, 20, 1000);
		 System.out.println("pointallbyuseridadnrouteid Inside search results!Id is: "+userid+" and routesid is:"+routesid);
		 List<Allpoint> allpoints = new ArrayList();
		 
		 try {
			 //allpoints = pointRepository.findAllByUserid(Integer.valueOf(userid));
			 allpoints = pointRepository.findByUserIdAndRoutesId(Integer.valueOf(userid),Integer.valueOf(routesid));
		 } catch(Exception e) {
			 System.out.println("pointallbyuseridandrouteid Error is:"+e.toString());
		 }

		 return allpoints;
	  }
	 
	 //Find the points within circle
	 @GetMapping("/pointwithindist")
	 public List<Allpoint> searchrwithindist(String lat,String longit,String distance,String category) {
		 
		 System.out.println("searchrwithindist pointwithindist Values are lat:"+lat+" longitudine : "+longit+" and distance is: "+distance);
		 System.out.println("Value of category is:"+category);
		 return pointRepository.findWithinDistance(Double.valueOf(lat), Double.valueOf(longit), Double.valueOf(distance),category);

	  }
	 
	 //Add the method to retrieve the data using the route id
		@GetMapping("/pointbyid")
		public Allpoint findPointById(String pointid) {
			
			Allpoint allpoint = new Allpoint();
			
			try {
				allpoint = pointRepository.findById(Integer.valueOf(pointid)).get();
				System.out.println("pointbyid Inside findPointById Check calls from react!");
			} catch(Exception e) {
				System.out.println("pointbyid Error is :"+e.getMessage());
			}
			
			System.out.println("pointbyid Check calls from react!");
			return allpoint;
		}
		
		
		//Add the method to retrieve the data using the route id
		@DeleteMapping("/deletepoint")
		public String deletePointById(String pointid) {
					
			       System.out.println("Point id is:"+pointid);
					
					try {
						pointRepository.deleteById(Integer.valueOf(pointid));
						System.out.println("deletePointById delete from react!");
					} catch(Exception e) {
						System.out.println("delete Error is :"+e.getMessage());
					}
					
					System.out.println("delete method from React!");
					
					return "Success";
		}
		
		//Method used example for sorting
		
		@RequestMapping("/pointbypageandsize")
		public PaginationPointsResponse pointsbypageandsize(@RequestParam("page") Optional<Integer> page, 
					 			         @RequestParam("size") Optional<Integer> size,
					 			         @RequestParam("userid") Optional<Integer> userid) {
			
			

		      System.out.println(" Value of page is: "+page.get());
		      System.out.println(" Value of size is: "+size.get());
		      System.out.println(" Value of page is: "+userid.get());
		      
		      
		      
			
			  int currentPage = page.orElse(1);
		      int pageSize = size.orElse(4);
		      
		      long lngUserid = userid.get();

		      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			  String username = auth.getName();
			  
			  /*User customUser = (User)auth.getPrincipal();
			  long userid = customUser.getId();*/
			  
			  Page<Allpoint> pointsPage;
			  
			  //How to find the roles
//			  Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
//			  
//			  for (GrantedAuthority grantedAuthority : authorities) {
//				  System.out.println(" The authority is: "+grantedAuthority.getAuthority());
//			  }
			  
		      //Page<WkMovielist> moviePage = wkMovielistRepository.findAll(PageRequest.of(currentPage - 1, pageSize));
			  if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
				  System.out.println(" Authority is: ADMIN ");
				  //Pagination using Sort.by
				  pointsPage = pointRepository.findAll(PageRequest.of(currentPage - 1, pageSize,Sort.by("seqnum").descending()));
			  } else {
				  pointsPage = pointRepository.findPageByUserid(PageRequest.of(currentPage - 1, pageSize,Sort.by("seqnum").ascending()),userid.get());
				  System.out.println(" Authority is: NOT ADMIN ");
			  }
			  
		      int totalPages = pointsPage.getTotalPages()+1;
		      long totalElements = pointsPage.getTotalElements();
		       
		      
		      System.out.println(" Total pages are: "+pointsPage.getTotalPages());
		      
		      if (totalPages > 0) {
		          List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
		              .boxed()
		              .collect(Collectors.toList());
		      }
		      
		     System.out.println(" Number of items are:"+pointsPage.getNumberOfElements());
		     
		     List<Allpoint> allPagePoints = pointsPage.getContent();
		     
		     for (Allpoint allPoint : allPagePoints) {
		    	 
		    	 System.out.println("Inside list and values of route are:"+allPoint.getAllroute().getId());
		    	 
		     }
			
		     PaginationPointsResponse paginationPointsResponse = new PaginationPointsResponse(allPagePoints,totalElements,totalPages);
		     
			 return paginationPointsResponse;
			
		}
		

}
