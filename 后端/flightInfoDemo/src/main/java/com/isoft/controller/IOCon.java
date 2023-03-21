package com.isoft.controller;

import com.isoft.dao.FlightDao;
import com.isoft.pojo.Flight;
import com.isoft.utins.FileDao;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RequestMapping("io")
@Controller
@CrossOrigin
public class IOCon {
    @Autowired
    FlightDao flightDao;
    @GetMapping("/download")
    public ResponseEntity<Byte[]> download(String filename,HttpServletRequest req) throws UnsupportedEncodingException {
        //导出
        String fileName="d:/file/航班统计表.xlsx";
        List<Flight> all = flightDao.findAll();
        FileDao.output(all,fileName);
        //
        String downloadPath="d:/file";
        System.out.println(filename);
        //路径/文件名字
        File file=new File(downloadPath+File.separator+getFileName(req, filename));
        filename = URLEncoder.encode(filename, "utf-8");//设置url里面文件名编码
        HttpHeaders httpHeaders=new HttpHeaders();//生成一个响应头对象
        httpHeaders.setContentDispositionFormData("attachment", filename);//告诉浏览器义下载方式打开
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);//设置下载形式告诉浏览器是什么类型比如字符流字节流
        ResponseEntity entity=null;
        try {
            entity = new ResponseEntity(FileUtils.readFileToByteArray(file), httpHeaders, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            entity = new ResponseEntity(e.getMessage().getBytes(StandardCharsets.UTF_8),httpHeaders,HttpStatus.EXPECTATION_FAILED);
        }
        return entity;
    }
    public String getFileName(HttpServletRequest request, String filename) throws UnsupportedEncodingException {
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};//获取是那个浏览器请求
        String userAge=request.getHeader("User-Agent");//获取请求头信息
        for (String str: IEBrowserKeyWords) {
            //contains（）当前列表若包含某元素，返回结果为true, 若不包含该元素，返回结果为false。
            if(userAge.contains(str)){//判断是不是这几个浏览器的请求
                return URLEncoder.encode(filename, "UTF-8").replace("+", " ");
            }
        }
        return  filename;
    }
}
