package shelter.map.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shelter.map.Domain.City;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByCityName(String cityName);
}
