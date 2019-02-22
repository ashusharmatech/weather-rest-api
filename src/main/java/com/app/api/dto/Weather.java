package com.app.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Weather implements Serializable {

    private String name;

    private long timestamp;

    private double temperature;

    private Integer weatherId;

    private String weatherIcon;

    private String weatherMain;

    private String weatherDescription;

    private String countryCode;

    private long sunrise;

    private long sunset;

    private double longitude;

    private double latitude;

    private String base;

    private double windSpped;

    private long windDeg;

    private double pressure;

    private double humidity;

    private double tempMin;

    private double tempMax;

    private double seaLevel;

    private double grndLevel;


    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("timestamp")
    public long getTimestamp() {
        return this.timestamp;
    }

    @JsonSetter("dt")
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperatureKelvin) {
        this.temperature = temperatureKelvin;
    }


    public Integer getWeatherId() {
        return this.weatherId;
    }

    public void setWeatherId(Integer weatherId) {
        this.weatherId = weatherId;
    }

    public String getWeatherIcon() {
        return this.weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @JsonSetter("base")
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public double getWindSpped() {
        return windSpped;
    }

    public void setWindSpped(double windSpped) {
        this.windSpped = windSpped;
    }

    public long getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(long windDeg) {
        this.windDeg = windDeg;
    }



    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }



    public double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(double grndLevel) {
        this.grndLevel = grndLevel;
    }

    @JsonProperty("wind")
    public void setWind(Map<String, Object> wind) {
        setWindSpped(Double.parseDouble(wind.get("speed").toString()));
        if(wind.get("deg")!=null)
            setWindSpped(Double.parseDouble(wind.get("deg").toString()));
    }

    @JsonProperty("sys")
    public void setSys(Map<String, Object> sys) {
        setCountryCode((String) sys.get("country"));
        setSunrise((int) sys.get("sunrise"));
        setSunset((int) sys.get("sunset"));

    }
    @JsonProperty("weather")
    public void setWeather(List<Map<String, Object>> weatherEntries) {
        Map<String, Object> weather = weatherEntries.get(0);
        setWeatherId((Integer) weather.get("id"));
        setWeatherIcon((String) weather.get("icon"));
        setWeatherMain((String) weather.get("main"));
        setWeatherDescription((String) weather.get("description"));
    }

    @JsonProperty("sunrise")
    public long getSunrise() {
        return this.sunrise;
    }

    @JsonSetter("sunrise")
    public void setSunrise(long timestamp) {
        this.sunrise = timestamp;
    }

    @JsonProperty("sunset")
    public long getSunset() {
        return this.sunset;
    }

    @JsonSetter("sunset")
    public void setSunset(long timestamp) {
        this.sunset = timestamp;
    }

    @JsonProperty("main")
    public void setMain(Map<String, Object> main) {
        setTemperature(Double.parseDouble(main.get("temp").toString()));
        setPressure(Double.parseDouble(main.get("pressure").toString()));
        setHumidity(Double.parseDouble(main.get("humidity").toString()));
        setTempMin(Double.parseDouble(main.get("temp_min").toString()));
        setTempMax(Double.parseDouble(main.get("temp_max").toString()));
        //setSeaLevel(Double.parseDouble(main.get("sea_level").toString()));
        //setPressure(Double.parseDouble(main.get("grnd_level").toString()));
    }


}
