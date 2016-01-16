package com.lm.utils;

import java.awt.image.BufferedImage;

/**
 * Created by hhur on 16-1-14.
 */
public class ImageFilter {
    private float contrast=0.7f;
    private float brightness=1.0f;
    public ImageFilter(){}
    public  BufferedImage filter(BufferedImage src){
        int width=src.getWidth();
        int height=src.getHeight();
        BufferedImage dest=new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
        int[] inPixels = new int[width*height];
        int[] outPixels = new int[width*height];
        src.getRGB( 0, 0, width, height, inPixels, 0, width );

        // calculate RED, GREEN, BLUE means of pixel
        int index = 0;
        for(int row=0; row<height; row++) {
            int tb = 0;
            for(int col=0; col<width; col++) {
                index = row * width + col;

                tb = inPixels[index] & 0xff;
                tb= (int) ( 255*0.0625*(Math.pow(17,((double)tb/255))-1));
//                tb= (int) (c*(Math.pow(d,tb)-1));


                outPixels[index] = (tb << 24) | (clamp(tb) << 16) | (clamp(tb) << 8) | clamp(tb);
            }
        }
        dest.setRGB(0,0,width,height,outPixels,0,width);
        return dest;
    }
    public void setContrast(float contrast){
        this.contrast=contrast;
    }
    public float getContrast(){
        return this.contrast;
    }
    public void setBrightness(float brightness){
        this.brightness=brightness;
    }
    public float getBrightness(){
        return this.brightness;
    }
    public int clamp(int value){
        return value>=255?255:(value<0?0:value);
    }
}
