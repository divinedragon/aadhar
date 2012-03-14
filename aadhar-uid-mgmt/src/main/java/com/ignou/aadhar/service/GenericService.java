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
package com.ignou.aadhar.service;

import java.util.List;

/**
 * Generic Service interface which provides CRUD operation methods.
 * @author Deepak Shakya
 * @param <MODEL> Model Class that will give the final model behaviour.
 */
public interface GenericService<MODEL> {

    /**
     * Adds the model in the database.
     * @param model model object that gets added to the database.
     * @return The persistent object in the database.
     */
    MODEL add(MODEL model) throws RuntimeException;

    /**
     * Updates the model data in the database.
     * @param model model object that gets updated in the database.
     * @return The updated persistent object in the database.
     */
    MODEL modify(MODEL model) throws RuntimeException;

    /**
     * Gets the model object for the provided model id.
     * @param id Id of the model which needs to be fetched.
     * @return Model object corresponding to Id value passed. Null otherwise.
     */
    MODEL read(Integer id) throws RuntimeException;

    /**
     * Fetch all the models available in the database.
     * @return All models in the database.
     */
    List<MODEL> list() throws RuntimeException;

    /**
     * Deletes the model from the database for the provided model id.
     * @param id Id for the model which needs to be deleted.
     */
    void delete(Integer id) throws RuntimeException;
}
