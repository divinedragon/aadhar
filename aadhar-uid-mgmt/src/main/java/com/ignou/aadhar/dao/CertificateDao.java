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

import com.ignou.aadhar.domain.Certificate;

/**
 * Data Access Object providing methods to perform operations on table - Certificate.
 * @author Deepak Shakya
 *
 */
public interface CertificateDao extends GenericDao<Certificate, Integer> {

    /**
     * All valid Types of Certificates supported by the Database.
     * @return Map containing the Types of the certificates. The keys are the
     * actual values which can be used for Certificate field in the database.
     * The values are Title Case version of the keys.
     */
    public Map<String, String> getTypes();
}
