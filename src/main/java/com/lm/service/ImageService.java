package com.lm.service;

import com.lm.entity.ImageEntity;

import java.util.List;

/**
 * Created by hhur on 16-1-8.
 */
public interface ImageService {
    ImageEntity getImageEntityById(String id);
    List<ImageEntity> getImageEntities();
    void deleteImageEntitiesById(String id);
    void insertImageEntity(ImageEntity imageEntity);
}
