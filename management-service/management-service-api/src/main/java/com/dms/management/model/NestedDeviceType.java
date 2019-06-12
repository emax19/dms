package com.dms.management.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Data
@NoArgsConstructor
@Document(indexName = "device-type", type = "_doc")
public class NestedDeviceType {

	@Id
	private String id;

	@Field
	private String name;


}
