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
            weatherRepository.persistOrUpdate(weatherLocation);
        }
    }
}
