package shelter.map.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shelter.map.Domain.City;
import shelter.map.Service.CityService;
import shelter.map.Service.ShelterService;

import java.util.List;

@Controller
public class HomeController {
    private final String mapKey;
    private final CityService cityService;


    public HomeController(@Value("${map.key}") String mapKey, CityService cityService) {
        this.mapKey = mapKey;
        this.cityService = cityService;
    }


    @GetMapping("/")
    public String home(Model model){

        List<City> cityList = cityService.findAll();

        model.addAttribute("cityList", cityList);

        return "home";
    }


}
