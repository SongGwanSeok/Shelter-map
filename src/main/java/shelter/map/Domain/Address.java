package shelter.map.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Table(name = "addressTBL")
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "address", fetch = LAZY)
    private Shelter shelter;

    private String addressOld;
    private String addressNew;
    private String zipcode;
    private String name;
}
