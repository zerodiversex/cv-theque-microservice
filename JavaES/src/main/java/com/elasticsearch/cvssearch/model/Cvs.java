package com.elasticsearch.cvssearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName="cvs", shards = 1, createIndex = false)
public class Cvs {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String fullname;

    private String cv;

    public Cvs(String fullname, String cv){
        this.fullname = fullname;
        this.cv = cv;
    }

    public String getFullname(){
        return this.fullname;
    }

    public void setId(String id){
        this.id = id;
    }


    public String getCV(){
        return this.cv;
    }

    public void setCV(String cv){
        this.cv = cv;
    }





}
