package com.jy.study.dataaccess.h2.domain;

import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(name = "cr_time")
    private Long crTime;
    @Column(name = "up_time")
    private Long upTime;
    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCrTime() {
        return crTime;
    }

    public void setCrTime(Long crTime) {
        this.crTime = crTime;
    }

    public Long getUpTime() {
        return upTime;
    }

    public void setUpTime(Long upTime) {
        this.upTime = upTime;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
            "id=" + id +
            ", crTime=" + crTime +
            ", upTime=" + upTime +
            ", version=" + version +
            '}';
    }
}
