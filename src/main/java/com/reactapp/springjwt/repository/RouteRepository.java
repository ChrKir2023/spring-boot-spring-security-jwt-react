package com.reactapp.springjwt.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reactapp.springjwt.models.Allpoint;
import com.reactapp.springjwt.models.Allroute;

@Repository
public interface RouteRepository extends JpaRepository<Allroute, Integer>{
	
	List<Allroute> findAllByUserid(int userid);
	
}
