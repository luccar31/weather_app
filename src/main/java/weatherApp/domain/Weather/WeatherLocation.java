package weatherApp.domain.Weather;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
public class WeatherLocation {
    @Id
    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("province")
    private String province;

    @OneToOne(fetch = FetchType.EAGER)
    @SerializedName("weather")
    private WeatherDetails weatherDetails;

    public WeatherLocation(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public WeatherDetails getWeather() {
        return weatherDetails;
    }

    public void setWeather(WeatherDetails weatherDetails) {
        this.weatherDetails = weatherDetails;
    }
}
