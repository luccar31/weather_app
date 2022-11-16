package weatherApp.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import weatherApp.domain.Location.Location;
import weatherApp.domain.Weather.WeatherService;

import java.util.List;

public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @RequestMapping(path = "/weather", method = RequestMethod.POST)
    public ModelAndView showWeather(@RequestParam("json") String json){
        ModelMap model = new ModelMap();

        weatherService.processJson(json);
        List<Location> locationList = weatherService.getAllLocations();

        model.put("locationList", locationList);

        return new ModelAndView("weather", model);
    }
}
