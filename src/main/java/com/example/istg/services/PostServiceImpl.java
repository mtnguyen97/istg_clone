package com.example.istg.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.Post;
import com.example.istg.commons.User;
import com.example.istg.repos.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository repo;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Iterable<Post> getAllPosts() {
        return repo.findAll();
    }

    @Override
    public Post getPost(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Post createPost(Post p, User user) {
        p.setPostedBy(user);
        p.setCreatedAt(new Date());
        if (p.getImages().isEmpty()) {
            return null;
        }
        p.setImages(p.getImages().stream().map(cloudinaryService::upload).collect(Collectors.toList()));
        p.setId(null);
        p.setDeletedAt(null);
        p = repo.save(p);
        return p;
    }

    @Override
    public Post updatePost(Post p) {
        if (repo.existsById(p.getId())) {
            p = repo.save(p);
            return p;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void deletePost(Long id, User user) {
        Post post;
        if ((post = repo.findById(id).get()).getPostedBy().equals(user)) {
            post.getImages().forEach(img -> {
                try {
                    cloudinaryService.destroy(img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            repo.delete(post);
        }
        throw new NoSuchElementException();
    }

}
