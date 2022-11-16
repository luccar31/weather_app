package weatherApp.domain.Location;

import weatherApp.domain.Weather.Weather;

import javax.persistence.*;

@Entity
public class Location {
    @Id
    private String id;
    private String name;
    private String province;

    @OneToOne(fetch = FetchType.EAGER)
    private Weather weather;

    public Location(){}

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

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
