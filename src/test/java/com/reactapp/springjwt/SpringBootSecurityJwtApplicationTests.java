package com.reactapp.springjwt;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.reactapp.springjwt.controllers.FileController;
import com.reactapp.springjwt.controllers.PointController;
import com.reactapp.springjwt.controllers.RouteController;
import com.reactapp.springjwt.models.Allpoint;
import com.reactapp.springjwt.models.Allroute;
import com.reactapp.springjwt.models.FileUpload;
import com.reactapp.springjwt.repository.PointRepository;

@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {
	
	@Autowired private PointRepository pointRepository;
	
	@Autowired private PointController pointController;
	
	@Autowired private RouteController routeController;
	
	@Autowired private FileController fileController;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void findPointsQWithinArea() {
		
		List<Allpoint>	allpointsindist = new ArrayList();
		//Add a test to check radius with Postgis
		allpointsindist = pointRepository.findWithinDistance(10.46,16.3,40);
		
		for (Allpoint currentAllPoint : allpointsindist) {
			System.out.println("Name of point is:"+currentAllPoint.getDescription());
			
		}
		
		System.out.println("Number of points within are:"+allpointsindist.size());
		
	}
	
	@Test
	public void findPointsFromController() {
		
		List<Allpoint>	allpointsindist = new ArrayList();
		//Add a test to check radius with Postgis
		allpointsindist = pointController.searchrwithindist("10.46","16.3","40");
		
		for (Allpoint currentAllPoint : allpointsindist) {
			System.out.println("Name of point is:"+currentAllPoint.getDescription());
			
		}
		
		System.out.println("Number of points within are:"+allpointsindist.size());
		
	}
	
	@Test
	public void findPointsByIdFromController() {
		
		List<Allpoint>	allpointsindist = new ArrayList();
		//Add a test to check radius with Postgis
		allpointsindist = pointController.searchresultsbyid("1");
		
		for (Allpoint currentAllPoint : allpointsindist) {
			System.out.println("Name of point is:"+currentAllPoint.getDescription());
			
		}
		
		System.out.println("Number of points within are:"+allpointsindist.size());
		
	}
	
	@Test
	public void findPointsByUserIdAndRoutesId() {
		
		List<Allpoint>	allpointsindist = new ArrayList();
		//Add a test to check radius with Postgis
		allpointsindist = pointController.searchresultsbyuseridandrouteid("1","44");
		
		for (Allpoint currentAllPoint : allpointsindist) {
			System.out.println("Name of point is:"+currentAllPoint.getDescription());
			
		}
		
		System.out.println("Number of points within are:"+allpointsindist.size());
		
	}
	
	
	@Test
	public void findRouteByRouteId() {
		
		Allroute allroute = new Allroute();
		
		try {
		 allroute = routeController.findRouteById("3");
		} catch(Exception e) {
			System.out.println("System error is:"+e.getLocalizedMessage());
		}
		
		System.out.println("Route id retrieved is:"+allroute.getId());
	}

	
	@Test
	public void findPointByPointId() {
		
		Allpoint allpoint = new Allpoint();
		
		try {
			allpoint = pointController.findPointById("1");
		} catch(Exception e) {
			System.out.println("System error is:"+e.getLocalizedMessage());
		}
		
		System.out.println("Route id retrieved is:"+allpoint.getId());
	}
	
	@Test
	public void deletePointByPointId() {
		
		try {
			pointController.deletePointById("5");
		} catch(Exception e) {
			System.out.println("System error is:"+e.getLocalizedMessage());
		}
		
		
	}
	
	@Test
	public void deleteRouteByRouteId() {
		
		try {
			routeController.deleteRouteById("3");
		} catch(Exception e) {
			System.out.println("System error is:"+e.getLocalizedMessage());
		}
		
		
	}
	
	@Test
	public void findFileUploadByRouteIdAndPointId() {
		
		try {
			FileUpload fileUpload = fileController.findByRouteIdAndPointId("1", "2");
			
			System.out.println(" File Upload download uri"+fileUpload.getFileDownloadUri());
			
		} catch(Exception e) {
			System.out.println("System error is:"+e.getLocalizedMessage());
		}
		
		
	}
	
	@Test
	public void findSeqByUserIdAndRouteId() {
		
		try {
			int maxseqnum = pointRepository.findSeqByUserIdAndRoutesId(8, 3);
			
			System.out.println(" Point seq num "+maxseqnum);
			
		} catch(Exception e) {
			System.out.println("System error is:"+e.getLocalizedMessage());
		}
		
		
	}
	
}
