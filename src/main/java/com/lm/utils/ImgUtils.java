package com.lm.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by hhur on 16-1-7.
 */
public class ImgUtils {
    private static  Image srcImage;
    private File srcFile;
    private String fileSuffix;
    private static int imageWidth;
    private static int imageHeight;
    private BufferedImage bufferedImage;
    private BufferedImage bufferedNew;
    public ImgUtils(String fileName) throws IOException {
        this(new File(fileName));
    }
    public ImgUtils(File srcFile) throws IOException {
        this(ImageIO.read(srcFile));
    }
    public ImgUtils(BufferedImage bufferedImage){
        this.bufferedImage=bufferedImage;
        imageWidth=bufferedImage.getWidth();
        imageHeight=bufferedImage.getHeight();

    }
    public ImgUtils(Image srcImage) throws IOException {
        imageHeight=srcImage.getHeight(null);
        imageWidth=srcImage.getWidth(null);
        this.bufferedImage=new BufferedImage(imageWidth*2,imageHeight*2,BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics=bufferedImage.createGraphics();

        graphics.drawImage(srcImage, 0, 0, imageWidth*2, imageHeight*2, null);
        graphics.dispose();
        graphics.setComposite(AlphaComposite.Src);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//        graphics.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        ImageFilter filter=new ImageFilter();
        this.bufferedNew=filter.filter(bufferedImage);
        ImageIO.write(bufferedNew,"bmp",new File("D://ct_image/a.bmp"));
//       graphics.setRenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//        this.bufferedImage=(BufferedImage)srcImage;
    }
//    public  BufferedImage resize(int w,int h) throws IOException{
//        if(imageWidth>=imageHeight){
//            h= (int) Math.round((imageHeight*w*1.0/imageWidth));
//        }
//        else
//            w=(int)Math.round((imageWidth*h*1.0/imageHeight));
//        BufferedImage newImage=new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);
//        Graphics graphics=newImage.getGraphics();
//        graphics.drawImage(srcImage,0,0,w,h,null);
//        graphics.dispose();
//        return newImage;
//    }
    public BufferedImage imageEnlarge2(){
        AffineTransform matrix=new AffineTransform();
        matrix.scale(2,2);
        AffineTransformOp op=new AffineTransformOp(matrix,AffineTransformOp.TYPE_BICUBIC);
        int width=imageWidth*2;
        int height=imageHeight*2;
        BufferedImage dstImage=new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
        op.filter(bufferedImage,dstImage);
        return dstImage;
    }
    public BufferedImage getBufferedImage(){
        return this.bufferedNew;
    }
}
