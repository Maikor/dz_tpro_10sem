package com.springapp.adapterPattern;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mkorshun on 4/23/2015.
 */
public class MikeLogging {
    public void showAction () {
        System.out.println("Method from MikeLogging was called");
        DateFormat dateFormat = new SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();
        Integer reminder = 30 - Integer.parseInt(dateFormat.format(cal.getTime()));
        System.out.println("Days before deadline: " + reminder);
    }
}
