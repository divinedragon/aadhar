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

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.ignou.aadhar.dao.GenericDao;

/**
 * Generic Dao for Hibernate to perform the CRUD operations for the Models.
 * @author Deepak Shakya
 * @param <MODEL> Model class that will provide the final functionality.
 */
@Repository
public class GenericDaoHibernate<MODEL, PRIMARYKEY extends Serializable>
                                    extends HibernateDaoSupport
                                    implements GenericDao<MODEL, PRIMARYKEY> {

    /**
     * Hibernate Session Factory instance used to perform the database
     * operations.
     */
    @Autowired
    public void init(SessionFactory hibernateSessionFactory) {
        setSessionFactory(hibernateSessionFactory);
    }

    /**
     * Stores the Final name of the implementing class.
     */
    private Class<MODEL> type;

    /**
     * Default constructor.
     * @param type Name of the implementing class.
     */
    public GenericDaoHibernate(final Class<MODEL> type) {
        this.type = type;
    }

    /**
     * Adds the model in the database.
     * @param model model object that gets added to the database.
     * @return The persistent object in the database
     */
    public MODEL add(final MODEL model) {
        return (MODEL) getHibernateTemplate().merge(model);
    }

    /**
     * Updates the model data in the database.
     * @param model model object that gets updated in the database.
     * @return The updated persistent object in the database.
     */
    public MODEL modify(final MODEL model) {
        return getHibernateTemplate().merge(model);
    }

    /**
     * Reads the model from the database for the provided model id.
     * @param id Id for the model which needs to be read.
     * @return model Model object corresponding to the provided model id.
     */
    public MODEL read(PRIMARYKEY id) {
        return (MODEL) getHibernateTemplate().get(type, id);
    }

    /**
     * Fetch all the models available in the database.
     * @return All models in the database.
     */
    public List<MODEL> list() {
        DetachedCriteria criteria = DetachedCriteria.forClass(type);
        return getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * Deletes the model from the database for the provided model id.
     * @param id Id for the model which needs to be deleted.
     */
    public void delete(PRIMARYKEY id) {

        /* First read the model from the database */
        MODEL model = read(id);

        /* Now delete the object from the database */
        getHibernateTemplate().delete(model);
    }

    /**
     * Fetches the Enum values for the provided column in the specified table.
     * @param tableName Table name in the database
     * @param columnName Column in the table.
     * @return Returns the ENUM values specified for the column in the table. It
     * does not format the ENUM values and they are available in the following
     * format.<br/>
     * <code>('ENUMVAL1','ENUMVAL2')</code>
     */
    @Override
    public Map<String, String> getEnum(String tableName, String columnName) {

        /* Generalized query for fetching the ENUM data for the column in the
         * required table specified.
         */
        String strSqlQuery = "SELECT SUBSTRING(COLUMN_TYPE,5) EnumData " +
                "FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_SCHEMA = 'aadhar' " +
                "AND TABLE_NAME = '" + tableName + "' " +
                "AND COLUMN_NAME = '" + columnName + "';";

        /* Get the Hibernate Session instance to perform db operation */
        Session session = getSessionFactory().getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(strSqlQuery);

        /* Execute the query and fetch the enum data as string */
        String enumData = (String) sqlQuery.uniqueResult();

        /* Remove the surrounding braces and quotes and return back */
        enumData = enumData.replaceAll("['()]", "");

        /* Lets split the enum data csv and make a map.
         * For example, for ENUM data - ACTIVE,PENDING, the corresponding map
         * would be { "ACTIVE" : "Active", "PENDING" : "Pending" }
         */
        List<String> enums = Arrays.asList(enumData.split(","));
        Map<String, String> enumMap = new HashMap<String, String>();

        /* The the enum values in the Map now */
        for (String enumEl : enums) {
            String enumElTitleCase = enumEl.charAt(0) + enumEl.substring(1).toLowerCase();
            enumMap.put(enumEl, enumElTitleCase.replaceAll("[^A-Za-z]", " "));
        }

        return enumMap;
    }
}


