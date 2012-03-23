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
package com.ignou.aadhar.editors;

import java.beans.PropertyEditorSupport;

import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.District;

/**
 * Editor class for District to allow Spring map district objects based on their ids
 * selected on the form by the user.
 * @author Deepak Shakya
 *
 */
public class DistrictEditor extends PropertyEditorSupport {

    /**
     * Using the GenericDao as we don't have to perform any specilized operation
     * from the district table. We need to read the objects based on their id value
     * selected on the form.
     */
    private final GenericDao<District, Integer> districtDao;

    /**
     * Constructor to create the object.
     * @param districtDao DistrictDao object used for performing the database
     * operations.
     */
    public DistrictEditor(GenericDao<District, Integer> districtDao) {
        this.districtDao = districtDao;
    }

    /**
     * Sets the district object value based on the id selected on the form.
     * @param text id value for which the corresponding district has to be set.
     */
    @Override
    public void setAsText(String text) {
        setValue(districtDao.read(Integer.parseInt(text, 10)));
    }

    /**
     * Returns the district id of the district which is selected by the user.
     * @return Id value of the District
     */
    @Override
    public String getAsText() {
        District district = (District) getValue();

        if (district == null) {
            return null;
        } else {
            return district.getId().toString();
        }
    }
}
