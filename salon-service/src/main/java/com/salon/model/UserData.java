package com.salon.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class UserData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("first_name")
	private String fname;

	@JsonProperty("last_name")
	private String lname;

	private String username;

	private String email;

	private String password;

	@JsonProperty(namespace = "mobile_number")
	private String mobileNo;

	@JsonProperty(namespace = "date_of_birth")
	private LocalDate dob;

	private String gender;

	@JsonProperty(namespace = "date_of_joining")
	private LocalDate dateOfJoining;

	@JsonProperty(namespace = "last_working_day")
	private LocalDate lastWorkingdate;

	private String pancard;

	private String aadharcard;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleData> roles = new HashSet<>();

	public UserData() {
	}

	public UserData(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

}
