package shelter.map.Domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "cityTBL")
public class City {

    @Id
    @GeneratedValue
    @Column(name = "city_id")
    private Long id;

    private String cityName;
    private int cityCode;
    private double centerLat;
    private double centerLon;
}
