package com.example.istg.services;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloudinary.*;

import java.io.IOException;

@Service
public class CloudinaryService {

    private Cloudinary cloudinary;

    @Autowired
    public void setCloudinary(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    public String upload(String base64DataURI) {
        String id = cloudinary.randomPublicId();
        try {
            cloudinary.uploader().upload(base64DataURI,
                    ObjectUtils.asMap("public_id", id));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return id;
    }

    public String generateURL(String id) {
        if (id == null) {
            return null;
        }
        return cloudinary.url().publicId(id).generate();
    }

    public void destroy(String id) throws IOException {
        cloudinary.uploader().destroy(id, null);
    }


}
