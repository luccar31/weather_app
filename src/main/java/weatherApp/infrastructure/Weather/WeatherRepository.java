package weatherApp.infrastructure.Weather;

import weatherApp.domain.Weather.WeatherLocation;

import java.util.List;

public interface WeatherRepository {
    void persistOrUpdate(WeatherLocation weatherLocation);

    List<WeatherLocation> getAll();
}
