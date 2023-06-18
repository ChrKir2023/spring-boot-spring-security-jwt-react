package com.reactapp.springjwt.models;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Point;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * The persistent class for the allpoints database table.
 * 
 */
@Entity
@Table(name="allpoints")
@NamedQuery(name="Allpoint.findAll", query="SELECT a FROM Allpoint a")
public class Allpoint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name="points_id_seq",
                       sequenceName="points_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="points_id_seq")
	private Integer id;

	private String description;

	private double latitude;

	private double longitude;
	
	private String category;

	@Column(name="point_name")
	private String pointName;

	private Integer seqnum;
	
	private Integer userid;
	
	//other fields 
	@JsonIgnore
	@Column(columnDefinition = "geometry(Point,4326)")
	private Geometry location;

	//bi-directional many-to-one association to Allroute
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="routesid")
	private Allroute allroute;
	
	
	
	
	public Allpoint() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	public String getPointName() {
		return this.pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public Integer getSeqnum() {
		return this.seqnum;
	}

	public void setSeqnum(Integer seqnum) {
		this.seqnum = seqnum;
	}

	public Allroute getAllroute() {
		return this.allroute;
	}

	public void setAllroute(Allroute allroute) {
		this.allroute = allroute;
	}

	public Geometry getLocation() {
		return location;
	}

	public void setLocation(Geometry location) {
		this.location = location;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	
	
	
}