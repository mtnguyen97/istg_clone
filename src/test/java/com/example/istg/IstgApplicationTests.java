package com.example.istg;

import com.example.istg.commons.User;
import com.example.istg.services.CloudinaryService;
import com.example.istg.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.io.IOException;

@SpringBootTest
class IstgApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private UserService userService;

    @Test
    void testCloudinary() throws IOException {
        String image = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";
        String id = cloudinaryService.upload(image);
        Assert.notNull(id, "Unable to upload images on Cloudinary");
        cloudinaryService.destroy(id);
    }

//    @Test
//    void testChatEngine() throws IOException {
//        User u = new User();
//        u.setUsername("testUser");
//        userService.createChatCred(u);
//    }

}
