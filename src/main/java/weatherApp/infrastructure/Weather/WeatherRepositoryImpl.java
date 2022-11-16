package weatherApp.infrastructure.Weather;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import weatherApp.domain.Weather.WeatherLocation;

import java.util.List;

@Repository("weatherRepository")
public class WeatherRepositoryImpl implements WeatherRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistOrUpdate(WeatherLocation weatherLocation) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(weatherLocation);
    }

    @Override
    public List<WeatherLocation> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(WeatherLocation.class);
        return cr.list();
    }
}
