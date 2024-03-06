package shelter.map.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.osgeo.proj4j.ProjCoordinate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shelter.map.Domain.Shelter;
import shelter.map.Repository.ShelterRepository;
import shelter.map.Util.CoordinateConverter;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ShelterService {

    private final ShelterRepository shelterRepository;
    private final CoordinateConverter coordinateConverter;

    /*
    대피소 찾아오기
     */
    @Transactional
    public List<Shelter> findShelter(){
        List<Shelter> shelterList = shelterRepository.findByAddress_AddressNewContains("서울특별시");

        return shelterList;
    }

    @Transactional
    public List<Shelter> findByAddressNewContainsAndCityCode(int cityCode){
        return shelterRepository.findByAddressNewContainsAndCityCode("서울특별시", cityCode);
    }

    @Transactional
    public List<Shelter> findAllJoinAddressCity(String cityName){
        List<Shelter> shelterList = shelterRepository.findAllJoinAddressCity(cityName);
        return shelterList;
    }

    @Transactional
    public List<Shelter> convertEspgToWsg(){
        List<Shelter> shelterList = shelterRepository.findByAddress_AddressNewContains("서울특별시");

        // shelterList를 순회하면서 좌표를 변환합니다.
        for (Shelter shelter : shelterList) {
            // 현재 좌표를 가져옵니다.
            double x = shelter.getCoord().getLat();
            double y = shelter.getCoord().getLon();

            // 좌표를 변환합니다.
            ProjCoordinate convertedCoord = coordinateConverter.transform(x, y);

            // 변환된 좌표를 저장합니다.
            shelter.getCoord().changeXY(convertedCoord.x, convertedCoord.y);
        }
        shelterRepository.saveAll(shelterList);
        return shelterList;
    }
}
