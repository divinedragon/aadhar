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

import com.ignou.aadhar.dao.StateDao;
import com.ignou.aadhar.domain.State;

/**
 * Concrete DAO class for hibernate for performing operations on State table.
 * @author Deepak Shakya
 *
 */
public class StateDaoHibernate extends GenericDaoHibernate<State, Integer>
                                implements StateDao {

    /**
     * Default constructor which passes the class name.
     */
    public StateDaoHibernate() {
        super(State.class);
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
    public List<Map<String, Object>> getStates(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {

        List<State> states = null;
        List<Map<String, Object>> returnStates = new ArrayList<Map<String,Object>>();

        Criteria criteria = getSessionFactory().getCurrentSession()
                                    .createCriteria(State.class, "s");

        /* Add the search parameters to the criteria */
        if (searchField != null && !searchField.isEmpty()
                && searchValue != null && !searchValue.isEmpty()) {

            /* Prefix and suffix the searchValue with % */
            searchValue = "%" + searchValue + "%";
            criteria.add(Restrictions.ilike("s.state", searchValue));
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
            states = criteria.setFirstResult(pageNumber)
                              .setMaxResults(recordsPerPage)
                              .list();
        } else {
            states = criteria.list();
        }

        /* Format this data before sending back for any other further usage */
        for (State stateRecord : states) {

            /* Create new map for current state and add it to master list */
            Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
            returnMap.put("id", stateRecord.getId());
            returnMap.put("state", stateRecord.getState());
            returnMap.put("totalCount", totalCount);

            returnStates.add(returnMap);
        }

        return returnStates;
    }

}
