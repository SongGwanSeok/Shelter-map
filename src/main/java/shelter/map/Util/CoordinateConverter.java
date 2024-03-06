package shelter.map.Util;

import org.osgeo.proj4j.BasicCoordinateTransform;
import org.osgeo.proj4j.CRSFactory;
import org.osgeo.proj4j.CoordinateReferenceSystem;
import org.osgeo.proj4j.ProjCoordinate;
import org.springframework.stereotype.Component;

@Component
public class CoordinateConverter {
    public ProjCoordinate transform(double x, double y) {

        CRSFactory factory = new CRSFactory();
        CoordinateReferenceSystem grs80 = factory.createFromName("EPSG:2097");
        CoordinateReferenceSystem wgs84 = factory.createFromName("EPSG:4326");
        BasicCoordinateTransform transformer = new BasicCoordinateTransform(grs80, wgs84);

        ProjCoordinate beforeCoord = new ProjCoordinate(x, y);
        ProjCoordinate afterCoord = new ProjCoordinate();

        return transformer.transform(beforeCoord, afterCoord);
    }

}
