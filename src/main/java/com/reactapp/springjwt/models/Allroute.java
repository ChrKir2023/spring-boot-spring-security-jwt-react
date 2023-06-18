package com.reactapp.springjwt.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the allroutes database table.
 * 
 */
@Entity
@Table(name="allroutes")
@NamedQuery(name="Allroute.findAll", query="SELECT a FROM Allroute a")
public class Allroute implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name="routes_id_seq",
                       sequenceName="routes_id_seq",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="routes_id_seq")
	private Integer id;

	private String routename;
	
	private String category;
	
	private Integer userid;
	

	//bi-directional many-to-one association to Allpoint
	@JsonIgnore
	@OneToMany(mappedBy="allroute")
	private List<Allpoint> allpoints;

	public Allroute() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoutename() {
		return this.routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public List<Allpoint> getAllpoints() {
		return this.allpoints;
	}

	public void setAllpoints(List<Allpoint> allpoints) {
		this.allpoints = allpoints;
	}

	public Allpoint addAllpoint(Allpoint allpoint) {
		getAllpoints().add(allpoint);
		allpoint.setAllroute(this);

		return allpoint;
	}

	public Allpoint removeAllpoint(Allpoint allpoint) {
		getAllpoints().remove(allpoint);
		allpoint.setAllroute(null);

		return allpoint;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
	
	

}