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
package com.ignou.aadhar.dao;

import java.util.List;
import java.util.Map;

import com.ignou.aadhar.domain.City;

/**
 * Data Access Object providing methods to perform operations on table - City.
 * @author Deepak Shakya
 *
 */
public interface CityDao extends GenericDao<City, Integer> {

    public List<Map<String, Object>> getCities(String city, String state,
            Integer pageNumber, Integer recordsPerPage, String sortField,
            String sortOrder);

}
