package weatherApp.domain.Weather;

import weatherApp.domain.Location.WeatherLocation;

import java.util.List;

public interface WeatherService {
    void persistEntities(List<WeatherLocation> list);

    List<WeatherLocation> getAllLocations();

    String callWeatherApi(String url);
}
