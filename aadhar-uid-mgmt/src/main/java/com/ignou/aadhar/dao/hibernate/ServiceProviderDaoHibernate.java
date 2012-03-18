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

import com.ignou.aadhar.dao.ServiceProviderDao;
import com.ignou.aadhar.domain.ServiceProvider;

/**
 * Concrete DAO class for hibernate for performing operations on serviceprovider
 * table.
 * @author Deepak Shakya
 *
 */
public class ServiceProviderDaoHibernate
                          extends GenericDaoHibernate<ServiceProvider, Integer>
                          implements ServiceProviderDao {

    /**
     * Default constructor which passes the class name.
     */
    public ServiceProviderDaoHibernate() {
        super(ServiceProvider.class);
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
    public List<Map<String, Object>> getServiceProviders(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {

        List<ServiceProvider> records = null;
        List<Map<String, Object>> returnRecords = new ArrayList<Map<String,Object>>();

        Criteria criteria = getSessionFactory().getCurrentSession()
                                    .createCriteria(ServiceProvider.class);

        /* Add the search parameters to the criteria */
        if (searchField != null && !searchField.isEmpty()
                && searchValue != null && !searchValue.isEmpty()) {

            /* Prefix and suffix the searchValue with % */
            searchValue = "%" + searchValue + "%";

            /* Set the matching field accordingly */
            if ("name".equals(searchField)) {
                criteria.add(Restrictions.ilike("name", searchValue));

            } else if ("requestUrl".equals(searchField)) {
                criteria.add(Restrictions.ilike("requestUrl", searchValue));

            } else if ("responseUrl".equals(searchField)) {
                criteria.add(Restrictions.ilike("responseUrl", searchValue));

            } else if ("accountNumber".equals(searchField)) {
                criteria.add(Restrictions.ilike("accountNumber", searchValue));

            } else if ("bankIFSCode".equals(searchField)) {
                criteria.add(Restrictions.ilike("bankIFSCode", searchValue));

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
            records = criteria.setFirstResult(pageNumber)
                              .setMaxResults(recordsPerPage)
                              .list();
        } else {
            records = criteria.list();
        }

        /* Format this data before sending back for any other further usage */
        for (ServiceProvider serviceProvider : records) {

            /* Create new map for current service provider and add it to
             * master list
             */
            Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
            returnMap.put("id", serviceProvider.getId());
            returnMap.put("name", serviceProvider.getName());
            returnMap.put("requestUrl", serviceProvider.getRequestUrl());
            returnMap.put("responseUrl", serviceProvider.getResponseUrl());
            returnMap.put("accountNumber", serviceProvider.getAccountNumber());
            returnMap.put("bankIFSCCode", serviceProvider.getBankIFSCCode());
            returnMap.put("totalCount", totalCount);

            returnRecords.add(returnMap);
        }

        return returnRecords;
    }
}
