package com.giga_appz.newmatrimony;

/**
 * Created by NANDHU on 20-12-2017.
 */

public class Profilesoffline {
    String id,name;
    public Profilesoffline(){

    }
    public Profilesoffline(String id,String name){
        this.id=id;
        this.name=name;
    }
    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
}
