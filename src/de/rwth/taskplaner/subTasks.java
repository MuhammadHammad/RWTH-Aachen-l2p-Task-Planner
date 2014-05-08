package de.rwth.taskplaner;

public class subTasks {


    //private variables
    int st_id;
    String st_name;
    String st_enddate;
    String st_endtime;
     
    // Empty constructor
    public subTasks(){
         
    }
    // constructor
    public void subTasks(int id, String name ,String enddate, String endtime){
        this.st_id = id;
        this.st_name = name;
        this.st_enddate = enddate;
        this.st_endtime = endtime;
    }
     
   
    // getting ID
    public int getID(){
        return this.st_id;
    }
     
    // setting id
    public void setID(int id){
        this.st_id = id;
    }
     
    // getting name
    public String getName(){
        return this.st_name;
    }
     
    // setting name
    public void setName(String name){
        this.st_name = name;
    }
     
    // getting phone number
    public String getenddate(){
        return this.st_enddate;
    }
     
    // setting phone number
    public void setenddate(String enddate){
        this.st_enddate = enddate;
    }
    public String getendtime(){
        return this.st_endtime;
    }
     
    // setting phone number
    public void setendTime(String endtime){
        this.st_endtime = endtime;
    }
}