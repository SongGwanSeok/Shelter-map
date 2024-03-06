package shelter.map.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "coordinatesTBL")
public class Coordinate {

    @Id
    @GeneratedValue
    @Column(name = "coord_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "coord", fetch = LAZY)
    private Shelter shelter;

    private double lat;
    private double lon;
    private double epsg_x;
    private double epsg_y;


    public void changeXY(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

}
