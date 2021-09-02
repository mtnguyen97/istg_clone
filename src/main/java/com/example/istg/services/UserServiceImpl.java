package com.example.istg.services;

import com.example.istg.commons.ChatCred;
import com.example.istg.commons.User;
import com.example.istg.commons.Utils;
import com.example.istg.exceptions.DuplicatedUsernameOrEmailException;
import com.example.istg.repos.UserRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private @Autowired
    UserRepository repo;

    private CloseableHttpClient httpClient;

    private static final String PRIVATE_KEY = "c21f9028-c163-4934-9936-5df2e34a0384";

    private static final String URL = "https://api.chatengine.io/users/";

    @Autowired
    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll().stream().peek((u) -> {
            // hide users password
            u.setPassword("");
        }).collect(Collectors.toList());
    }

    @Override
    public User getUser(Long id) {
        User u = repo.findById(id).orElse(null);
        // hide user password
        if (u != null) {
            u.setPassword("");
        }
        return u;
    }

    @Override
    public User createUser(User u) throws DuplicatedUsernameOrEmailException {
        if (repo.existsByEmail(u.getEmail()) || repo.existsByUsername(u.getUsername())) {
            throw new DuplicatedUsernameOrEmailException();
        }
        u.setCreatedAt(new Date());
        u = repo.save(u);
        return u;
    }

    @Override
    public User updateUser(User u) {
        if (repo.existsById(u.getId())) {
            u = repo.save(u);
            return u;
        }
        throw new NoSuchElementException();
    }

    @Override
    public void deleteUser(Long id) {
        if (!repo.existsById(id)) {
            throw new NoSuchElementException();
        }
        repo.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public boolean isEmailExisted(String email) {
        return repo.existsByEmail(email);
    }

    @Override
    public ChatCred createChatCred(User user) throws IOException {
        HttpPost httpPost = new HttpPost(URL);
        httpPost.setHeader("PRIVATE-KEY", PRIVATE_KEY);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        Map<String, String> payload = new HashMap<>();
        payload.put("username", user.getUsername());
        final String secret = Utils.randomString(24);
        payload.put("secret", secret);
        httpPost.setEntity(new StringEntity(new ObjectMapper().writeValueAsString(payload)));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream stream = entity.getContent();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ChatCred chatCred = mapper.readValue(stream, ChatCred.class);
            if (chatCred != null && chatCred.getId() != null) {
                chatCred.setSecret(secret);
                user.setChatCred(chatCred);
                user = repo.save(user);
                return user.getChatCred();
            }
        }
        response.close();
        return null;
    }


}
