package com.isoft.pojo;

import com.isoft.dao.FlightDao;
import com.isoft.utins.FileDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlightInfoDemoApplicationTests {
    @Autowired
    FlightDao flightDao;
    @Test
    void contextLoads() {
        FileDao fileDao = new FileDao();
        String fileName="d:/file/myFlightdata.xlsx";
        fileDao.output(flightDao.findAll(),fileName);
        //查询所有
//        List<Flight> all = flightDao.findAll();
//        for (Flight item:all
//             ) {
//            System.out.println(item);
//
//        }
        //查询单条
//        flightDao.findById(Integer.valueOf("20"));
        //修改
//        flightDao.updateByJPQL(Integer.valueOf(22),"001",new Date(),"22:01","1h",new City(1,""),new City(3,""),Integer.valueOf(22));
        //全部删除
//        flightDao.deleteAll();
        //添加
//        Flight flight = new Flight();
//        flight.setFlightnum("001");
//        Date date = new Date();
//        flight.setFlydate(date);
//        flight.setStarttime("21:07");
//        flight.setFlytime("2h");
//        flight.setStartcity(new City(Integer.valueOf(1),""));
//        flight.setEndcity(new City(Integer.valueOf(2),""));
//        flight.setSeatnum(Integer.valueOf(23));
//        String s = flight.toString();
//        System.out.println(s);
//        flightDao.save(flight);
    }

}
