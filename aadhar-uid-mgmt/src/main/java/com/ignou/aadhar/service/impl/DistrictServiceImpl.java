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

import com.ignou.aadhar.dao.DistrictDao;
import com.ignou.aadhar.domain.District;
import com.ignou.aadhar.service.DistrictService;

/**
 * Core implementation of DistrictService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    /**
     * Data Access Object for district table.
     */
    @Autowired
    private DistrictDao districtDao;


    @Transactional
    public District add(District district) throws RuntimeException {
        return districtDao.add(district);
    }

    @Transactional
    public District modify(District district) throws RuntimeException {
        return districtDao.modify(district);
    }

    @Transactional
    public District read(Integer id) throws RuntimeException {
        return districtDao.read(id);
    }

    @Transactional
    public List<District> list() throws RuntimeException {
        return districtDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        districtDao.delete(id);
    }
}
