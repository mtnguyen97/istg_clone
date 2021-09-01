package com.example.istg;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HttpClientConfig {

    @Bean("httpClient")
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public CloseableHttpClient getClient() {
        return HttpClients.createDefault();
    }
}
