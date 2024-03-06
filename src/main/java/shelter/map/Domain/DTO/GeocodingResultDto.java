package shelter.map.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class GeocodingResultDto {

    private String name;
    private String x;
    private String y;
}
