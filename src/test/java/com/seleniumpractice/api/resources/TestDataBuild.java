package com.seleniumpractice.api.resources;

import java.util.ArrayList;
import java.util.List;

import com.seleniumpractice.api.pojo.AddPlace;
import com.seleniumpractice.api.pojo.Location;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address) {
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress(address);
        place.setLanguage(language);
        place.setPhone_number("(+91) 983 893 3937");
        place.setWebsite("http://rahulshettyacademy.com");
        place.setName(name);

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        place.setTypes(types);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        place.setLocation(location);

        return place;
    }
}