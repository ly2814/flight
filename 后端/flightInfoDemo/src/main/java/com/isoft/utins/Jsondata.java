package com.isoft.utins;

public class Jsondata {
    private  int clode;
    private  Object object;
    private  String mseeage;

    public int getClode() {
        return clode;
    }

    public void setClode(int clode) {
        this.clode = clode;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMseeage() {
        return mseeage;
    }

    public void setMseeage(String mseeage) {
        this.mseeage = mseeage;
    }
    public  Jsondata(int clode,Object object,String mseeage){
        this.clode=clode;
        this.object=object;
        this.mseeage=mseeage;
    }
    public  Jsondata(int clode,String mseeage){
        this.clode=clode;
        this.mseeage=mseeage;
    }
    public static  Jsondata Sueeess(int clode,Object object,String mseeage){
        return  new Jsondata(clode,object,mseeage);
    }
    public static Jsondata File(){
        return  new Jsondata(404,"请求失败");
    }
}
