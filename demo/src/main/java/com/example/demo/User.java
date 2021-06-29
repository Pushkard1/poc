package com.example.demo;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.Specification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table

@ToString
public class User {
	public User(String name, String surname, String email, int pincode, LocalDate dob, LocalDate doj) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.pincode = pincode;
		this.dob = dob;
		this.doj = doj;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@SequenceGenerator(
			name ="user_sequence",
			sequenceName="user_sequence",
			allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator ="user_sequence" )
	private long id;
	private String name;
	private String surname;
	private String email;
	private int pincode;
	private LocalDate dob;
	private LocalDate doj;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	public User(long id, String name, String surname, String email, int pincode, LocalDate dob, LocalDate doj) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.pincode = pincode;
		this.dob = dob;
		this.doj = doj;
	}
	
	
}
