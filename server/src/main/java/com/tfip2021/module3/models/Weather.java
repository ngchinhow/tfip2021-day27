package com.tfip2021.module3.models;

import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Component
public class Weather {
    private String id;
    private String main;
    private String description;
    private String iconUrl;
    private Float temp;
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMain() { return main; }
    public void setMain(String main) { this.main = main; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() { return iconUrl; }
    public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }

    public Float getTemp() { return temp; }
    public void setTemp(Float temp) { this.temp = temp; }
    
    public static Weather fromJson(
        JsonObject weatherObject,
        JsonObject measurementsObject
    ) {
        final Weather w = new Weather();
        w.setId(String.valueOf(weatherObject.getInt("id")));
        w.setMain(weatherObject.getString("main"));
        w.setDescription(weatherObject.getString("description"));
        w.setIconUrl(Weather.buildIconUrl(weatherObject.getString("icon")));
        w.setTemp((float) measurementsObject.getJsonNumber("temp").doubleValue());
        return w;
    }

    public String toJsonString() {
        return this.toJson().toString();
    }

    public JsonObject toJson() {
        JsonObjectBuilder weatherBuilder = Json.createObjectBuilder();
        weatherBuilder.add("id", this.getId());
        weatherBuilder.add("main", this.getMain());
        weatherBuilder.add("description", this.getDescription());
        weatherBuilder.add("iconUrl", this.getIconUrl());
        weatherBuilder.add("temp", this.getTemp());
        return weatherBuilder.build();
    }

    public static String buildIconUrl(String icon) {
        return String.format("http://openweathermap.org/img/wn/%s@2x.png", icon);
    }
}
