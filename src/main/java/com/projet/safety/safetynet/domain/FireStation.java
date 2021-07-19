package com.projet.safety.safetynet.domain;

import javax.persistence.*;

@Entity
@Table(
	name="firestation",
	uniqueConstraints = {
		@UniqueConstraint(name = "firestation_unique", columnNames = {"address", "station"})
	}
	)
public class FireStation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long Id;
	
	@Column(name="address")
	private String address;

	@Column(name="station")
	private String station;
	
	public FireStation() {
	}

	public FireStation(Long id, String address, String station) {
		this.Id = id;
		this.address = address;
		this.station = station;
	}

	public FireStation(String address, String station) {
		this.address = address;
		this.station = station;
	}

	public Long getId() {
		return Id;
	}
	
	public void setId(Long id) {
		this.Id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

}
