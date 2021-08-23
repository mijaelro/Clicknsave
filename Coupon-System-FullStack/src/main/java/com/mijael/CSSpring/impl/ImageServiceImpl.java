package com.mijael.CSSpring.impl;

import com.mijael.CSSpring.beans.Image;
import com.mijael.CSSpring.exceptions.IllegalActionException;
import com.mijael.CSSpring.repos.ImageRepository;
import com.mijael.CSSpring.services.ImageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
@Getter
public class ImageServiceImpl  implements ImageService {

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public Image addImage(byte[] bytes) throws IllegalActionException {
        Image image=new Image(bytes);
        try {
            image = imageRepository.saveAndFlush(image);
        }catch (Exception e){
            throw new IllegalActionException("Error uploading file");
        }
        return image;
    }
}
