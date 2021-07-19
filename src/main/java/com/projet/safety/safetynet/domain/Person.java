package com.projet.safety.safetynet.domain;

import javax.persistence.*;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(
	name="person",
	uniqueConstraints = {
		@UniqueConstraint(name = "person_unique", columnNames = {"first_name", "last_name"})
	}
)
public class Person {
	
	@Id
	@SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
	@Column(name="id")
	private Long Id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name="zip")
	private String zip;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	public Person() {
	}
	
	public Person(Long id, String firstName, String lastName, String adress, String city, String zip, String phone,
			String email) {
		this.Id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = adress;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	public Person(String firstName, String lastName, String adress, String city, String zip, String phone,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = adress;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
	}
	
	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		this.Id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
