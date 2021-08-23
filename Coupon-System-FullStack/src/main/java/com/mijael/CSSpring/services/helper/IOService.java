package com.mijael.CSSpring.services.helper;

import com.mijael.CSSpring.beans.Image;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Data
@Service
public class IOService {

    public Image generateImage(String imagePath) throws IOException {
        File file = new File("src/main/resources/static/images/"+imagePath+"");
        byte[] bFile = new byte[(int) file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        int no_bytes_read =  fileInputStream.read(bFile);
        fileInputStream.close();
        return new Image(bFile);
    }









}
