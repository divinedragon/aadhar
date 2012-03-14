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

import com.ignou.aadhar.dao.AccountDao;
import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.Account;
import com.ignou.aadhar.service.AccountService;

/**
 * Core implementation of AccountService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

    /**
     * Data Access Object for account table.
     */
    @Autowired
    private AccountDao accountDao;

    public GenericDao<Account, Integer> getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Transactional
    public Account add(Account account) throws RuntimeException {
        return accountDao.add(account);
    }

    @Transactional
    public Account modify(Account account) throws RuntimeException {
        return accountDao.modify(account);
    }

    @Transactional
    public Account read(Integer id) throws RuntimeException {
        return accountDao.read(id);
    }

    @Transactional
    public List<Account> list() throws RuntimeException {
        return accountDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        accountDao.delete(id);
    }
}
