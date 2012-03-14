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
package com.ignou.aadhar.dao.hibernate;

import com.ignou.aadhar.dao.AddressDao;
import com.ignou.aadhar.domain.Address;

/**
 * Concrete DAO class for hibernate for performing operations on address table.
 * @author Deepak Shakya
 *
 */
public class AddressDaoHibernate
                              extends GenericDaoHibernate<Address, Integer>
                              implements AddressDao {

    /**
     * Default constructor which passes the class name.
     */
    public AddressDaoHibernate() {
        super(Address.class);
    }
}
