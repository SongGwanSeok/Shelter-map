package shelter.map.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Table(name = "shelterTBL")
public class Shelter {

    @Id
    @GeneratedValue
    @Column(name = "shelter_id")
    private Long id;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "coord_id")
    private Coordinate coord;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String manageNum;

    private int groupCode;

    private int statusNum;

    private String statusName;

    private Date licenseDate;

    private Date licenseRevocDate;

    private double areaSize;

    private Date updateDate;

    private String updateGbn;
}
