package com.dms.management.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
@NoArgsConstructor
@Document(indexName = "dms_device", type = "_doc", createIndex = false, useServerConfiguration = true)
@ToString
public class Device {

    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String location;

    @Field
    private String host;

    @Field
    private String lastActivity;

    @Field
    private String type;

    @Field
    private List<Action> actions;

    @Field
    private String token;

    public Device(String id) {
        this.id = id;
    }

}
