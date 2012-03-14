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

import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.dao.StateDao;
import com.ignou.aadhar.domain.State;
import com.ignou.aadhar.service.StateService;

/**
 * Core implementation of StateService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class StateServiceImpl implements StateService {

    /**
     * Data Access Object for State table.
     */
    @Autowired
    private StateDao stateDao;

    public GenericDao<State, Integer> getStateDao() {
        return stateDao;
    }

    public void setStateDao(StateDao stateDao) {
        this.stateDao = stateDao;
    }

    @Transactional
    public State add(State state) throws RuntimeException {
        return stateDao.add(state);
    }

    @Transactional
    public State modify(State state) throws RuntimeException {
        return stateDao.modify(state);
    }

    @Transactional
    public State read(Integer id) throws RuntimeException {
        return stateDao.read(id);
    }

    @Transactional
    public List<State> list() throws RuntimeException {
        return stateDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        stateDao.delete(id);
    }
}
