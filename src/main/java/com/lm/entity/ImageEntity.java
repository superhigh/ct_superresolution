package com.lm.entity;

/**
 * Created by hhur on 16-1-8.
 */
public class ImageEntity {

    private String id;
    private String username;
    private String path;

    public String getId(){return this.id;}

    public void setId(String id){this.id=id;}

    public String getUsername(){return this.username;}

    public void setUsername(String username){this.username=username;}

    public String getPath(){return this.path;}

    public void setPath(String path){this.path=path;}

    public String toString(){

        return "ImageEntity [imageId="+id+",userName="+username+
                ",imagePath="+path+"]";

    }
}
