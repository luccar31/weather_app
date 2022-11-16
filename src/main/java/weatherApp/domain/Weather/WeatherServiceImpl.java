package weatherApp.domain.Weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import weatherApp.infrastructure.Weather.WeatherRepository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Service("weatherService")
@Transactional
public class WeatherServiceImpl implements WeatherService {

    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository){
        this.weatherRepository = weatherRepository;
    }

    @Override
    public List<WeatherLocation> getAllLocations() {
        return weatherRepository.getAll();
    }
    @Override
    public void scheduledTask(String url){
        String json = callWeatherApi(url);
        List<WeatherLocation> weatherLocationList = getListFromJson(json);
        persistEntities(weatherLocationList);
    }

    public String callWeatherApi(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    public List<WeatherLocation> getListFromJson(String json) {
        Gson gson = new Gson();
        Type weatherLocationListType = new TypeToken<List<WeatherLocation>>(){}.getType();

        List<WeatherLocation> weatherLocationList = gson.fromJson(json, weatherLocationListType);

        return weatherLocationList;
    }

    public void persistEntities(List<WeatherLocation> weatherLocationList) {
        for (var weatherLocation : weatherLocationList){
            WeatherLocation weatherLocationDB = weatherRepository.getByApiId(weatherLocation.getApiId());

            if(weatherLocationDB == null){
                weatherRepository.persist(weatherLocation);
            }
            else{
                if(!temperatureHasntChanged(weatherLocation, weatherLocationDB))
                    updateTemperature(weatherLocation, weatherLocationDB);
            }
        }
    }

    private boolean temperatureHasntChanged(WeatherLocation weatherLocation, WeatherLocation weatherLocationDB) {
        Double storedTemperature = weatherLocationDB.getWeatherDetails().getTemperature();
        Double temperatureFromApi = weatherLocation.getWeatherDetails().getTemperature();

        return Objects.equals(temperatureFromApi, storedTemperature);
    }

    private void updateTemperature(WeatherLocation weatherLocationDB, WeatherLocation weatherLocationApi) {
        WeatherDetails weatherDetailsDB = weatherLocationDB.getWeatherDetails();
        WeatherDetails weatherDetailsApi = weatherLocationApi.getWeatherDetails();

        weatherDetailsDB.setTemperature( weatherDetailsApi.getTemperature() );

        weatherRepository.update(weatherDetailsDB);
    }
}
