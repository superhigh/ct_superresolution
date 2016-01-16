package com.lm.controller;

import com.lm.entity.ImageEntity;
import com.lm.service.ImageService;
import com.lm.utils.FileNameGenerator;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hhur on 16-1-8.
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    public ImageService getImageService(){return this.imageService;}
    public void setImageService(ImageService imageService){this.imageService=imageService;}

    @RequestMapping("/upload")
    public String upload(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageEntity imageEntity=new ImageEntity();
        DiskFileItemFactory dff=new DiskFileItemFactory();
        ServletFileUpload upload=new ServletFileUpload(dff);
        upload.setHeaderEncoding("utf-8");
        List fileList=null;
        try{
            fileList=upload.parseRequest(request);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        String name="";
        String extName="";
        Iterator<FileItem> it=fileList.iterator();
        while(it.hasNext()){
            FileItem item=it.next();
            if(!item.isFormField()){
                name=item.getName();
                long size=item.getSize();
                String type=item.getContentType();
                if(name!=null&&(!name.trim().equals(""))) {

                    if (name.lastIndexOf(".") >= 0) {
                        extName = name.substring(name.lastIndexOf("."));
                        String fileId=FileNameGenerator.generateRandomFilename();
                        String savePath = request.getSession().getServletContext().getRealPath("/") + "resources" + "/upload/"
                                + fileId;

                        File file = null;
                        do {
                            file = new File(savePath+extName);
                        } while (file.exists());
                        try {
                            item.write(file);
                            imageEntity.setId(fileId);
                            imageEntity.setUsername("zjubfd");
                            imageEntity.setPath("upload/"+fileId+extName);
                            imageService.insertImageEntity(imageEntity);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }



        return "faq";
    }
}
