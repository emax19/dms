package com.dms.management.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Map;

@Data
@NoArgsConstructor
@Document(indexName = "dms_device_log", type = "_doc", createIndex = false, useServerConfiguration = true)
@ToString
public class DeviceLog {

    @Field
    private String id;

    @Field
    private String timestamp;

    @Field
    private String deviceId;

    @Field
    private Map<String, Object> state;

}
