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
}
