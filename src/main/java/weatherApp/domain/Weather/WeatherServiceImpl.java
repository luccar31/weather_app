package weatherApp.domain.Weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import weatherApp.domain.Location.Location;
import weatherApp.infrastructure.Location.LocationRepository;
import weatherApp.infrastructure.Weather.WeatherRepository;

import java.lang.reflect.Type;
import java.util.List;

@Service("weatherService")
@Transactional
public class WeatherServiceImpl implements WeatherService {

    private WeatherRepository weatherRepository;
    private LocationRepository locationRepository;

    public WeatherServiceImpl(WeatherRepository weatherRepository, LocationRepository locationRepository){
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
    }


    //recibir json
    //convertirlo a objetos
    //persistirlos en la base de datos
    @Override
    public void processJson(String json) {
        Gson gson = new Gson();
        Type locationListType = new TypeToken<List<Location>>(){}.getType();

        List<Location> locationList = gson.fromJson(json, locationListType);

        for (var location : locationList){
            locationRepository.persistOrUpdate(location);
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.getAll();
    }


}
