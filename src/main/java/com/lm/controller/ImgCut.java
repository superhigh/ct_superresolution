package com.lm.controller;

import com.lm.utils.ImgUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hhur on 16-1-7.
 */
public class ImgCut {
    public static void cutImg(OutputStream outputStream, String path , int x, int y, int w, int h){
        try{
            Image img;
            ImageFilter cropFilter;
            BufferedImage bufferedImage= ImageIO.read(new File(path));
            int width=bufferedImage.getWidth();
            int height=bufferedImage.getHeight();
            if(width>w&&height>h){
                Image image=bufferedImage.getScaledInstance(width,height,Image.SCALE_REPLICATE);
//                int x1=x*width/400;
//                int y1=y*height/270;
//                int w1=w*width/400;
//                int h1=h*height/270;
                cropFilter=new CropImageFilter(x,y,w,h);
                img=Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(),cropFilter));
                ImgUtils imgUtils=new ImgUtils(img);
                ImageIO.write(imgUtils.getBufferedImage(),"bmp",outputStream);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
