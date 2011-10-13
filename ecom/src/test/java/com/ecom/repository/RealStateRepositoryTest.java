package com.ecom.repository;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.common.utils.ImageUtils;
import com.ecom.domain.RealState;
import com.ecom.test.AbstractIntegrationTest;

public class RealStateRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    RealStateRepository repository;
    
    @Before
    public void purgeRepository() {
        repository.deleteAll();
        super.setUp();
    }
    
    @Test
    public void testSaveRealState() throws Exception {
        
        
        for (int i=1;i < 10; i++) {
            RealState appartment = new RealState();
            appartment.setTitle(i + " zimmer appartment");            
            
            BufferedImage image = null;

            
            if (i % 2 == 0) {
                appartment.setBalconyAvailable(false);
                appartment.setTotalRooms(2d);
                appartment.setBedRooms(1);     
                appartment.setCost(600);
                appartment.setSize(50.5d + 1);
                appartment.setDescription("2 rooms appartment, schöne wohnlage");
                image = ImageIO.read(readImageFile("test-images/test_image_" + i  + ".jpg"));
                
            } else if (i % 3 == 0){
                appartment.setBalconyAvailable(true);
                appartment.setTotalRooms(3d);
                appartment.setBedRooms(2);
                appartment.setCost(800);
                appartment.setSize(67d + i*1.1);
                appartment.setDescription("3 zimmer appartment, EBK, familien freündlich");
                image = ImageIO.read(readImageFile("test-images/test_image_" + i + ".jpg"));
                
            } else if (i % 4 == 0){
                appartment.setBalconyAvailable(true);
                appartment.setTotalRooms(4d);
                appartment.setBedRooms(3);
                appartment.setCost(600 + i* 20);
                appartment.setSize(80 + i*1.5);
                appartment.setDescription("4 zimmer hell, ruhig, provisinfrei mit panoromablick");
                image = ImageIO.read(readImageFile("test-images/test_image_" + i + ".jpg"));
                
            } else if (i % 5 == 0){
                appartment.setBalconyAvailable(true);
                appartment.setTotalRooms(5d);
                appartment.setBedRooms(4);
                appartment.setCost(800 + i* 20);
                appartment.setSize(101 + i*1.5);
                appartment.setDescription("5 zimmer groß, ruhig, provisinfrei mit panoromablick");
                image = ImageIO.read(readImageFile("test-images/test_image_" + i  + ".jpg"));
            } else {
                appartment.setBalconyAvailable(false);
                appartment.setTotalRooms(1d);
                appartment.setBedRooms(1);
                appartment.setCost(500 + i* 20);
                appartment.setSize(50.5d);
                image = ImageIO.read(readImageFile("test-images/test_image_1.jpg"));
                
            }    
            
            appartment.setCategoryId(1);
            appartment.setTypeId(1);            
            
            image = ImageUtils.resize(image, 60, 60);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            ImageIO.write(image, "jpeg", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();
            appartment.setTitleImage(imageBytes);
            
            realStates.add(appartment);
            
        }
        repository.save(realStates);
        
        assert(repository.count() > 0);
    }
}
