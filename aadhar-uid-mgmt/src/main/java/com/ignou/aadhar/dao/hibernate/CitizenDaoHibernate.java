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
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.ignou.aadhar.constants.UIDStates;
import com.ignou.aadhar.dao.CitizenDao;
import com.ignou.aadhar.domain.Citizen;

/**
 * Concrete DAO class for hibernate for performing operations on citizen table.
 * @author Deepak Shakya
 *
 */
public class CitizenDaoHibernate extends GenericDaoHibernate<Citizen, Integer>
                                implements CitizenDao {

    /**
     * Default constructor which passes the class name.
     */
    public CitizenDaoHibernate() {
        super(Citizen.class);
    }

    /**
     * Setting the created date column every time the add method is called.
     */
    @Override
    public Citizen add(Citizen model) {
        model.setCreated(new Date());
        return super.add(model);
    } 
    
    /**
     * All valid Statuses for a Citizen.
     * @return Map containing the Statuses. The keys are the actual values which
     * can be used for the Status field in the database. The values are Title
     * Case version of the keys.
     */
    @Override
    public Map<String, String> getCitizenStatuses() {
        return getEnum("citizen", "Status");
    }

    /**
     * All valid Access Roles which can be granted to citizens.
     * @return Map containing the Access Roles. The keys are the actual values
     * which can be used for the AccessRole field in the database. The values
     * are Title Case version of the keys.
     */
    @Override
    public Map<String, String> getAccessRoles() {
        return getEnum("citizen", "AccessRole");
    }

    /**
     * All valid Gender values which can be selected by citizens.
     * @return Map containing the Gender values. The keys are the actual values
     * which can be used for the Gender field in the database. The values
     * are Title Case version of the keys.
     */
    @Override
    public Map<String, String> getGenders() {
        return getEnum("citizen", "Gender");
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
    public List<Map<String, Object>> getCitizens(String searchField,
            String searchValue, Integer pageNumber, Integer recordsPerPage,
            String sortField, String sortOrder) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Fetches the citizen record for the provided UID.
     * @param uid UID for which citizen record needs to be fetched.
     * @return Citizen record object.
     */
    @Override
    @Transactional
    public Citizen getByUID(String uid) {
        return (Citizen) getSessionFactory().getCurrentSession()
                            .createCriteria(Citizen.class)
                            .add(Restrictions.eq("uid", uid))
                            .add(Restrictions.eq("status", UIDStates.ACTIVE.getCode()))
                            .uniqueResult();
    }
}
