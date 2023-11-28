package com.example.jobsApp.controller;

import com.example.jobsApp.models.Extra;

record ExtraDto(
		Boolean remote,
		Boolean flexibel,
		Boolean signup,
		Boolean devices,
		Boolean extrapay,
		String sonstiges) {

public static ExtraDto fromDomain(Extra c) {
	return new ExtraDto(
			c.getRemote(),
			c.getFlexibel(),
			c.getSignup(),
			c.getDevices(),
			c.getExtrapay(),
			c.getSonstiges()
			);
	}
}
