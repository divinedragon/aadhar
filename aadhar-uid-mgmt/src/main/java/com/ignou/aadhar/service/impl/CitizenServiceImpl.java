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
}
