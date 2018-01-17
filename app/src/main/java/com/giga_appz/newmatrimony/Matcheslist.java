package com.giga_appz.newmatrimony;

/**
 * Created by NANDHU on 23-09-2017.
 */

public class Matcheslist {
    private String age,education,home,mobile,id,photo;
    public Matcheslist(String age, String education, String home, String id, String photo){
        this.age=age;
        this.education=education;
        this.id=id;
        this.home=home;
        this.photo=photo;
    }
    public String getAge(){
        return age;
    }
    public String getHome(){
        return home;
    }
    public String getId(){
        return id;
    }
    public String getPhoto(){
        return photo;
    }
    public String getEducation(){
        return  education;
    }
    /*public  String getHome(){
        return home;
    }*/
    public String getMobile(){
        return mobile;
    }
}
