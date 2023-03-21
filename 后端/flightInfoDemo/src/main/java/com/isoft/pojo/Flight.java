package com.isoft.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "flightinfo")
public class Flight implements Serializable {
    @Id
    private int flightid;
    private String flightnum;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date flydate;
    private String starttime;
    private String flytime;
    @ManyToOne
    @JoinColumn(name = "startcity",referencedColumnName = "cityid")
    City startcity;
    @ManyToOne
    @JoinColumn(name = "endcity",referencedColumnName = "cityid")
    City endcity;
//    private  Integer startcity;
//    private  Integer endcity;
    private Integer seatnum;

    public Flight() {
    }
}
