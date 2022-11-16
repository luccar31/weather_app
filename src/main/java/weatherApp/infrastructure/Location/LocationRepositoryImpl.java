package weatherApp.infrastructure.Location;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import weatherApp.domain.Location.Location;

import java.util.List;

@Repository("locationRepository")
public class LocationRepositoryImpl implements LocationRepository{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void persistOrUpdate(Location location) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(location);
    }

    @Override
    public List<Location> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria cr = session.createCriteria(Location.class);
        return cr.list();
    }
}
