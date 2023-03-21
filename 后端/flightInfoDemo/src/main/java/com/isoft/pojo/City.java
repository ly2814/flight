package com.isoft.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cityinfo")
public class City implements Serializable {
    @Id
    private Integer cityid;
    private String cityname;

    public City(Integer cityid, String cityname) {
        this.cityid = cityid;
        this.cityname = cityname;
    }

    public City() {

    }
}
