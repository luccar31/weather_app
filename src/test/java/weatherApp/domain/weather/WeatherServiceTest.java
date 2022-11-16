package weatherApp.domain.weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import weatherApp.domain.Weather.WeatherLocation;
import weatherApp.domain.Weather.WeatherDetails;
import weatherApp.domain.Weather.WeatherService;
import weatherApp.domain.Weather.WeatherServiceImpl;
import weatherApp.infrastructure.Weather.WeatherRepository;

import java.lang.reflect.Type;
import java.util.List;

public class WeatherServiceTest {

    private WeatherService weatherService;

    @Mock
    private WeatherRepository weatherRepositoryMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        this.weatherService = new WeatherServiceImpl(weatherRepositoryMock);
    }

    @Test
    public void givenJson_thenConvertToJavaObject_resultCorrectJavaObject(){
        String jsonString = getJson();
        WeatherLocation weatherLocation = convertToJavaObject(jsonString);
        assertCorrectObject(weatherLocation);
    }

    @Test
    public void givenJson_thenConvertToListOfJavaObjects_resultCorrectJavaObjectList(){
        String jsonString = getJsonList();
        List<WeatherLocation> weatherLocationList = convertToListOfJavaObject(jsonString);
        assertCorrectObjectList(weatherLocationList);
    }

    private void assertCorrectObjectList(List<WeatherLocation> weatherLocationList) {
        assertEquals(weatherLocationList.size(), 2);
        assertEquals(weatherLocationList.get(0).getId(), "609de7b1364a909f83b25f46");
        assertNotNull(weatherLocationList.get(0).getWeather());
        assertEquals(weatherLocationList.get(1).getId(), "609de7b1364a909f83b25f47");
        assertNotNull(weatherLocationList.get(1).getWeather());
    }

    private List<WeatherLocation> convertToListOfJavaObject(String jsonString) {
        Gson gson = new Gson();
        Type weatherLocationListType = new TypeToken<List<WeatherLocation>>(){}.getType();
        List<WeatherLocation> weatherLocationList = gson.fromJson(jsonString, weatherLocationListType);
        return weatherLocationList;

    }

    private String getJson() {
        String jsonString = "{\"_id\":\"609de7b1364a909f83b25f46\",\"dist\":6.13,\"lid\":3884,\"fid\":3884,\"int_number\":87544,\"name\":\"Pehuajó\",\"province\":\"Buenos Aires\",\"lat\":\"-35.81163406\",\"lon\":\"-61.89644241\",\"zoom\":\"2\",\"updated\":1658869200,\"weather\":{\"humidity\":94,\"pressure\":1005.8,\"st\":null,\"visibility\":2,\"wind_speed\":22,\"id\":10,\"description\":\"Cubierto con neblina\",\"temp\":16,\"wing_deg\":\"Sur\",\"tempDesc\":\"16ºC\"},\"forecast\":null}";
        return jsonString;
    }

    private String getJsonList() {
        String jsonString = "[{\"_id\":\"609de7b1364a909f83b25f46\",\"dist\":6.13,\"lid\":3884,\"fid\":3884,\"int_number\":87544,\"name\":\"Pehuajó\",\"province\":\"Buenos Aires\",\"lat\":\"-35.81163406\",\"lon\":\"-61.89644241\",\"zoom\":\"2\",\"updated\":1658869200,\"weather\":{\"humidity\":94,\"pressure\":1005.8,\"st\":null,\"visibility\":2,\"wind_speed\":22,\"id\":10,\"description\":\"Cubierto con neblina\",\"temp\":16,\"wing_deg\":\"Sur\",\"tempDesc\":\"16ºC\"},\"forecast\":null}, {\"_id\":\"609de7b1364a909f83b25f47\",\"dist\":6.13,\"lid\":3884,\"fid\":3884,\"int_number\":87544,\"name\":\"Pehuajó\",\"province\":\"Buenos Aires\",\"lat\":\"-35.81163406\",\"lon\":\"-61.89644241\",\"zoom\":\"2\",\"updated\":1658869200,\"weather\":{\"humidity\":94,\"pressure\":1005.8,\"st\":null,\"visibility\":2,\"wind_speed\":22,\"id\":10,\"description\":\"Cubierto con neblina\",\"temp\":16,\"wing_deg\":\"Sur\",\"tempDesc\":\"16ºC\"},\"forecast\":null}]";
        return jsonString;
    }

    private WeatherLocation convertToJavaObject(String jsonString) {
        Gson gson = new Gson();
        WeatherLocation weatherLocation = gson.fromJson(jsonString, WeatherLocation.class);
        return weatherLocation;
    }

    private void assertCorrectObject(WeatherLocation weatherLocation) {
        WeatherDetails weatherDetails =  weatherLocation.getWeather();

        assertEquals(weatherLocation.getId(), "609de7b1364a909f83b25f46");
        assertEquals(weatherDetails.getTemperature(), 16D, 0.001);
    }

}
