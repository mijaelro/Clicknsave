package com.mijael.CSSpring.services;

import com.mijael.CSSpring.beans.Image;
import com.mijael.CSSpring.exceptions.IllegalActionException;
public interface ImageService {
    Image addImage(byte[] bytes) throws IllegalActionException;

}
