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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Validator class which validates the json parameters in the String.
 * @author Deepak Shakya
 *
 */
public class JsonRequestValidator {

    /**
     * Validates the json parameters in the json string.
     * @param jsonString Json String which is validated.
     * @param validParams List of parameters against which the json string is
     * validated.
     * @return Returns the Map object for the json string.
     */
    public static Map<String, String> validateRequestParams(String jsonString,
            String[] validParams) {

        Map<String, String> paramMap = (HashMap<String, String>) JsonMarshaller
                                        .parse(jsonString, HashMap.class);

        if (paramMap == null || paramMap.isEmpty()) {
            throw new IllegalArgumentException("Invalid Parameters : Empty/Invalid parameter list");
        }

        List<String> paramList = Arrays.asList(validParams);
        Set<String> paramKeys = paramMap.keySet();

        for (String key : paramKeys) {
            if (!(paramList.contains(key))) {
                throw new IllegalArgumentException("Invalid Parameter : \"" + key + "\" is not a valid parameter");
            }
        }

        return paramMap;
    }
}
