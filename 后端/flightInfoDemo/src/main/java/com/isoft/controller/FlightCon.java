package com.isoft.controller;

import com.isoft.dao.FlightDao;
import com.isoft.pojo.City;
import com.isoft.pojo.Flight;
import com.isoft.utins.FileDao;
import com.isoft.utins.Jsondata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RequestMapping("api")
@RestController
@CrossOrigin
public class FlightCon{
    @Autowired
    FlightDao flightDao;
    @GetMapping("/info")
    public Jsondata flightinfo(int page,int pageSize){
        System.out.println(page);
        System.out.println(pageSize);
        Pageable pageable=PageRequest.of(page-1, pageSize);
        Page<Flight> page1=flightDao.findAll(pageable);
        return  Jsondata.Sueeess(200,page1,"请求成功");
    }
    @GetMapping("/search")
    public Jsondata search(int startCity,int endCity){
        List<Flight> searchRes = flightDao.select(startCity, endCity);
        return  Jsondata.Sueeess(200,searchRes,"请求成功");
    }
    @PostMapping("/insert")
    public  Jsondata insert(@RequestBody Flight flight){
        Flight save = flightDao.save(flight);
        if (save != null){
        return  Jsondata.Sueeess(200,null,"添加成功");
        }else {
            return Jsondata.File();
        }
    }
    @DeleteMapping("/deleteById")
    public Jsondata deleteById(int id){
        flightDao.deleteById(id);
        return Jsondata.Sueeess(200,"","删除成功");
    }
    @DeleteMapping("/deleteAll")
    public Jsondata deleteById(){
        flightDao.deleteAll();
        return Jsondata.Sueeess(200,"","删除全部成功");
    }
@PutMapping("update")
public  Jsondata update(@RequestBody Flight flight){
    int res = flightDao.updateByJPQL(
            Integer.valueOf(flight.getFlightid()),
            flight.getFlightnum(),
            flight.getFlydate(),
            flight.getStarttime(),
            flight.getFlytime(),
            new City(flight.getStartcity().getCityid(), ""),
            new City(flight.getEndcity().getCityid(), ""),
            Integer.valueOf(flight.getSeatnum()));
    if(res>0){
        return Jsondata.Sueeess(200,res,"更新成功");
    }
    return  Jsondata.File();
}
@GetMapping("output")
public  Jsondata output(){
        String fileName="d:/file/航班统计表.xlsx";
        List<Flight> all = flightDao.findAll();
        FileDao.output(all,fileName);
        return Jsondata.Sueeess(200,"","导出成功");
}
}
