/*
 * Aadhar UID Management.
 *
 * Copyright (C) 2012 Deepak Shakya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.ignou.aadhar.util.json;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * JsonMarshaller to convert the json String to appropriate java object class
 * specified.
 * @author Deepak Shakya
 *
 */
public class JsonMarshaller {

    /**
     * Parses the json string in the specified class provided.
     * @param jsonString Json String which needs to be converted.
     * @param classObj Class for which the object is created with the json data.
     * @return Object created with the parsed Json data.
     */
    public static Object parse(String jsonString, Class classObj) {

        ObjectMapper mapper = new ObjectMapper();
        Object parsedObject = null;

        try {
            parsedObject = mapper.readValue(jsonString, classObj);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parsedObject;
    }
}
