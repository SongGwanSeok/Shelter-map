package shelter.map.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shelter.map.Domain.City;
import shelter.map.Domain.Shelter;
import shelter.map.Service.CityService;
import shelter.map.Service.CoordinateService;
import shelter.map.Service.GeocodingService;
import shelter.map.Service.ShelterService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GeocodingController {
    private final ShelterService shelterService;
    private final CoordinateService coordinateService;
    private final CityService cityService;
    private final GeocodingService geocodingService;

    @PostMapping("/geocodingCity")
    public String GeocodingCity(@Param("cityName") String cityName) throws IOException {

        List<Shelter> shelterList = shelterService.findAllJoinAddressCity(cityName);
        double[] LatLon = geocodingService.getGeocoding(shelterList.get(0).getAddress().getAddressNew());
        log.info("Lat" + LatLon[0] + "Lon" + LatLon[1]);

//        for (Shelter shelter : shelterList) {
//
//        }

        return "/home";
    }

    @GetMapping("/geocodingCity")
    public String GeocodingCity(Model model){
        List<City> cityAll = cityService.findAll();

        model.addAttribute("cityList", cityAll);

        return "geocodingCity";
    }
}
