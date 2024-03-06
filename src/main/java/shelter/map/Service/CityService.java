package shelter.map.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shelter.map.Domain.City;
import shelter.map.Repository.CityRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CityService {
    private final CityRepository cityRepository;

    @Transactional
    public List<City> findAll(){
        return cityRepository.findAll();
    }

    @Transactional
    public City findByCityName(String cityName){
        return cityRepository.findByCityName(cityName);
    }
}
