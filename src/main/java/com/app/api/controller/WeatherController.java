package com.app.api.controller;

import com.app.api.dto.Weather;
import com.app.api.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by Ashutosh Sharma
 */
@RestController
@RequestMapping(value = "/weather", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/{city}")
    public Weather getWeather(@PathVariable("city") String city) throws Exception {
        return weatherService.getWeather(city);

    }

    @PostMapping
    public Weather getWeatherViaPost(@RequestBody Map<String, Object> payload) throws Exception {
        return weatherService.getWeather(payload.get("city").toString());
    }

}
