package com.example.jobsApp.models;

import java.util.Objects;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name="company")
public class Company {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	private String name;
	@Column(name = "logo_url")
	private String logoUrl;
	private String address;
	private String plz;
	private String ort;
	private String website;
	
	protected Company() {}

	public Company(String name, String logoUrl, String address, String plz, String ort, String website) {
		setName(name);
		setLogoUrl(logoUrl);
		setAddress(address);
		setPlz(plz);
		setOrt(ort);
		setWebsite(website);
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return name == other.name;
	}

	@Override
	public String toString() {
		return "ID: %i, Name: %n, Address: %a, PLZ: %p, Ort: %o, Website: %w".formatted(id,name,address,plz,ort,website);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public long getId() {
		return id;
	}
	
}
