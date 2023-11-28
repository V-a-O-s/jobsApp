package com.example.jobsApp.models;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name="extras")
public class Extra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	private Boolean remote;
	private Boolean flexibel;
	private Boolean sign_up;
	private Boolean devices;
	private Boolean extrapay;
	private String sonstiges;
	
	protected Extra( ) {}
	
	public Extra(Boolean remote, Boolean flexibel, Boolean signup, Boolean devices, Boolean extrapay, String sonstiges) {
		setRemote(remote);
		setFlexibel(flexibel);
		setSignup(signup);
		setDevices(devices);
		setExtrapay(extrapay);
		setSonstiges(sonstiges);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getRemote() {
		return remote;
	}

	public void setRemote(Boolean remote) {
		this.remote = remote;
	}

	public Boolean getFlexibel() {
		return flexibel;
	}

	public void setFlexibel(Boolean flexibel) {
		this.flexibel = flexibel;
	}

	public Boolean getSignup() {
		return sign_up;
	}

	public void setSignup(Boolean signup) {
		this.sign_up = signup;
	}

	public Boolean getDevices() {
		return devices;
	}

	public void setDevices(Boolean devices) {
		this.devices = devices;
	}

	public Boolean getExtrapay() {
		return extrapay;
	}

	public void setExtrapay(Boolean extrapay) {
		this.extrapay = extrapay;
	}

	public String getSonstiges() {
		return sonstiges;
	}

	public void setSonstiges(String sonstiges) {
		this.sonstiges = sonstiges;
	}
	
	}
	


