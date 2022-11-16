package weatherApp.domain.Weather;

import java.util.List;

public interface WeatherService {
    List<WeatherLocation> getAllLocations();

    void scheduledTask(String url);
}
