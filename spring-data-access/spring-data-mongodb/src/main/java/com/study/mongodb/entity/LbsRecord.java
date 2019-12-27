package com.study.mongodb.entity;

import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document
public class LbsRecord {

    private String id;

    private String title;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private double[] loc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double[] getLoc() {
        return loc;
    }

    public void setLoc(double[] loc) {
        this.loc = loc;
    }

    public LbsRecord() {
    }

    public LbsRecord(String title, double[] loc) {
        this.title = title;
        this.loc = loc;
    }

    @Override
    public String toString() {
        return String.format(
            "Customer[id=%s, title='%s', loc='%s']",
            id, title, Arrays.toString(loc));
    }
}
