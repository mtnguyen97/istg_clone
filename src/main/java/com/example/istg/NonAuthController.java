package com.example.istg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.istg.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.commons.Post;
import com.example.istg.services.UserService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/nonauth/api")
public class NonAuthController {
    @Autowired
    private UserService service;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/emailexisted")
    public Map<String, Object> checkUsableEmail(@RequestParam("email") String email) {
        Map<String, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("existed", service.isEmailExisted(email));
        return response;
    }

    @GetMapping("/image/{id}")
    public void getImage(@PathVariable String id, HttpServletResponse response) throws IOException {
//		try {
//			File f = new File(Post.IMG_DIR, id);
//			InputStreamResource resource = new InputStreamResource(new FileInputStream(f));
//			return ResponseEntity.ok().contentLength(f.length()).contentType(MediaType.APPLICATION_OCTET_STREAM)
//					.body(resource);
//		} catch (FileNotFoundException e) {
//			return ResponseEntity.notFound().build();
//		}
        String url = cloudinaryService.generateURL(id);
        response.sendRedirect(url);
    }

}
