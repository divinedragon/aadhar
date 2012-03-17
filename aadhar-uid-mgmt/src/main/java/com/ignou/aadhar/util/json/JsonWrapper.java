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

import java.util.HashMap;

/**
 * Wrapper over the HashMap class which stores the data with String keys. We
 * are using it for processing and storing data for JSON objects.
 * @author Deepak Shakya
 *
 */
public class JsonWrapper extends HashMap<String, Object> {

    /**
     * Auto-generated serialisation id.
     */
    private static final long serialVersionUID = 2874806523888402638L;

    public JsonWrapper() {
    }

    public JsonWrapper(Object object, String status, String errorMsg) {
        this.put("object", object);
        this.put("requestStatus", status);
        this.put("errorMsg", errorMsg);
    }

    public JsonWrapper(Object object, String status) {
        this.put("object", object);
        this.put("requestStatus", status);
    }

    public JsonWrapper(String status, String errorMsg) {
        this.put("requestStatus", status);
        this.put("errorMsg", errorMsg);
    }

    public void addParam(String key, Object value) {
        this.put(key, value);
    }
}
