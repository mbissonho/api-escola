package com.escola.api.configuration.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("apiescola")
public class ApiEscolaProperty {

	private String originAllowed = "";

	public String getOriginAllowed() {
		return originAllowed;
	}

	public void setOriginAllowed(String originAllowed) {
		this.originAllowed = originAllowed;
	}
	
}
