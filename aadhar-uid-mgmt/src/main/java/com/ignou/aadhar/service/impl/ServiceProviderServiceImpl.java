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
    public ServiceProvider add(ServiceProvider serviceProvider) throws RuntimeException {
        return serviceProviderDao.add(serviceProvider);
    }

    @Transactional
    public ServiceProvider modify(ServiceProvider serviceProvider) throws RuntimeException {
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
}
