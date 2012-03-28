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

import com.ignou.aadhar.dao.TransactionTokenDao;
import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.Citizen;
import com.ignou.aadhar.domain.Transaction;
import com.ignou.aadhar.domain.TransactionToken;
import com.ignou.aadhar.service.TransactionTokenService;

/**
 * Core implementation of TransactionTokenService layer.
 * @author Deepak Shakya
 *
 */
@Service
public class TransactionTokenServiceImpl implements TransactionTokenService {

    /**
     * Data Access Object for TransactionToken table.
     */
    @Autowired
    private TransactionTokenDao transactionTokenDao;

    public GenericDao<TransactionToken, Integer> getTransactionTokenDao() {
        return transactionTokenDao;
    }

    public void setTransactionTokenDao(TransactionTokenDao transactionTokenDao) {
        this.transactionTokenDao = transactionTokenDao;
    }

    @Transactional
    public TransactionToken add(TransactionToken transactionToken)
                                                    throws RuntimeException {
        return transactionTokenDao.add(transactionToken);
    }

    @Transactional
    public TransactionToken modify(TransactionToken transactionToken)
                                                    throws RuntimeException {
        return transactionTokenDao.modify(transactionToken);
    }

    @Transactional
    public TransactionToken read(Integer id) throws RuntimeException {
        return transactionTokenDao.read(id);
    }

    @Transactional
    public List<TransactionToken> list() throws RuntimeException {
        return transactionTokenDao.list();
    }

    @Transactional
    public void delete(Integer id) throws RuntimeException {
        transactionTokenDao.delete(id);
    }

    /**
     * Fetches the TransactionToken object for the transaction id and uid value.
     * @param txn Transaction object.
     * @param citizen Citizen object.
     * @return TransactionToken object corresponding for the Transaction and
     * citizen object provided.
     */
    @Override
    @Transactional
    public TransactionToken getTokenForTransactionIdAndUID(Transaction txn,
            Citizen citizen) {
        return transactionTokenDao.getTokenForTransactionIdAndUID(txn, citizen);
    }
}
