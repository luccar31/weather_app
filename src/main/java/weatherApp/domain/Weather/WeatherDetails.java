package weatherApp.domain.Weather;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

@Entity
public class WeatherDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @SerializedName("temp")
    private Double temperature;

    public WeatherDetails(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
