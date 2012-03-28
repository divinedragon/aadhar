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

import com.ignou.aadhar.dao.ServiceProviderDao;
import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.ServiceProvider;
import com.ignou.aadhar.service.ServiceProviderService;

/**
 * Core implementation of ServiceProviderService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

    /**
     * Data Access Object for service provider table.
     */
    @Autowired
    private ServiceProviderDao serviceProviderDao;

    public GenericDao<ServiceProvider, Integer> getServiceProviderDao() {
        return serviceProviderDao;
    }

    public void setServiceProviderDao(ServiceProviderDao serviceProviderDao) {
        this.serviceProviderDao = serviceProviderDao;
    }

    @Transactional
    public ServiceProvider add(ServiceProvider serviceProvider)
                                                throws RuntimeException {
        return serviceProviderDao.add(serviceProvider);
    }

    @Transactional
    public ServiceProvider modify(ServiceProvider serviceProvider)
                                                throws RuntimeException {
        return serviceProviderDao.modify(serviceProvider);
    }

    @Transactional
    public ServiceProvider read(Integer id) throws RuntimeException {
        return serviceProviderDao.read(id);
    }

    @Transactional
    public List<ServiceProvider> list() throws RuntimeException {
        return serviceProviderDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        serviceProviderDao.delete(id);
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
    public List<Map<String, Object>> getServiceProviders(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {

        return serviceProviderDao.getServiceProviders(searchField, searchValue,
                              pageNumber, recordsPerPage, sortField, sortOrder);
    }

    /**
     * Fetches the Service Provider object corresponding to the parameters Name
     * and Password.
     * @param name Name with which Service Provider is registered.
     * @param password Password allocated to Service Provider for authentication
     * @return Service Provider record object if it exists.
     */
    @Override
    @Transactional
    public ServiceProvider getByNameAndPassword(String name, String password) {
        return serviceProviderDao.getByNameAndPassword(name, password);
    }
}
