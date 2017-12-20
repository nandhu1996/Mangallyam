package com.giga_appz.newmatrimony;

/**
 * Created by NANDHU on 23-09-2017.
 */

public class Matcheslist {
    private String name,email,home,mobile,id,photo;
    public Matcheslist(String name, String email, String mobile, String home, String id, String photo){
        this.name=name;
        this.email=email;
        this.mobile=mobile;
        this.id=id;
        this.home=home;
        this.photo=photo;
    }
    public String getName(){
        return name;
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
    public String getEmail(){
        return  email;
    }
    /*public  String getHome(){
        return home;
    }*/
    public String getMobile(){
        return mobile;
    }
}
