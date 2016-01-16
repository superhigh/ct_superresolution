package com.lm.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hhur on 16-1-5.
 */
public class ImageShow {
    public static int getImage(OutputStream out,String path){
        int size=-1;
        FileInputStream ins=null;
        try{
            ins=new FileInputStream(path);
            byte[] bytes=new byte[1024];
            int len=0;
            while((len=ins.read(bytes))!=-1){
                out.write(bytes,0,len);
                size+=len;
            }
            out.flush();
            out.close();
            ins.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }
}
