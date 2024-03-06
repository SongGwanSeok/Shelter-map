package shelter.map.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import shelter.map.Domain.Shelter;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    @Query("select s from Shelter s join fetch s.address join fetch s.coord where s.address.addressNew like CONCAT('%', :city, '%')")
    List<Shelter> findByAddress_AddressNewContains(@Param("city") String city);

    @Query("select s from Shelter s join fetch s.address  where s.address.addressNew like CONCAT('%', :city, '%')")
    List<Shelter> findAllJoinAddressCity(@Param("city") String city);

    @Query("select s from Shelter s join fetch s.address join fetch s.coord where s.address.addressNew like CONCAT('%', :city, '%') and s.groupCode = :cityCode")
    List<Shelter> findByAddressNewContainsAndCityCode(@Param("city") String city, @Param("cityCode") int CityCode);

}
