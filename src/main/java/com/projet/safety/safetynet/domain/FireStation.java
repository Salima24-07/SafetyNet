package com.projet.safety.safetynet.domain;

import javax.persistence.*;

@Entity
@Table(name="firestation")
public class FireStation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int Id;
	
	@Column(name="address")
	private String address;

	@Column(name="station")
	private String station;
	
	public FireStation(String address, String station) {
		this.address = address;
		this.station = station;
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
