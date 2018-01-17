package com.giga_appz.newmatrimony;

import android.graphics.Bitmap;

/**
 * Created by NANDHU on 20-12-2017.
 */

public class Profilesoffline {
    String id,age,education,location;
    Bitmap photo;
    public Profilesoffline(String string, String cursorString){

    }
    public Profilesoffline(String id, String age, String education, Bitmap photo, String location){
        this.id=id;
        this.age=age;
        this.education=education;
        this.photo=photo;
        this.location=location;
    }
    public String getId(){
        return this.id;
    }
    public void setEducation(String education){
        this.education=education;
    }
    public String getEducation(){
        return this.education;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location=location;
    }
    public Bitmap getPhoto(){
        return this.photo;
    }
    public void setPhoto(Bitmap photo){
        this.photo=photo;
    }
    public String getAge(){
        return this.age;
    }
    public void setAge(String age){
        this.age=age;
    }
}
