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
package org.h2gis.h2spatialext.function.spatial.earth;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import java.util.Date;
import org.h2gis.h2spatialapi.DeterministicScalarFunction;

/**
 * Compute the sun position and return a new coordinate with x = azimuth and y = altitude
 * 
 * @author Erwan Bocher
 */
public class ST_SunPosition extends DeterministicScalarFunction{

    
    public ST_SunPosition(){
        addProperty(PROP_REMARKS, "Return the sun position (horizontal coordinate system) as a Point where : \n"
                + "x = sun azimuth in radians (direction along the horizon, measured from north to\n"
                + "east).\n"        
                + "y = sun altitude above the horizon in radians, e.g. 0 at the\n"
                + "horizon and PI/2 at the zenith.\n");
    }
    
    @Override
    public String getJavaStaticMethod() {
        return "sunPosition";
    }
    
    /**
     * Return the current sun position 
     * @param point
     * @return 
     */
    public static Geometry sunPosition(Geometry point){
        return sunPosition(point, new Date());
    }
    
    /**
     * Return the sun position for a given date
     * 
     * @param point
     * @param date
     * @return
     * @throws IllegalArgumentException 
     */
    public static Geometry sunPosition(Geometry point, Date date) throws IllegalArgumentException{
        if(point == null){
            return null;
        }
        if (point instanceof Point) {
            Coordinate coord = point.getCoordinate();
            return point.getFactory().createPoint( SunCalc.getPosition(date, coord.y, coord.x));
        } else {
            throw new IllegalArgumentException("The sun position is computed according a point location.");
        }
    }
}
