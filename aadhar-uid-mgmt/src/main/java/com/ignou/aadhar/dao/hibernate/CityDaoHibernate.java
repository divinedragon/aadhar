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
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ignou.aadhar.dao.CityDao;
import com.ignou.aadhar.domain.City;

/**
 * Concrete DAO class for hibernate for performing operations on city table.
 * @author Deepak Shakya
 *
 */
public class CityDaoHibernate extends GenericDaoHibernate<City, Integer>
                                implements CityDao {

    /**
     * Default constructor which passes the class name.
     */
    public CityDaoHibernate() {
        super(City.class);
    }


    /************************************************************************
     * THIS METHOD IS ONLY FOR REFERENCE. I WAS GOING FORWARD WITH THIS ONE
     * BUT WAS ABLE TO IMPLEMENT THE SAME WITH HIBERNATE CRITERIA. MIGHT
     * NEED THIS CODE SOME TIME LATER. HENCE NOT DELETED.
     * @param city
     * @param state
     * @param pageNumber
     * @param recordsPerPage
     * @param sortField
     * @param sortOrder
     * @return
     */
    public List<Map<String, Object>> getCitiesHQL(String city, String state,
            Integer pageNumber, Integer recordsPerPage, String sortField,
            String sortOrder) {

        List<Object[]> cities = null;

        /* Set the default sort field if not provided */
        if (sortField == null || "".equals(sortField)) {
            sortField = "city";
        }

        /* Set the default sort order if not provided */
        if (sortOrder == null || "".equals(sortOrder)) {
            sortField = "asc";
        }

        String searchString = "";

        /* Add the city field search condition if provided */
        if (city != null && !city.isEmpty()) {
            searchString += " AND city like '%" + city + "%' ";
        }

        /* Add the State field search condition if provided */
        if (state != null && !state.isEmpty()) {
            searchString += " AND state.state like '%" + state + "%' ";
        }

        /* Create the Query now */
        Query cityQuery = getSession().createQuery(
                            "SELECT c.id, c.city, c.state.state FROM city c "
                          + "WHERE (1=1) " + searchString
                          + "ORDER BY " + sortField + " " + sortOrder);

        /* Lets also count the total number of records matching the criteria */
        int totalRecordCount = 0;
        /* Set the record filtering on pageCount and recordsPerPage if they are
         * available.
         */
        if (pageNumber != null && recordsPerPage != null) {
            cities = cityQuery.setFirstResult(pageNumber)
                              .setMaxResults(recordsPerPage)
                              .list();
        } else {
            cities = cityQuery.list();
        }


        return null;
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
    public List<Map<String, Object>> getCities(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {

        List<City> cities = null;
        List<Map<String, Object>> returnCities = new ArrayList<Map<String,Object>>();

        Criteria criteria = getSessionFactory().getCurrentSession()
                                    .createCriteria(City.class, "c")
                                        .createAlias("state", "s");

        /* Add the search parameters to the criteria */
        if (searchField != null && !searchField.isEmpty()
                && searchValue != null && !searchValue.isEmpty()) {

            /* Prefix and suffix the searchValue with % */
            searchValue = "%" + searchValue + "%";

            /* Now there are only two fields which we can search here. */
            if ("city".equals(searchField)) {
                criteria.add(Restrictions.ilike("c.city", searchValue));
            } else if ("state".equals(searchField)) {
                criteria.add(Restrictions.ilike("s.state", searchValue));
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

        /* We don't want to load the entire objects. Just the names would
         * suffice. Setting projections for the same.
         */
        //criteria.setProjection(Projections.property("id").as("id"));
        //criteria.setProjection(Projections.property("city").as("city"));
        //criteria.setProjection(Projections.property("state").as("state"));

        /* Set the record filtering on pageCount and recordsPerPage if they are
         * available.
         */
        if (pageNumber != null && recordsPerPage != null) {
            cities = criteria.setFirstResult(pageNumber)
                              .setMaxResults(recordsPerPage)
                              .list();
        } else {
            cities = criteria.list();
        }

        /* Format this data before sending back for any other further usage */
        for (City cityRecord : cities) {

            /* Create a new map for current city and add it to master list */
            Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
            returnMap.put("id", cityRecord.getId());
            returnMap.put("city", cityRecord.getCity());
            returnMap.put("state", cityRecord.getState().getState());
            returnMap.put("totalCount", totalCount);

            returnCities.add(returnMap);
        }

        return returnCities;
    }

    /**
     * Gets the Cities which are linked to the stateId provided.
     * @param stateId State id for which all the cities will be returned.
     * @return All city objects for the stateId as a list.
     */
    @Override
    @Transactional
    public List<City> getCitiesForStateId(Integer stateId) {
        
        return getSessionFactory().getCurrentSession()
                .createCriteria(City.class)
                    .add(Restrictions.eq("state.id", stateId))
                        .addOrder(Order.asc("city")).list();
    }
}
