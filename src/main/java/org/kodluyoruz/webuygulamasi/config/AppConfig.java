package org.kodluyoruz.webuygulamasi.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public Mapper dozerMapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
