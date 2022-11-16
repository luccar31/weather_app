package weatherApp.infrastructure.Location;

import weatherApp.domain.Location.Location;

import java.util.List;

public interface LocationRepository {
    void persistOrUpdate(Location location);

    List<Location> getAll();
}
