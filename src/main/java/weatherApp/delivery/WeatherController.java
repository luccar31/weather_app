package weatherApp.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import weatherApp.domain.Weather.WeatherLocation;
import weatherApp.domain.Weather.WeatherService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

//TODO: organizar los controlarores, servicios y entidades sus subpaquetes correspondientes
@Controller
public class WeatherController {

    private WeatherService weatherService;
    private Boolean activated = false;

    @Value(value = "${app.url}")
    private String url;
    private LocalDateTime lastUpdated;

    @Autowired
    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @RequestMapping(path = "/")
    public ModelAndView goHome() throws InterruptedException {

        if(!activated){
            setUpScheduler();
        }

        return new ModelAndView("redirect:/weather");
    }

    @RequestMapping(path = "/weather", method = RequestMethod.GET)
    public ModelAndView showWeather(){
        ModelMap model = new ModelMap();

        List<WeatherLocation> weatherLocationList = weatherService.getAllLocations();


        model.put("lastUpdated", getDateTimeFormated("dd-MM-yyyy HH:mm:ss", lastUpdated));
        model.put("weatherList", weatherLocationList);

        return new ModelAndView("weather", model);
    }

    private String getDateTimeFormated(String pattern, LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }

    private void setUpScheduler() throws InterruptedException {
        //TODO: extraer este timer task a una clase
        TimerTask timerTask = new TimerTask() {
            public void run() {
                //TODO: extraer esta url a un archivo de configuracion para no tener que estar compilando toda la web cada vez que se hace un cambio
                weatherService.processApi("https://ws.smn.gob.ar/map_items/weather");
                lastUpdated = LocalDateTime.now();
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 1000, 1000 * 60 * 5);
        activated = true;

        Thread.sleep(3000);
    }
}
