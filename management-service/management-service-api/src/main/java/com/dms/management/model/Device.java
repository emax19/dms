package com.dms.management.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@NoArgsConstructor
@Document(indexName = "device", type = "_doc", useServerConfiguration = true)
public class Device {

	@Id
	private String id;

	@Field
	private String name;

	@Field
	private NestedDeviceType type;

//	@Field
//	private GeoPoint geoPoint;

	@Field
	private String token;

}
