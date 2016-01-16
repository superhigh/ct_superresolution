package com.lm.service.impl;

import com.lm.entity.ImageEntity;
import com.lm.mapping.ImageMapper;
import com.lm.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hhur on 16-1-8.
 */
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageMapper imageMapper;

    public ImageMapper getImageMapper(){return this.imageMapper;}

    public void setImageMapper(ImageMapper imageMapper){this.imageMapper=imageMapper;}

    @Override
    public ImageEntity getImageEntityById(String id) {
        return this.imageMapper.getImageEntityById(id);
    }

    @Override
    public List<ImageEntity> getImageEntities() {
        return this.imageMapper.getImageEntities();
    }

    @Override
    public void deleteImageEntitiesById(String id) {
        this.imageMapper.deleteImageEntitiesById(id);
    }

    @Override
    public void insertImageEntity(ImageEntity imageEntity) {
        this.imageMapper.insertImage(imageEntity);
    }
}
