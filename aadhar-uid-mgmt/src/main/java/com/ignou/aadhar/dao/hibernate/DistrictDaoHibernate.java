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

import com.ignou.aadhar.dao.DistrictDao;
import com.ignou.aadhar.domain.District;

/**
 * Concrete DAO class for hibernate for performing operations on district table.
 * @author Deepak Shakya
 *
 */
public class DistrictDaoHibernate extends GenericDaoHibernate<District, Integer>
                                implements DistrictDao {

    /**
     * Default constructor which passes the class name.
     */
    public DistrictDaoHibernate() {
        super(District.class);
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
    public List<Map<String, Object>> getDistricts(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {

        List<District> districts = null;
        List<Map<String, Object>> returnDistricts = new ArrayList<Map<String,Object>>();

        Criteria criteria = getSessionFactory().getCurrentSession()
                                    .createCriteria(District.class, "d")
                                        .createAlias("state", "s");

        /* Add the search parameters to the criteria */
        if (searchField != null && !searchField.isEmpty()
                && searchValue != null && !searchValue.isEmpty()) {

            /* Prefix and suffix the searchValue with % */
            searchValue = "%" + searchValue + "%";

            /* Now there are only two fields which we can search here. */
            if ("district".equals(searchField)) {
                criteria.add(Restrictions.ilike("d.district", searchValue));
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

        /* Set the record filtering on pageCount and recordsPerPage if they are
         * available.
         */
        if (pageNumber != null && recordsPerPage != null) {
            districts = criteria.setFirstResult(pageNumber)
                              .setMaxResults(recordsPerPage)
                              .list();
        } else {
            districts = criteria.list();
        }

        /* Format this data before sending back for any other further usage */
        for (District districtRecord : districts) {

            /* Create new map for current district and add it to master list */
            Map<String, Object> returnMap = new LinkedHashMap<String, Object>();
            returnMap.put("id", districtRecord.getId());
            returnMap.put("district", districtRecord.getDistrict());
            returnMap.put("state", districtRecord.getState().getState());
            returnMap.put("totalCount", totalCount);

            returnDistricts.add(returnMap);
        }

        return returnDistricts;
    }

    /**
     * Gets the Districts which are linked to the stateId provided.
     * @param stateId State id for which all the districts will be returned.
     * @return All district objects for the stateId as a list.
     */
    @Override
    public List<District> getDistrictsForStateId(Integer stateId) {
        return getSessionFactory().getCurrentSession()
                .createCriteria(District.class)
                    .add(Restrictions.eq("state.id", stateId))
                        .addOrder(Order.asc("district")).list();
    }
}
