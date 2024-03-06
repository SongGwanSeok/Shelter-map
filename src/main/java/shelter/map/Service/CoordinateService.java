package shelter.map.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shelter.map.Domain.Coordinate;
import shelter.map.Domain.Shelter;
import shelter.map.Repository.CoordinateRepository;
import shelter.map.Repository.ShelterRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class CoordinateService {

    private final CoordinateRepository coordinateRepository;
    private final ShelterRepository shelterRepository;

    @Transactional
    public Coordinate save(double lat, double lon){

//        Coordinate coord = Coordinate.builder()
//                .lat(lat)
//                .lon(lon)
//                .build();
//
//        return coordinateRepository.save(coord);
        return null;
    }
}
