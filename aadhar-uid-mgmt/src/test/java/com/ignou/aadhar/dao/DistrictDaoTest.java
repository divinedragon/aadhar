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

import org.junit.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ignou.aadhar.domain.District;
import com.ignou.aadhar.domain.State;

/**
 * Unit Testing class for DistrictDao interface.
 * @author Deepak Shakya
 *
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = { "classpath:spring-context.xml",
                                     "classpath:spring-tests.xml" })
public class DistrictDaoTest extends HibernateDaoSupport {

    @Autowired
    public void setupSessionFactory(SessionFactory hibernateSessionFactory) {
        this.setSessionFactory(hibernateSessionFactory);
    }

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private StateDao stateDao;

    /**
     * Adding a new district into the database & obtaining the id for the same.
     * We again fetch the same record in a different object and then compare
     * if the name of the district for both the object matches or not.
     */
    @Test
    public void testDistrict() {

        String strDummyDistrict1 = "DummyDistrict";
        String strDummyDistrict2 = "EvenMoreDummyDistrict";

        /* Load the first state in the database */
        State state = stateDao.read(1);

        /* Testing add() */
        District javaDistrict = new District();
        javaDistrict.setDistrict(strDummyDistrict1);
        javaDistrict.setState(state);

        District newDistrict = districtDao.add(javaDistrict);
        Assert.assertNotNull("add() returned a null object", newDistrict);
        Assert.assertTrue("add() method failed to insert new record.",
                newDistrict.getId() > 0);

        /* Testing read() */
        District dbDistrict = districtDao.read(newDistrict.getId());
        Assert.assertTrue("read() didm't read the inserted record correctly",
                strDummyDistrict1.equals(dbDistrict.getDistrict()));

        /* Testing modify() */
        dbDistrict.setDistrict(strDummyDistrict2);
        District dbDistrictUpdated = districtDao.modify(dbDistrict);
        Assert.assertTrue("modify() did not update the record correctly",
                strDummyDistrict2.equals(dbDistrictUpdated.getDistrict()));

        /* Testing list() */
        List<District> cities = districtDao.list();
        Assert.assertTrue("list() did not return any records.",
                cities.size() > 0);

        /* Testing delete() */
        districtDao.delete(newDistrict.getId());
        District nonExistentDistrict = districtDao.read(newDistrict.getId());
        Assert.assertNull("delete() did not remove the record",
                nonExistentDistrict);
    }

    /**
     * Testing the Association between the state table and district table.
     */
    @Test
    public void testValidDistrictAndState() {
        /* State ID of Haryana in DB = 13 */
        Integer stateIdHaryana = 13;

        /* District Id of Ambala in Haryana in district table = 1 */
        Integer ambalaId = 1;
        District districtInHaryana = districtDao.read(ambalaId);

        Assert.assertEquals("State details not fetched in District.",
                districtInHaryana.getState().getId(), stateIdHaryana);
    }
}
