package com.reactapp.springjwt.repository;

import java.util.List;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reactapp.springjwt.models.Allpoint;

@Repository
public interface PointRepository extends JpaRepository<Allpoint, Integer> {	
	
	@Query(value = "SELECT * FROM public.allpoints ORDER BY ST_Distance(location,  :geom ) LIMIT 5", nativeQuery = true)
	List<Allpoint> findNearest(final Point geom);
	
	/*  SELECT * FROM allpoints WHERE ST_DWithin(location, ST_SetSRID(ST_Point(6.9333, 46.8167), 4326), 60)  */
	@Query(value = "SELECT * FROM public.allpoints WHERE ST_DWithin(location, ST_SetSRID(ST_Point(:lat, :longit), 4326), :distance) and category=:category", nativeQuery = true)
	List<Allpoint> findWithinDistance(double lat,double longit,double distance,String category);
	
	/*  SELECT * FROM allpoints WHERE ST_DWithin(location, ST_SetSRID(ST_Point(6.9333, 46.8167), 4326), 60)  */
	@Query(value = "SELECT * FROM public.allpoints WHERE ST_DWithin(location, ST_SetSRID(ST_Point(:lat, :longit), 4326), :distance) and userid=:userid and routesid=:routesid", nativeQuery = true)
	List<Allpoint> findWithinDistanceAndUser(double lat,double longit,double distance, int userid, int routesid);
	
	List<Allpoint> findAllByUserid(long userid);
	
	@Query(value = "SELECT * FROM public.allpoints u where u.userid = :userid and u.routesid = :routesid", nativeQuery = true)
	List<Allpoint> findByUserIdAndRoutesId(int userid,int routesid);
	
	@Query(value = "SELECT COALESCE(max(u.seqnum),0) FROM public.allpoints u where u.userid = :userid and u.routesid = :routesid", nativeQuery = true)
	int findSeqByUserIdAndRoutesId(int userid,int routesid);
	
	Page<Allpoint> findPageByUserid(Pageable pageable,int userid); 
	
}
