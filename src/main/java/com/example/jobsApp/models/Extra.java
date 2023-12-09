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
@Table(name="extras")
public class Extra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	private Boolean remote;
	private Boolean flexibel;
	@Column(name = "sign_up")
	private Boolean signUp;
	private Boolean devices;
	private Boolean extrapay;
	private String sonstiges;

	protected Extra( ) {}

	public Extra(Boolean remote, Boolean flexibel, Boolean signup, Boolean devices, Boolean extrapay, String sonstiges) {
		setRemote(remote);
		setFlexibel(flexibel);
		setSignUp(signup);
		setDevices(devices);
		setExtrapay(extrapay);
		setSonstiges(sonstiges);
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

	public Boolean getSignUp() {
		return signUp;
	}

	public void setSignUp(Boolean signUp) {
		this.signUp = signUp;
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

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sonstiges);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extra other = (Extra) obj;
		return Objects.equals(sonstiges, other.sonstiges);
	}

	@Override
	public String toString() {
		return "Extra [id=" + id + ", remote=" + remote + ", flexibel=" + flexibel + ", signUp=" + signUp
				+ ", devices=" + devices + ", extrapay=" + extrapay + ", sonstiges=" + sonstiges + "]";
	}

	
}



