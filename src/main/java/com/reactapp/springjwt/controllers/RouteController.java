package com.reactapp.springjwt.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactapp.springjwt.models.Allroute;
import com.reactapp.springjwt.payload.request.RouteRequest;
import com.reactapp.springjwt.repository.RouteRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/add")
public class RouteController {
	
	
	@Autowired
	RouteRepository routeRepository;
	
	//Method to save the data to the table
	
	 @PostMapping("/route")
	  public String addRoute(@Valid @RequestBody RouteRequest routeRequest) {
		 
		System.out.println(" route and RouteRequest"+routeRequest.getRoutename()); 

		Allroute allroute = new Allroute();
		allroute.setRoutename(routeRequest.getRoutename());
		allroute.setCategory(routeRequest.getCategory());
		allroute.setUserid(routeRequest.getUserid());
		
		routeRepository.save(allroute);

	    return allroute.getId().toString();
	  }
	 
	 //Add the method to retrieve the data using the route id
	@GetMapping("/routebyid")
	public Allroute findRouteById(String routeid) {
		
		System.out.println("findRouteById route and RouteRequest routeid is: "+routeid); 
		
		Allroute allroute = new Allroute();
		
		try {
			allroute = routeRepository.findById(Integer.valueOf(routeid)).get();
		} catch(Exception e) {
			System.out.println("Error is :"+e.getMessage());
		}
		
		return allroute;
	}
	
	//Add the method to retrieve the data using the route id
		@GetMapping("/routebyuserid")
		public List<Allroute> findRouteByUserId(String userid) {
			
			System.out.println("findRouteByUserId and user id is: "+userid); 
			
			List<Allroute> allroutesbuuserid = new ArrayList();
			
			try {
				allroutesbuuserid = routeRepository.findAllByUserid(Integer.valueOf(userid));
			} catch(Exception e) {
				System.out.println("findRouteByUserId "); 
				System.out.println("Error is :"+e.getMessage());
			}
			
			return allroutesbuuserid;
		}
		
		
		@DeleteMapping("/deleteroute")
		public String deleteRouteById(String routeid) {
					
			       System.out.println("Delete method and Route id is:"+routeid);
			       String result = "";
					
					try {
						routeRepository.deleteById(Integer.valueOf(routeid));
						result = result+"success";
						System.out.println("deleteRouteById delete from react!");
					} catch(Exception e) {
						result = result+"error";
						System.out.println("delete Error is :"+e.getMessage());
					}
					
					System.out.println("delete route from React using id!");
					
					return result;
		}
	 

}
