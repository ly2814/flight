package com.isoft.utins;

import com.isoft.dao.FlightDao;
import com.isoft.pojo.Flight;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileDao {
    @Autowired
    public static void output(List<Flight> list, String fileName){
//        fileName="d:/file/myFlightdata.xlsx";
        String[] titles = {"航班ID","航班号","飞行日期","发出时间","飞行时间","始发地","目的地","座位总数"};
        Workbook workbook = new XSSFWorkbook();
        Sheet mySheet = workbook.createSheet("航班表");
        Row titleRow = mySheet.createRow(0);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = titleRow.createCell(i);
            cell.setCellValue(titles[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            Row row = mySheet.createRow(i+1);
            Flight flight = list.get(i);
            row.createCell(0).setCellValue(flight.getFlightid());
            row.createCell(1).setCellValue(flight.getFlightnum());
            row.createCell(2).setCellValue(flight.getFlydate());
            row.createCell(3).setCellValue(flight.getStarttime());
            row.createCell(4).setCellValue(flight.getFlytime());
            row.createCell(5).setCellValue(flight.getStartcity().getCityid());
            row.createCell(6).setCellValue(flight.getEndcity().getCityid());
            row.createCell(7).setCellValue(flight.getSeatnum());
        }
        try {
            workbook.write(new FileOutputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
