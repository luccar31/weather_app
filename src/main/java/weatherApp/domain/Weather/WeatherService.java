package weatherApp.domain.Weather;

import java.util.List;

public interface WeatherService {
    List<WeatherLocation> getAllLocations();

    void processApi(String url);
    String callWeatherApi(String url);
    List<WeatherLocation> getListFromJson(String json);

    void persistEntities(List<WeatherLocation> weatherLocationList);
}
