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
package com.ignou.aadhar.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignou.aadhar.dao.CityDao;
import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.City;
import com.ignou.aadhar.service.CityService;

/**
 * Core implementation of CityService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class CityServiceImpl implements CityService {

    /**
     * Data Access Object for city table.
     */
    @Autowired
    private CityDao cityDao;

    public GenericDao<City, Integer> getCityDao() {
        return cityDao;
    }

    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Transactional
    public City add(City city) throws RuntimeException {
        return cityDao.add(city);
    }

    @Transactional
    public City modify(City city) throws RuntimeException {
        return cityDao.modify(city);
    }

    @Transactional
    public City read(Integer id) throws RuntimeException {
        return cityDao.read(id);
    }

    @Transactional
    public List<City> list() throws RuntimeException {
        return cityDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        cityDao.delete(id);
    }

    /**
     * Gets the records from the Database based on the parameters provided.
     * @param searchField The field name on which the search is to be made.
     * @param searchValue Value which needs to be searched.
     * @param pageNumber Initial offset of the records.
     * @param recordsPerPage Total number of records which are selected for
     * resultset.
     * @param sortField Name of the field on which the data needs to be sorted.
     * @param sortOrder Order in which the sortField is sorted.
     * @return Returns the records as list of map where each map stores the
     * record data as key-value pairs.
     */
    @Override
    @Transactional
    public List<Map<String, Object>> getCities(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {
        return cityDao.getCities(searchField, searchValue, pageNumber,
                                     recordsPerPage, sortField, sortOrder);
    }

    /**
     * Gets the Cities which are linked to the stateId provided.
     * @param stateId State id for which all the cities will be returned.
     * @return All city objects for the stateId as a list.
     */
    @Override
    @Transactional
    public List<City> getCitiesForStateId(Integer stateId) {
        return cityDao.getCitiesForStateId(stateId);
    }
}
