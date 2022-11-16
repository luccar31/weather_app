package weatherApp.domain.Weather;

import weatherApp.domain.Location.Location;

import java.util.List;

public interface WeatherService {
    void processJson(String json);

    List<Location> getAllLocations();
}
