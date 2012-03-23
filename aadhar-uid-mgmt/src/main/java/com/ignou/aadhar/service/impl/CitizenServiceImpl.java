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

import com.ignou.aadhar.dao.CitizenDao;
import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.Citizen;
import com.ignou.aadhar.service.CitizenService;

/**
 * Core implementation of CitizenService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class CitizenServiceImpl implements CitizenService {

    /**
     * Data Access Object for citizen table.
     */
    @Autowired
    private CitizenDao citizenDao;

    public GenericDao<Citizen, Integer> getCitizenDao() {
        return citizenDao;
    }

    public void setCitizenDao(CitizenDao citizenDao) {
        this.citizenDao = citizenDao;
    }

    @Transactional
    public Citizen add(Citizen citizen) throws RuntimeException {
        return citizenDao.add(citizen);
    }

    @Transactional
    public Citizen modify(Citizen citizen) throws RuntimeException {
        return citizenDao.modify(citizen);
    }

    @Transactional
    public Citizen read(Integer id) throws RuntimeException {
        return citizenDao.read(id);
    }

    @Transactional
    public List<Citizen> list() throws RuntimeException {
        return citizenDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        citizenDao.delete(id);
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
    public List<Map<String, Object>> getCitizens(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {

        return citizenDao.getCitizens(searchField, searchValue, pageNumber,
                                        recordsPerPage, sortField, sortOrder);
    }

    /**
     * All valid Gender values which can be selected by citizens.
     * @return Map containing the Gender values. The keys are the actual values
     * which can be used for the Gender field in the database. The values
     * are Title Case version of the keys.
     */
    @Override
    @Transactional
    public Map<String, String> getGenders() {
        return citizenDao.getGenders();
    }

    /**
     * All valid Access Roles which can be selected by citizens.
     * @return Map containing the Access Role values. The keys are the actual
     * values which can be used for the Access Role field in the database.
     * The values are Title Case version of the keys.
     */
    @Override
    @Transactional
    public Map<String, String> getAccessRoles() {
        return citizenDao.getAccessRoles();
    }
}
