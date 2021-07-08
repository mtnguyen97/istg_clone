package com.example.istg;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CloudinaryConfig {

    @Bean("cloudinary")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "mtnguyen97",
                "api_key", "772597228129484",
                "api_secret", "1mKoUdvouTF4bCr6AT6gG01_osY"));
    }

}
