package ru.stqa.pft.soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


/**
 * Created by a.oreshnikova on 24.12.17.
 */

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("91.204.150.197");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }

    @Test
    public void tesInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("91.204.150.xxx");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
