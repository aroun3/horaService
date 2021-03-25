package com.visitor.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CurrentDate {

    private String formater;

    public CurrentDate(String formater){
        this.formater = formater;
    }

    public Date getCurrentDate(){
       DateFormat format = new SimpleDateFormat(this.formater); //"yyyy-MM-dd hh:mm:ss a"
        
        //obtenir la date courante
       Date date = new Date();
       Calendar calendar = Calendar.getInstance();

       //System.out.println("==================== : "+ calendar.getTime();

       return date ;//format.format(date);
    }

    public String getCurrentTime(){

        DateFormat format = new SimpleDateFormat(this.formater);

        //obtenir l'heure courante
       Calendar calendar = Calendar.getInstance();

       return format.format(calendar.getTime());
    }
}
