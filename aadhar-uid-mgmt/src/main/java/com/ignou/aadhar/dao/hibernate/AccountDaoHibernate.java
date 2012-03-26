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

import java.util.Date;

import com.ignou.aadhar.dao.AccountDao;
import com.ignou.aadhar.domain.Account;
import com.ignou.aadhar.domain.Citizen;

/**
 * Concrete DAO class for hibernate for performing operations on account table.
 * @author Deepak Shakya
 *
 */
public class AccountDaoHibernate
                              extends GenericDaoHibernate<Account, Integer>
                              implements AccountDao {

    /**
     * Default constructor which passes the class name.
     */
    public AccountDaoHibernate() {
        super(Account.class);
    }

    /**
     * Setting the created date column every time the add method is called.
     */
    @Override
    public Account add(Account model) {
        model.setCreated(new Date());
        return super.add(model);
    }
}
