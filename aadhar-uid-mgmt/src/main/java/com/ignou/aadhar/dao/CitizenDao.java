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
package com.ignou.aadhar.dao;

import java.util.Map;

import com.ignou.aadhar.domain.Citizen;

/**
 * Data Access Object providing methods to perform operation on table - Citizen.
 * @author Deepak Shakya
 *
 */
public interface CitizenDao extends GenericDao<Citizen, Integer> {

    /**
     * All valid Statuses for a Citizen.
     * @return Map containing the Statuses. The keys are the actual values which
     * can be used for the Status field in the database. The values are Title
     * Case version of the keys.
     */
    public Map<String, String> getCitizenStatuses();

    /**
     * All valid Access Roles which can be granted to citizens.
     * @return Map containing the Access Roles. The keys are the actual values
     * which can be used for the AccessRole field in the database. The values
     * are Title Case version of the keys.
     */
    public Map<String, String> getAccessRoles();

    /**
     * All valid Gender values which can be selected by citizens.
     * @return Map containing the Gender values. The keys are the actual values
     * which can be used for the Gender field in the database. The values
     * are Title Case version of the keys.
     */
    public Map<String, String> getGenders();
}
