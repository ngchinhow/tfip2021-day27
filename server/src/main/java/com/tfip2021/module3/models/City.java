package com.tfip2021.module3.models;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Component
public class City {
    private String id;
    private String name;
    private String code;
    private List<Weather> weathers;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public List<Weather> getWeathers() { return weathers; }
    public void setWeathers(List<Weather> weathers) { this.weathers = weathers; }

    public static City fromJson(JsonObject cObject) {
        final City city = new City();
        city.setId(String.valueOf(cObject.getInt("id")));
        city.setName(cObject.getString("name"));
        city.setCode(cObject.getJsonObject("sys").getString("country"));
        city.setWeathers(
            cObject.getJsonArray("weather").stream().
                map(v -> Weather.
                    fromJson((JsonObject) v,cObject.getJsonObject("main"))).
                collect(Collectors.toList())
        );
        return city;
    }

    public String toJsonString() {
        return this.toJson().toString();
    }

    public JsonObject toJson() {
        JsonObjectBuilder cityBuilder = Json.createObjectBuilder();
        JsonArrayBuilder weatherBuilder = Json.createArrayBuilder();
        cityBuilder.add("id", this.getId());
        cityBuilder.add("name", this.getName());
        cityBuilder.add("code", this.getCode());
        this.weathers.forEach(v -> weatherBuilder.add(v.toJson()));
        cityBuilder.add("weathers", weatherBuilder);
        return cityBuilder.build();
    }
}
