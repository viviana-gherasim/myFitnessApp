package com.example.myfitnessapp;

import static org.junit.Assert.assertEquals;

import com.example.myfitnessapp.Manager.City;

import org.junit.Before;
import org.junit.Test;

public class CityTest {

    private static final String CITY_NAME = "cityName";
    private City city;

    @Before
    public void setUp() throws Exception {
        city =  new City();
        city.setName(CITY_NAME);
    }

    @Test
    public void testGetCityName() {
        String s = "";
        s=city.getName();
        assertEquals(s,CITY_NAME);
    }
}
