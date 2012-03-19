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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.ignou.aadhar.dao.BankDao;
import com.ignou.aadhar.domain.Bank;

/**
 * Concrete DAO class for hibernate for performing operations on bank table.
 * @author Deepak Shakya
 *
 */
public class BankDaoHibernate extends GenericDaoHibernate<Bank, Integer>
                              implements BankDao {

    /**
     * Default constructor which passes the class name.
     */
    public BankDaoHibernate() {
        super(Bank.class);
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
    public List<Map<String, Object>> getBanks(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {

        List<Bank> banks = null;
        List<Map<String, Object>> returnBanks = new ArrayList<Map<String,Object>>();

        Criteria criteria = getSessionFactory().getCurrentSession()
                                    .createCriteria(Bank.class);

        /* Add the search parameters to the criteria */
        if (searchField != null && !searchField.isEmpty()
                && searchValue != null && !searchValue.isEmpty()) {

            /* Prefix and suffix the searchValue with % */
            searchValue = "%" + searchValue + "%";

            /* Now there are only two fields which we can search here. */
            if ("name".equals(searchField)) {
                criteria.add(Restrictions.ilike("name", searchValue));
            } else if ("url".equals(searchField)) {
                criteria.add(Restrictions.ilike("url", searchValue));
            }
        }

        /* Let's first get the total number of records that satisfy the provided
         * parameters.
         */
        String totalCount = (String) criteria
                                          .setProjection(Projections.rowCount())
                                          .uniqueResult().toString();

        /* Reset the Criteria specification to remove the projection details */
        criteria.setProjection(null);
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);

        /* Set the default sort field if not provided */
        if (sortField != null && !sortField.isEmpty()) {

            /* Check what order was provided for this field */
            if ("asc".equals(sortOrder)) {
                /* Sort in ascending order */
                criteria.addOrder(Order.asc(sortField));

            } else if ("desc".equals(sortOrder)) {
                /* Sort in descending order */
                criteria.addOrder(Order.desc(sortField));

            } else {
                /* Default sort behaviour other wise */
                criteria.addOrder(Order.asc(sortField));
            }
        }

        /* Set the record filtering on pageCount and recordsPerPage if they are
         * available.
         */
        if (pageNumber != null && recordsPerPage != null) {
            banks = criteria.setFirstResult(pageNumber)
                              .setMaxResults(recordsPerPage)
                              .list();
        } else {
            banks = criteria.list();
        }

        /* Format this data before sending back for any other further usage */
        for (Bank bankRecord : banks) {

            /* Create new map for current bank and add it to master list */
            Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
            returnMap.put("id", bankRecord.getId());
            returnMap.put("name", bankRecord.getName());
            returnMap.put("url", bankRecord.getUrl());
            returnMap.put("totalCount", totalCount);

            returnBanks.add(returnMap);
        }

        return returnBanks;
    }
}
