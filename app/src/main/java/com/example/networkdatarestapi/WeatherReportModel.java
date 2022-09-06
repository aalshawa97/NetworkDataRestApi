package com.example.networkdatarestapi;

public class WeatherReportModel {

    /*
    {"coord":{"lon":34.4667,"lat":31.5},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"base":"stations","main":{"temp":299.75,"feels_like":299.75,"temp_min":299.75,"temp_max":299.75,"pressure":1009,"humidity":68,"sea_level":1009,"grnd_level":1004},"visibility":10000,"wind":{"speed":2.69,"deg":334,"gust":3},"clouds":{"all":0},"dt":1662398575,"sys":{"type":2,"id":2002441,"country":"PS","sunrise":1662348016,"sunset":1662393720},"timezone":10800,"id":281133,"name":"Gaza","cod":200}
     */
    //"sys":{"type":2,"id":2002441,"country":"PS","sunrise":1662348016,"sunset":1662393720},"timezone":10800,"id":281133,"name":"Gaza","cod":200
    private float longitude = 0.0F;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public void setWeather_state_abbr(String weather_state_abbr) {
        this.weather_state_abbr = weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public void setWind_direction_compass(String wind_direction_compass) {
        this.wind_direction_compass = wind_direction_compass;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(float seaLevel) {
        this.seaLevel = seaLevel;
    }

    public float getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(float groundLevel) {
        this.groundLevel = groundLevel;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    private float latitude = 0.0F;
    private int id;
    private String weather_state_name;
    private String weather_state_abbr;

    @Override
    public String toString() {
        return "WeatherReportModel{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", id=" + id +
                ", weather_state_name='" + weather_state_name + '\'' +
                ", weather_state_abbr='" + weather_state_abbr + '\'' +
                ", wind_direction_compass='" + wind_direction_compass + '\'' +
                ", temperature=" + temperature +
                ", temperatureMin=" + temperatureMin +
                ", temperatureMax=" + temperatureMax +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", seaLevel=" + seaLevel +
                ", groundLevel=" + groundLevel +
                ", visibility=" + visibility +
                ", distance=" + distance +
                '}';
    }

    private String wind_direction_compass;
    private float temperature = 0.0F;
    private float temperatureMin = 0.0F;
    private float temperatureMax = 0.0F;
    private float pressure = 0.0F;
    private float humidity = 0;
    private float seaLevel = 0;
    private float groundLevel = 0;
    private float visibility = 0;
    private float distance = 0.0F;
/*
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }*/

    public WeatherReportModel(int id, String weather_state_name, String weather_state_abbr, float longitude, float latitude, float temperature, String wind_direction_compass){
        this.id = id;
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;

    }
}
