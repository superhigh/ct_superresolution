package com.lm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hhur on 16-1-8.
 */
public class FileNameGenerator {
    public static String generateRandomFilename(){
        String fourRandom;
        int randomNum=(int)(Math.random()*10000);
        fourRandom=String.format("%04d",randomNum);
        StringBuilder stringBuilder=new StringBuilder();
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmSS");
        stringBuilder.append(sdf.format(date)).append(fourRandom);
        return stringBuilder.toString();
    }
}
