package com.project.management.model;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String fullName;
	private String email;
	private String password;
	
	@JsonIgnore
	@OneToMany(mappedBy = "assignee",cascade = CascadeType.ALL)
	private List<Issue> assignedItems = new ArrayList<>();
	
	private int projectsize;
}
