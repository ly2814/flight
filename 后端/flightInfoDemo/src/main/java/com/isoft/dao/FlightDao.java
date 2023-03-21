package com.isoft.dao;

import com.isoft.pojo.City;
import com.isoft.pojo.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightDao extends JpaRepository<Flight,Integer> {
    //更新JPQL
    @Transactional
    @Query(value = "update Flight set flightnum=?2,flydate=?3,starttime=?4,flytime=?5,startcity=?6,endcity=?7,seatnum=?8 where flightid=?1",nativeQuery = false)
    @Modifying
    int updateByJPQL(Integer flightid, String flightnum, Date flydate, String starttime, String flytime, City startcity, City endcity, Integer seatnum);

  @Query(value = "SELECT * from flightinfo WHERE startcity=?1 AND endcity=?2",nativeQuery = true)
   List<Flight> select(int startcity,int endcity);
}
