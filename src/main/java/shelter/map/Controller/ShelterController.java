package shelter.map.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shelter.map.Domain.City;
import shelter.map.Domain.Shelter;
import shelter.map.Service.AddressSevice;
import shelter.map.Service.CityService;
import shelter.map.Service.ShelterService;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ShelterController {

    private final String mapKey;
    private final ShelterService shelterService;
    private final CityService cityService;
    private final AddressSevice addressSevice;

    //생성자
    public ShelterController(@Value("${map.key}") String mapKey, ShelterService shelterService, CityService cityService, AddressSevice addressSevice) {
        this.mapKey = mapKey;
        this.shelterService = shelterService;
        this.cityService = cityService;
        this.addressSevice = addressSevice;
    }

    //주소 검색
    @PostMapping("/search")
    public String search(Model model, @Param("address") String address){

        List<City> cityList = cityService.findAll();

        model.addAttribute("mapKey", mapKey);
        model.addAttribute("address", address);
        model.addAttribute("cityList", cityList);

        String findCityName = addressSevice.getGuFromAddress(address);

        List<Shelter> shelterList = new ArrayList<>();
        if(!(findCityName==null)) {
            City city = cityService.findByCityName(findCityName);
            ArrayList<Double> centerLatLon = new ArrayList<>();
            centerLatLon.add(city.getCenterLat());
            centerLatLon.add(city.getCenterLon());
            shelterList = shelterService.findShelter();
        }


        model.addAttribute("shelterList", shelterList);

        return "search";
    }

    //서울 전체 대피소 정보
    @GetMapping("/all")
    public String all(Model model){

        List<Shelter> shelterList = shelterService.findShelter();

        List<City> cityList = cityService.findAll();

        model.addAttribute("mapKey", mapKey);
        model.addAttribute("shelterList", shelterList);
        model.addAttribute("cityList", cityList);

        return "all";
    }

    //주소로 검색하기 구현 덜 됨
    @GetMapping("/byAddress")
    public String byAddress(Model model, @Param("address") String address){

        List<Shelter> shelterList = shelterService.findAllJoinAddressCity("서울특별시");

        List<City> cityList = cityService.findAll();

        model.addAttribute("mapKey", mapKey);
        model.addAttribute("shelterList", shelterList);
        model.addAttribute("cityList", cityList);

        return "showShelterByAddress";
    }

    //coordinatesTBL의 ESPG:2097 좌표를 위도, 경도 좌표계로 변환
    //사용 안하고 python으로 구현
    @GetMapping("/convert")
    public void convert(){
        shelterService.convertEspgToWsg();
    }

    //시군구 주소별로 서울특별시 대피소 찾기
    @GetMapping("/searchCity")
    public String searchCity(@Param("cityName") String cityName, Model model){

        City city = cityService.findByCityName(cityName);
        ArrayList<Double> centerLatLon = new ArrayList<>();
        centerLatLon.add(city.getCenterLat());
        centerLatLon.add(city.getCenterLon());

        List<Shelter> shelterList = shelterService.findByAddressNewContainsAndCityCode(city.getCityCode());
        List<City> cityList = cityService.findAll();


        model.addAttribute("mapKey", mapKey);
        model.addAttribute("shelterList", shelterList);
        model.addAttribute("cityList", cityList);
        model.addAttribute("centerLatLon", centerLatLon);

        return "showShelterByCity";
    }

    @GetMapping("/setLatLon")
    public void setLatLon(){
        List<Shelter> shelterList = shelterService.findAllJoinAddressCity("서울특별시");

        for (Shelter shelter : shelterList) {
            shelter.getAddress().getAddressNew();
        }


    }

}
