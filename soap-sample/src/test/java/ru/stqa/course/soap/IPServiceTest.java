package ru.stqa.course.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IPServiceTest {

    @Test

    public void testIP() {
        GeoIPService ipLocation =  new GeoIPService().getGeoIPServiceSoap12().getIpLocation("178.57.98.66");
        assertEquals(ipLocation.getGeoIPServiceSoap12().getLocation(), "RU");
    }

}
