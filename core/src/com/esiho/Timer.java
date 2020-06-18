package com.esiho;

import java.util.concurrent.TimeUnit;

public class Timer {
    public static void timer(Integer duree){
        try{
            TimeUnit.MILLISECONDS.sleep(duree);
        }catch(InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
