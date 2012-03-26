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

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ignou.aadhar.dao.AddressDao;
import com.ignou.aadhar.domain.Account;
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

    @Override
    @Transactional
    public Address fetchOrCreate(Address localAddress) {
        
        /* We will check if there is any object with the address values similar
         * to the one provided in the argument object. If yes, we will return
         * the same object, otherwise we will create the object and return the
         * same.
         */
        Criteria criteria = getSessionFactory().getCurrentSession()
                                .createCriteria(Address.class)
                                .add(Restrictions.ilike("addressLine1", localAddress.getAddressLine1()))
                                .add(Restrictions.ilike("addressLine2", localAddress.getAddressLine2()))
                                .add(Restrictions.ilike("addressLine3", localAddress.getAddressLine3()))
                                .add(Restrictions.eq("city", localAddress.getCity()));

        /* Execute the criteria and see if any records are fetched */
        Address dbAddress = (Address) criteria.uniqueResult();

        if (dbAddress == null) {
            /* No record found. Lets create a db record and return the same. */
            dbAddress = add(localAddress);
        }

        return dbAddress;
    }
}
