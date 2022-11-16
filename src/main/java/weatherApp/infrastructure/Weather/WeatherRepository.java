package weatherApp.infrastructure.Weather;

import weatherApp.domain.Location.WeatherLocation;

import java.util.List;

public interface WeatherRepository {
    void persistOrUpdate(WeatherLocation weatherLocation);

    List<WeatherLocation> getAll();
}
