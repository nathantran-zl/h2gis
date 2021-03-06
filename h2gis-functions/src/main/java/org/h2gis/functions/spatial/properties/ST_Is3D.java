/**
 * H2GIS is a library that brings spatial support to the H2 Database Engine
 * <http://www.h2database.com>. H2GIS is developed by CNRS
 * <http://www.cnrs.fr/>.
 *
 * This code is part of the H2GIS project. H2GIS is free software; 
 * you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation;
 * version 3.0 of the License.
 *
 * H2GIS is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details <http://www.gnu.org/licenses/>.
 *
 *
 * For more information, please consult: <http://www.h2gis.org/>
 * or contact directly: info_at_h2gis.org
 */

package org.h2gis.functions.spatial.properties;

import org.h2gis.api.DeterministicScalarFunction;
import org.h2gis.utilities.jts_utils.GeometryMetaData;

import java.io.IOException;

/**
 * Returns 1 if a geometry has a z-coordinate, otherwise 0.
 * 
 * Implements the SQL/MM Part 3: Spatial 5.1.3
 * 
 * @author Erwan Bocher
 */
public class ST_Is3D extends DeterministicScalarFunction{

    
    public ST_Is3D(){
        addProperty(PROP_REMARKS, "Returns 1 if a geometry has a z-coordinate, otherwise 0.");
    }
    
    @Override
    public String getJavaStaticMethod() {
        return "is3D";
    }
    
    /**
     * Returns 1 if a geometry has a z-coordinate, otherwise 0.
     * @param geom
     * @return
     * @throws IOException 
     */
    public static int is3D(byte[] geom) throws IOException {
         if (geom == null) {
            return 0;
        }
        return GeometryMetaData.getMetaDataFromWKB(geom).hasZ?1:0;              
    }

   
}
