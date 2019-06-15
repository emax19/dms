package com.dms.management.model;

import lombok.Data;

@Data
public class Action {

	private String name;
	private String method;
	private String relativeUrl;
	private String body;

}
