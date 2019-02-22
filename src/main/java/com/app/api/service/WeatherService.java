package com.app.api.service;

import com.app.api.dto.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;

/**
 * Created by Ashutosh.
 */
@Service
public class WeatherService {

    private final String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    WeatherAppProperties weatherAppProperties;

    private final String apiKey;

    public WeatherService(WeatherAppProperties weatherAppProperties) {
        //this.restTemplate.setErrorHandler(new OWMResponseErrorHandler());
        this.apiKey = weatherAppProperties.getApi().getKey();
        this.apiUrl = weatherAppProperties.getApi().getUrl();
    }

    @Cacheable("weather")
    public Weather getWeather(String city) throws Exception {
        Weather weather = null;
        if (validParameters(city)) {
            URI url = new UriTemplate(this.apiUrl).expand(city, this.apiKey);
            weather = invoke(url, Weather.class);
        }
        return weather;
    }

    private boolean validParameters(String city) {
        return city != null && !"".equals(city) && apiKey != null && !"".equals(apiKey) && apiUrl != null
                && !"".equals(apiUrl);
    }

    private <T> T invoke(URI url, Class<T> responseType) throws Exception{
        T weather = null;
        RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<T> exchange = this.restTemplate.exchange(request, responseType);

        weather = exchange.getBody();

        return weather;
    }

}
