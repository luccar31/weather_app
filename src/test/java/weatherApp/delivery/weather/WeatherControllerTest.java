package weatherApp.delivery.weather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import weatherApp.delivery.WeatherController;
import weatherApp.domain.Weather.WeatherLocation;
import weatherApp.domain.Weather.WeatherService;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class WeatherControllerTest {
    private WeatherController weatherController;

    @Mock
    private WeatherService weatherServiceMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        weatherController = new WeatherController(weatherServiceMock);
    }

    @Test
    public void givenWeatherListAndModel_thenCallWeatherService_resultInGivenList(){
        List<WeatherLocation> weatherLocationList = givenWeatherLocationList();
        when(weatherServiceMock.getAllLocations()).thenReturn(weatherLocationList);

        ModelAndView mav = weatherController.showWeather();

        List<WeatherLocation> listFromDB = (List<WeatherLocation>) mav.getModel().get("weatherList");

        assertEquals(2, listFromDB.size());
    }

    private List<WeatherLocation> givenWeatherLocationList() {
        List<WeatherLocation> weatherLocationList = new ArrayList<>();
        weatherLocationList.add(new WeatherLocation());
        weatherLocationList.add(new WeatherLocation());
        return weatherLocationList;
    }
}
