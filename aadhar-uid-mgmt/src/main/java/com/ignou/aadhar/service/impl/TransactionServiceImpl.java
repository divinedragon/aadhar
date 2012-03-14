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

import com.ignou.aadhar.dao.TransactionDao;
import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.Transaction;
import com.ignou.aadhar.service.TransactionService;

/**
 * Core implementation of TransactionService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    /**
     * Data Access Object for transaction table.
     */
    @Autowired
    private TransactionDao transactionDao;

    public GenericDao<Transaction, Integer> getTransactionDao() {
        return transactionDao;
    }

    public void setTransactionDao(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Transactional
    public Transaction add(Transaction transaction) throws RuntimeException {
        return transactionDao.add(transaction);
    }

    @Transactional
    public Transaction modify(Transaction transaction) throws RuntimeException {
        return transactionDao.modify(transaction);
    }

    @Transactional
    public Transaction read(Integer id) throws RuntimeException {
        return transactionDao.read(id);
    }

    @Transactional
    public List<Transaction> list() throws RuntimeException {
        return transactionDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        transactionDao.delete(id);
    }
}
