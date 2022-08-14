package com.store.storevmware.Entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
public class Catalog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonIgnore
	@OneToMany(mappedBy = "catalog")
	private List<Item> items;
	@NotNull
	private String discription;
	
	public Catalog() {
		
	}

	public Catalog(int id, List<Item> items, String discription) {
		super();
		this.id = id;
		this.items = items;
		this.discription = discription;
	}
	
	public Catalog(int id, String discription) {
		super();
		this.id = id;
		this.discription = discription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	
	
}
