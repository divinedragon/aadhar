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

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic Data access object interface which provides CRUD operation methods.
 * @author Deepak Shakya
 * @param <MODEL> Model Class that will give the final model behaviour.
 * @param <PRIMARYKEY> Class used for Primary Key column in model table.
 */
public interface GenericDao<MODEL, PRIMARYKEY extends Serializable> {

    /**
     * Adds the model in the database.
     * @param model model object that gets added to the database.
     * @return The persistent object in the database
     */
    MODEL add(MODEL model);

    /**
     * Updates the model data in the database.
     * @param model model object that gets updated in the database.
     * @return The updated persistent object in the database.
     */
    MODEL modify(MODEL model);

    /**
     * Reads the model from the database for the provided model id.
     * @param id Id for the model which needs to be read.
     * @return model Model object corresponding to the provided model id.
     */
    MODEL read(PRIMARYKEY id);

    /**
     * Fetch all the models available in the database.
     * @return All models in the database.
     */
    List<MODEL> list();

    /**
     * Deletes the model from the database for the provided model id.
     * @param id Id for the model which needs to be deleted.
     */
    void delete(PRIMARYKEY id);

    /**
     * Fetches the Enum values for the provided column in the specified table.
     * @param tableName Table name in the database
     * @param columnName Column in the table.
     * @return Returns the ENUM values specified for the column in the table. It
     * does not format the ENUM values and they are available in the following
     * format.<br/>
     * <code>('ENUMVAL1','ENUMVAL2')</code>
     */
    public Map<String, String> getEnum(String tableName, String columnName);
}
