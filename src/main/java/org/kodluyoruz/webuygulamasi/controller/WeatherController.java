package org.kodluyoruz.webuygulamasi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/havadurumu")
public class WeatherController {

    @Value(value = "${thirdapi.weather.url}")
    private String weatherUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/{id}", produces = { "application/json" })
    public ResponseEntity<?> getWeatherInfo(@PathVariable Integer id) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(weatherUrl + id, HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String, Object>>(){});
    }
}
