package weatherApp.infrastructure.Weather;

import weatherApp.domain.Weather.WeatherDetails;
import weatherApp.domain.Weather.WeatherLocation;

import java.util.List;

public interface WeatherRepository {
    WeatherLocation getById(Long id);

    WeatherLocation getByApiId(String apiId);

    void persist(WeatherLocation weatherLocation);

    void update(WeatherDetails weatherDetails);

    List<WeatherLocation> getAll();
}
