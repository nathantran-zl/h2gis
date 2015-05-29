/**
 * H2GIS is a library that brings spatial support to the H2 Database Engine
 * <http://www.h2database.com>.
 *
 * H2GIS is distributed under GPL 3 license. It is produced by CNRS
 * <http://www.cnrs.fr/>.
 *
 * H2GIS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * H2GIS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * H2GIS. If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information, please consult: <http://www.h2gis.org/>
 * or contact directly: info_at_h2gis.org
 */
package org.h2gis.h2spatialext.function.spatial.properties;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import org.h2gis.h2spatialapi.DeterministicScalarFunction;

/**
 * ST_CompactnessRatio computes the perimeter of a circle whose area is equal to the
 * given polygon's area, and returns the ratio of this computed perimeter to the given
 * polygon's perimeter.
 * <p/>
 * Equivalent definition: ST_CompactnessRatio returns the square root of the
 * polygon's area divided by the area of the circle with circumference equal to
 * the polygon's perimeter.
 * <p/>
 * Note: This uses the 2D perimeter/area of the polygon.
 *
 * @author Adam Gouge
 */
public class ST_CompactnessRatio extends DeterministicScalarFunction {

    public ST_CompactnessRatio() {
        addProperty(PROP_REMARKS, "Returns the compactness ratio of the " +
                "given polygon, defined to be the the perimeter of a circle " +
                "whose area is equal to the given geometry's area divided by " +
                "the given polygon's perimeter.");
    }

    @Override
    public String getJavaStaticMethod() {
        return "computeCompacity";
    }

    /**
     * Returns the compactness ratio of the given polygon
     *
     * @param geom Geometry
     * @return The compactness ratio of the given polygon
     */
    public static Double computeCompacity(Geometry geom) {
        if(geom == null){
            return null;
        }
        if (geom instanceof Polygon) {
            final double circleRadius = Math.sqrt(geom.getArea() / Math.PI);
            final double circleCurcumference = 2 * Math.PI * circleRadius;
            return circleCurcumference / geom.getLength();
        }
        return null;
    }
}
