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

import com.ignou.aadhar.dao.AddressDao;
import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.Address;
import com.ignou.aadhar.service.AddressService;

/**
 * Core implementation of AddressService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class AddressServiceImpl implements AddressService {

    /**
     * Data Access Object for address table.
     */
    @Autowired
    private AddressDao addressDao;

    public GenericDao<Address, Integer> getAddressDao() {
        return addressDao;
    }

    public void setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
    }

    @Transactional
    public Address add(Address address) throws RuntimeException {
        return addressDao.add(address);
    }

    @Transactional
    public Address modify(Address address) throws RuntimeException {
        return addressDao.modify(address);
    }

    @Transactional
    public Address read(Integer id) throws RuntimeException {
        return addressDao.read(id);
    }

    @Transactional
    public List<Address> list() throws RuntimeException {
        return addressDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        addressDao.delete(id);
    }

    @Override
    @Transactional
    public Address fetchOrCreate(Address localAddress) {
        return addressDao.fetchOrCreate(localAddress);
    }
}
