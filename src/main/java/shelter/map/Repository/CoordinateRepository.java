package shelter.map.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shelter.map.Domain.Coordinate;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
}
