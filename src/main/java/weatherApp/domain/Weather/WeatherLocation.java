package weatherApp.domain.Weather;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
public class WeatherLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SerializedName("_id")
    private String apiId;
    @SerializedName("name")
    private String locName;
    @SerializedName("province")
    private String province;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_idWeather")
    @SerializedName("weather")
    private WeatherDetails weatherDetails;

    public WeatherLocation(){}

    public String getLocName() {
        return locName;
    }

    public void setLocName(String setLocName) {
        this.locName = setLocName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    public void setWeatherDetails(WeatherDetails weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }
}
