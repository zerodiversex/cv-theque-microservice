package com.elasticsearch.cvssearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="cv", shards = 1, createIndex = false)
public class Cv {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String fullName;

    private String cv;


    public Cv(String fullName, String cv) {
        this.fullName = fullName;
        this.cv = cv;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }
}
