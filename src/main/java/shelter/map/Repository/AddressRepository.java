package shelter.map.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shelter.map.Domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
