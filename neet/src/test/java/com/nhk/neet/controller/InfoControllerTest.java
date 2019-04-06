package com.nhk.neet.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InfoControllerTest {

    private InfoController infoController;

    @Before
    public void setUp() {
        infoController = new InfoController();
    }

    @Test
    public void getInfo() {
        String info = infoController.getInfo();
        String[] brokenPieces = info.split(" ");
        String initials = Arrays.stream(brokenPieces)
                .map(s -> s.charAt(0))
                .map(Object::toString)
                .collect(Collectors.joining(""));

        Assert.assertEquals("NHK", initials);
    }
}
