package weatherApp.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import weatherApp.domain.Location.WeatherLocation;
import weatherApp.domain.Weather.WeatherService;

import java.util.List;

@Controller
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @RequestMapping(path = "/weather", method = RequestMethod.POST)
    public ModelAndView showWeather(){
        ModelMap model = new ModelMap();

        //TODO: implementar tests de esto a ver si es lo que se necesita
        List<WeatherLocation> weatherLocationList = weatherService.getAllLocations();

        model.put("locationList", weatherLocationList);

        return new ModelAndView("weather", model);
    }
}
