package com.reactapp.springjwt.payload.response;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.reactapp.springjwt.models.Allpoint;

public class PaginationPointsResponse {

	public PaginationPointsResponse(List<Allpoint> allPaginatedPoints,
			                        long totalPaginationPoints,
			                        int totalPages) {
		super();
		
		this.allPaginatedPoints = allPaginatedPoints;
		this.totalPaginationPoints = totalPaginationPoints;
		this.totalPages = totalPages;
		
	}
	
	List<Allpoint> allPaginatedPoints = new ArrayList<>();
	
	long totalPaginationPoints;
	
	int totalPages;
	
	int routeid;

	public List<Allpoint> getAllPaginatedPoints() {
		return allPaginatedPoints;
	}

	public void setAllPaginatedPoints(List<Allpoint> allPaginatedPoints) {
		this.allPaginatedPoints = allPaginatedPoints;
	}


	public long getTotalPaginationPoints() {
		return totalPaginationPoints;
	}

	public void setTotalPaginationPoints(long totalPaginationPoints) {
		this.totalPaginationPoints = totalPaginationPoints;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getRouteid() {
		return routeid;
	}

	public void setRouteid(int routeid) {
		this.routeid = routeid;
	}
	
	
	
	
}
