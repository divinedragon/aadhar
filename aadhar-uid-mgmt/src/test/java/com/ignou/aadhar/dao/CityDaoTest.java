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

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ignou.aadhar.domain.City;
import com.ignou.aadhar.domain.State;

/**
 * Unit Testing class for CityDao interface.
 * @author Deepak Shakya
 *
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = { "classpath:spring-context.xml",
                                     "classpath:spring-tests.xml" })
public class CityDaoTest extends HibernateDaoSupport {

    @Autowired
    public void setupSessionFactory(SessionFactory hibernateSessionFactory) {
        this.setSessionFactory(hibernateSessionFactory);
    }

    @Autowired
    private CityDao cityDao;

    @Autowired
    private StateDao stateDao;

    /**
     * Adding a new city into the database and obtaining the id for the same.
     * We again fetch the same record in a different object and then compare
     * if the name of the city for both the object matches or not.
     */

    public void testCity() {

        String strDummyCity1 = "DummyCity";
        String strDummyCity2 = "EvenMoreDummyCity";

        /* Load the first state in the database */
        State state = stateDao.read(1);

        /* Testing add() */
        City javaCity = new City();
        javaCity.setCity(strDummyCity1);
        javaCity.setState(state);

        City newCity = cityDao.add(javaCity);
        Assert.assertNotNull("add() returned a null object", newCity);
        Assert.assertTrue("add() method failed to insert new record.",
                newCity.getId() > 0);

        /* Testing read() */
        City dbCity = cityDao.read(newCity.getId());
        Assert.assertTrue("read() didnt' read the inserted record correctly",
                strDummyCity1.equals(dbCity.getCity()));

        /* Testing modify() */
        dbCity.setCity(strDummyCity2);
        City dbCityUpdated = cityDao.modify(dbCity);
        Assert.assertTrue("modify() did not update the record correctly",
                strDummyCity2.equals(dbCityUpdated.getCity()));

        /* Testing list() */
        List<City> cities = cityDao.list();
        Assert.assertTrue("list() did not return any records.",
                cities.size() > 0);

        /* Testing delete() */
        cityDao.delete(newCity.getId());
        City nonExistentCity = cityDao.read(newCity.getId());
        Assert.assertNull("delete() didn't remove the record",
                nonExistentCity);
    }

    /**
     * Testing the Association between the state table and city table.
     */

    public void testValidCityAndState() {
        /* State ID of Assam in DB = 4 */
        Integer stateIdAssam = 4;

        /* City Id of Abhayapuri in Assam in city table = 1*/
        Integer abhayapuriId = 1;
        City cityInAssam = cityDao.read(abhayapuriId);

        Assert.assertEquals("State details not fetched in City.",
                cityInAssam.getState().getId(), stateIdAssam);
    }

    /**
     * Testing the record fetching logic for search parameters and record
     * parameters from City table.
     */
    public void testGetCities() {

        List<Map<String, Object>> cities;

        /* Pull First 15 rows */
        cities = cityDao.getCities(null, null, 0, 15, null, null);
        Assert.assertTrue("No records fetched when pulled first 15 records.",
                cities.size() == 15);

        /* Pull only 1 row */
        cities = cityDao.getCities(null, null, 0, 1, null, null);
        Assert.assertTrue("Failed to pull only 1 record", cities.size() == 1);

        /* Pull just the record for rajkot. Record count = 1 */
        cities = cityDao.getCities("city", "rajkot", 0, 15, null, null);
        Assert.assertTrue("No records fetched from city search",
                cities.size() == 1);

        /* Pull records for state Goa. */
        cities = cityDao.getCities("state", "Goa", 0, 15, null, null);
        Assert.assertEquals("No records fetched from state search ",
                "Goa", cities.get(0).get("state"));

        /* Because we cannot check the ordering of records in test, we are just
         * passing the parameters to see if they fail to return records. If they
         * are fetching some records, we should be fine.
         */
        /* Only 1 record with default sorting on city field */
        cities = cityDao.getCities(null, null, 0, 1, "city", null);
        Assert.assertTrue("No records fetched with only 1 record and default"
                    + " sorting on city column", cities.size() == 1);

        /* Only 1 record with descending sorting on city field */
        cities = cityDao.getCities(null, null, 0, 1, "city", "desc");
        Assert.assertTrue("No records fetched with only 1 record and DESC"
                    + " sorting on city column", cities.size() == 1);
    }

    @Test
    public void testGetCitiesForStateId() {
        
        List<City> cities = cityDao.getCitiesForStateId(2);
        
        Assert.assertNotNull("Null object returned", cities);
        Assert.assertTrue("No cities returned for State ID - 2", cities.size() > 0);
        
        System.out.println(cities);
        
    }
}
