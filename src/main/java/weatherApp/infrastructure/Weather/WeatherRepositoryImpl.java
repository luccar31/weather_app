package weatherApp.infrastructure.Weather;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import weatherApp.domain.Weather.WeatherDetails;
import weatherApp.domain.Weather.WeatherLocation;

import java.util.List;

@Repository("weatherRepository")
public class WeatherRepositoryImpl implements WeatherRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public WeatherLocation getById(Long id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(WeatherLocation.class, id);
    }
    //se asume que el "_id" del json es unico
    @Override
    public WeatherLocation getByApiId(String apiId){
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(WeatherLocation.class);
        cr.add(Restrictions.eq("apiId", apiId));
        if (cr.list().size() == 0)
            return null;

        return (WeatherLocation) cr.list().get(0);
    }
    @Override
    public void persist(WeatherLocation weatherLocation){
        Session session = sessionFactory.getCurrentSession();
        session.save(weatherLocation.getWeatherDetails());
        session.save(weatherLocation);
    }

    @Override
    public void update(WeatherDetails weatherDetails){
        Session session = sessionFactory.getCurrentSession();
        session.update(weatherDetails);
    }

    @Override
    public List<WeatherLocation> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(WeatherLocation.class);
        return cr.list();
    }
}
