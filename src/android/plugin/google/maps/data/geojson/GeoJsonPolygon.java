package plugin.google.maps.data.geojson;

import com.google.android.gms.maps.model.LatLng;
import plugin.google.maps.data.DataPolygon;

import java.util.ArrayList;
import java.util.List;

/**
 * A GeoJsonPolygon geometry contains an array of arrays of {@link com.google.android.gms.maps.model.LatLng}s.
 * The first array is the polygon exterior boundary. Subsequent arrays are holes.
 */

public class GeoJsonPolygon implements DataPolygon {

    private final static String GEOMETRY_TYPE = "Polygon";

    private final List<? extends List<LatLng>> mCoordinates;

    private final static int POLYGON_OUTER_COORDINATE_INDEX = 0;

    private final static int POLYGON_INNER_COORDINATE_INDEX = 1;

    /**
     * Creates a new GeoJsonPolygon object
     *
     * @param coordinates list of list of coordinates of GeoJsonPolygon to store
     */
    public GeoJsonPolygon(
            List<? extends List<LatLng>> coordinates) {
        if (coordinates == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        mCoordinates = coordinates;
    }

    /**
     * Gets the type of geometry. The type of geometry conforms to the GeoJSON 'type'
     * specification.
     *
     * @return type of geometry
     */
    public String getType() {
        return GEOMETRY_TYPE;
    }

    /**
     * Gets a list of a list of coordinates of the GeoJsonPolygons
     *
     * @return list of a list of coordinates of the GeoJsonPolygon
     */
    public List<? extends List<LatLng>> getCoordinates() {
        return mCoordinates;
    }

    /**
     * Gets the stored geometry object
     *
     * @return geometry object
     */
    public List<? extends List<LatLng>> getGeometryObject() { return getCoordinates(); }

    /**
     * Gets the type of geometry
     *
     * @return type of geometry
     */
    public String getGeometryType() { return getType(); }


    /**
     * Gets an array of outer boundary coordinates
     *
     * @return array of outer boundary coordinates
     */
    public ArrayList<LatLng> getOuterBoundaryCoordinates() {
        // First array of coordinates are the outline
        return (ArrayList<LatLng>) getCoordinates().get(POLYGON_OUTER_COORDINATE_INDEX);
    }

    /**
     * Gets an array of arrays of inner boundary coordinates
     *
     * @return array of arrays of inner boundary coordinates
     */
    public ArrayList<ArrayList<LatLng>> getInnerBoundaryCoordinates() {
        // Following arrays are holes
        ArrayList<ArrayList<LatLng>> innerBoundary = new ArrayList<>();
        for (int i = POLYGON_INNER_COORDINATE_INDEX; i < getCoordinates().size();
             i++) {
            innerBoundary.add((ArrayList<LatLng>) getCoordinates().get(i));
        }
        return innerBoundary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(GEOMETRY_TYPE).append("{");
        sb.append("\n coordinates=").append(mCoordinates);
        sb.append("\n}\n");
        return sb.toString();
    }
}
