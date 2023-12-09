package com.example.jobsApp.controller;

import com.example.jobsApp.models.Extra;

record ExtraDto(
		Long id,
		Boolean remote,
		Boolean flexibel,
		Boolean signUp,
		Boolean devices,
		Boolean extrapay,
		String sonstiges) {

public static ExtraDto fromDomain(Extra c) {
	return new ExtraDto(c.getId(),
			c.getRemote(),
			c.getFlexibel(),
			c.getSignUp(),
			c.getDevices(),
			c.getExtrapay(),
			c.getSonstiges()
			);
	}
}
