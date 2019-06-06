package com.revature.utils;

import java.util.Date;

public class Josh4J {
    private static Josh4J k;
    private Josh4J() {};
    public static Josh4J getInstance() {
        if(k==null) {
            k = new Josh4J();
            
        }
        return k;
    }
    public static void info(String info) {
        Date d = new Date();
        System.out.println(d.getHours() + " : " + d.getMinutes() + " : " + d.getSeconds() + " " + info);
    }
}
