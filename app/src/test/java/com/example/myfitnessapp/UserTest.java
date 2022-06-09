package com.example.myfitnessapp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
    public static final String TEST_EMAIL = "testGetEmail";
    public static final String TEST_PASSWORD = "testGetPassword";
    public static final long TEST_WEIGHT = 70;
    public static final long TEST_HEIGHT = 180;
    public static final long TEST_TARGETWEIGHT = 65;
    public static final String TEST_DAY = "testGetDay";
    public static final String TEST_MONTH = "testGetMonth";
    public static final String TEST_YEAR = "testGetYear";

    private User user;

    @Before
    public void setUp() throws Exception {

        user = new User();
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        user.setWeight(TEST_WEIGHT);
        user.setHeight(TEST_HEIGHT);
        user.setTargetWeight(TEST_TARGETWEIGHT);
        user.setDay(TEST_DAY);
        user.setMonth(TEST_MONTH);
        user.setYear(TEST_YEAR);
    }

    @Test
    public void testGetEmail() {
        String s="";
        s=user.getEmail();
        assertEquals(s,TEST_EMAIL);
    }

    @Test
    public void testGetPassword() {
        String s="";
        s=user.getPassword();
        assertEquals(s,TEST_PASSWORD);
    }

    @Test
    public void testGetWeight() {
        long l;
        l=user.getWeight();
        assertEquals(l,TEST_WEIGHT);
    }

    @Test
    public void testGetHeight() {
        long l;
        l=user.getHeight();
        assertEquals(l,TEST_HEIGHT);
    }

    @Test
    public void testGetTargetWeight() {
        long l;
        l=user.getTargetWeight();
        assertEquals(l,TEST_TARGETWEIGHT);
    }

    @Test
    public void testGetDay() {
        String s="";
        s=user.getDay();
        assertEquals(s,TEST_DAY);
    }

    @Test
    public void testGetMonth() {
        String s="";
        s=user.getMonth();
        assertEquals(s,TEST_MONTH);
    }

    @Test
    public void testGetYear() {
        String s="";
        s=user.getYear();
        assertEquals(s,TEST_YEAR);
    }

}
