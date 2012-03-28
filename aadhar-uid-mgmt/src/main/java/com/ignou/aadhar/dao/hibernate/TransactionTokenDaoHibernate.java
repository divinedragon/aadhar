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

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ignou.aadhar.dao.TransactionTokenDao;
import com.ignou.aadhar.domain.Citizen;
import com.ignou.aadhar.domain.Transaction;
import com.ignou.aadhar.domain.TransactionToken;

/**
 * Concrete DAO class for hibernate for performing operations on transaction
 * token table.
 * @author Deepak Shakya
 *
 */
public class TransactionTokenDaoHibernate
                          extends GenericDaoHibernate<TransactionToken, Integer>
                                             implements TransactionTokenDao {

    /**
     * Default constructor which passes the class name.
     */
    public TransactionTokenDaoHibernate() {
        super(TransactionToken.class);
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
        return  (TransactionToken) getSessionFactory().getCurrentSession()
                                    .createCriteria(TransactionToken.class)
                                    .add(Restrictions.eq("transaction", txn))
                                    .add(Restrictions.eq("citizen", citizen))
                                    .uniqueResult();
    }
}
