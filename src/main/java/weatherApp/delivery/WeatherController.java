package weatherApp.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import weatherApp.domain.Weather.WeatherLocation;
import weatherApp.domain.Weather.WeatherService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class WeatherController {

    private WeatherService weatherService;
    private Boolean activated = false;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @RequestMapping(path = "/")
    public ModelAndView goHome(){

        //TODO: extract to outside method
        if(!activated){
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    weatherService.scheduledTask("https://ws.smn.gob.ar/map_items/weather");
                }
            };

            Timer timer = new Timer();
            timer.scheduleAtFixedRate(timerTask, 0, 1000 * 60 * 5);
            activated = true;
        }

        return new ModelAndView("home");
    }

    @RequestMapping(path = "/weather", method = RequestMethod.POST)
    public ModelAndView showWeather(){
        ModelMap model = new ModelMap();

        List<WeatherLocation> weatherLocationList = weatherService.getAllLocations();

        model.put("weatherList", weatherLocationList);

        return new ModelAndView("weather", model);
    }
}
