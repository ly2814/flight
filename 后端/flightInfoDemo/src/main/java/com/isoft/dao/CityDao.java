package com.isoft.dao;

import com.isoft.pojo.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City,Integer> {
}
