package com.dms.management.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Data
@NoArgsConstructor
@Document(indexName = "device", type = "_doc")
public class Device {

	@Id
	private String id;

	@Field
	private String name;

	@Field
	private String category;

	@Field
	private GeoPoint geoPoint;

	@Field
	private String token;

}
