package com.isoft.controller;

import com.isoft.dao.CityDao;
import com.isoft.dao.FlightDao;
import com.isoft.pojo.City;
import com.isoft.pojo.Flight;
import com.isoft.utins.Jsondata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("city")
@RestController
@CrossOrigin
public class CityCon {
    @Autowired
    CityDao cityDao;
    @GetMapping("/info")
    public Jsondata flightinfo(){
        List<City> cityList = cityDao.findAll();
        return  Jsondata.Sueeess(200,cityList,"请求成功");
    }
}
