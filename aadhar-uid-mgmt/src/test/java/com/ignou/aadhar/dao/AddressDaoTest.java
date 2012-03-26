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

import com.ignou.aadhar.domain.Address;

/**
 * Unit Testing class for AddressDao interface.
 * 
 * @author Deepak Shakya
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml",
        "classpath:spring-tests.xml" })
public class AddressDaoTest extends HibernateDaoSupport {

    @Autowired
    public void setupSessionFactory(SessionFactory hibernateSessionFactory) {
        this.setSessionFactory(hibernateSessionFactory);
    }

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private DistrictDao districtDao;

    @Autowired
    private StateDao stateDao;
    /**
     * Adding a new address into the database and obtaining the id for the same. We
     * again fetch the same record in a different object and then compare if the
     * name of the address for both the object matches or not.
     */
    @Test
    public void testAddress() {

        String strDummyLine1 = "DummyLine1";
        String strDummyLine2 = "DummyLine2";
        String strDummyLine3 = "DummyLine3";
        String strDummyArea  = "DummyArea";
        String strDummyCareOf = "DummyC/O";

        /* Testing add() */
        Address javaAddress = new Address();
        javaAddress.setCareOf("");
        javaAddress.setAddressLine1(strDummyLine1);
        javaAddress.setAddressLine2(strDummyLine2);
        javaAddress.setAddressLine3(strDummyLine3);
        javaAddress.setArea(strDummyArea);
        javaAddress.setCity(cityDao.read(1));
        javaAddress.setDistrict(districtDao.read(1));
        javaAddress.setState(stateDao.read(1));

        Address newAddress = addressDao.add(javaAddress);
        Assert.assertNotNull("add() returned a null object", newAddress);
        Assert.assertTrue("add() method failed to insert new record.",
                newAddress.getId() > 0);

        /* Testing read() */
        Address dbAddress = addressDao.read(newAddress.getId());
        Assert.assertTrue("read() didnt' read the inserted record correctly",
                strDummyLine1.equals(dbAddress.getAddressLine1()));

        /* Testing modify() */
        dbAddress.setCareOf(strDummyCareOf);
        Address dbAddressUpdated = addressDao.modify(dbAddress);
        Assert.assertTrue("modify() did not update the record correctly",
                strDummyCareOf.equals(dbAddressUpdated.getCareOf()));

        /* Testing list() */
        List<Address> addresss = addressDao.list();
        Assert.assertTrue("list() did not return any records.",
                addresss.size() > 0);

        /* Testing delete() */
        addressDao.delete(newAddress.getId());
        Address nonExistentAddress = addressDao.read(newAddress.getId());
        Assert.assertNull("delete() didn't remove the record", nonExistentAddress);
    }

    /**
     * Testing the Null value being passed for Address Line 1.
     */
    @Test
    public void testNullAddressLine1() {

        /*
         * Create the New Address object with Null Address Line1 and try saving
         * into the database. It should give an exception.
         */
        Address address = new Address();
        address.setAddressLine1(null);
        address.setAddressLine2("Line2");
        address.setAddressLine3("Line3");
        address.setArea("DummyArea");
        address.setCity(cityDao.read(1));
        address.setDistrict(districtDao.read(1));
        address.setState(stateDao.read(1));

        try {
            addressDao.add(address);
            Assert.fail("NULL Address Line 1 was saved in DB.");
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * Testing the Null value being passed for Area.
     */
    @Test
    public void testNullArea() {

        /*
         * Create the New Address object with Null Area and try saving
         * into the database. It should give an exception.
         */
        Address address = new Address();
        address.setAddressLine1("Line1");
        address.setAddressLine2("Line2");
        address.setAddressLine3("Line3");
        address.setArea(null);
        address.setCity(cityDao.read(1));
        address.setDistrict(districtDao.read(1));
        address.setState(stateDao.read(1));

        try {
            addressDao.add(address);
            Assert.fail("NULL Area was saved in DB.");
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * Testing the Null value being passed for City.
     */
    @Test
    public void testNullCity() {

        /*
         * Create the New Address object with Null City and try saving
         * into the database. It should give an exception.
         */
        Address address = new Address();
        address.setAddressLine1("Line1");
        address.setAddressLine2("Line2");
        address.setAddressLine3("Line3");
        address.setArea("DummyArea");
        address.setCity(null);
        address.setDistrict(districtDao.read(1));
        address.setState(stateDao.read(1));

        try {
            addressDao.add(address);
            Assert.fail("NULL City was saved in DB.");
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * Testing the Null value being passed for District.
     */
    @Test
    public void testNullDistrict() {

        /*
         * Create the New Address object with Null District and try saving
         * into the database. It should give an exception.
         */
        Address address = new Address();
        address.setAddressLine1("Line1");
        address.setAddressLine2("Line2");
        address.setAddressLine3("Line3");
        address.setArea("DummyArea");
        address.setCity(cityDao.read(1));
        address.setDistrict(null);
        address.setState(stateDao.read(1));

        try {
            addressDao.add(address);
            Assert.fail("NULL District was saved in DB.");
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * Testing the Null value being passed for State.
     */
    @Test
    public void testNullState() {

        /*
         * Create the New Address object with Null State and try saving
         * into the database. It should give an exception.
         */
        Address address = new Address();
        address.setAddressLine1("Line1");
        address.setAddressLine2("Line2");
        address.setAddressLine3("Line3");
        address.setArea("DummyArea");
        address.setCity(cityDao.read(1));
        address.setDistrict(districtDao.read(1));
        address.setState(null);

        try {
            addressDao.add(address);
            Assert.fail("NULL State was saved in DB.");
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * Testing for fetchOrCreate() method.
     */
    @Test
    public void testFetchOrCreate() {

        /* We will fetch an existing db record and create another object with
         * same values. Then we will test if the object returned from
         * fetchOrCreate and the existing db record are same.
         */
        Address dbAddress = addressDao.read(1);

        Address testAddress1 = new Address();
        testAddress1.setAddressLine1(dbAddress.getAddressLine1());
        testAddress1.setAddressLine2(dbAddress.getAddressLine2());
        testAddress1.setAddressLine3(dbAddress.getAddressLine3());
        testAddress1.setCity(dbAddress.getCity());

        Address address = addressDao.fetchOrCreate(testAddress1);

        Assert.assertEquals("Existing Address record not fetchet",
                dbAddress.getId(), address.getId());

        /* Lets now create a new object and see it gets inserted or not. */
        Address newAddress = new Address();
        newAddress.setAddressLine1("Line1");
        newAddress.setAddressLine2("Line2");
        newAddress.setAddressLine3("Line3");
        newAddress.setArea("DummyArea");
        newAddress.setCity(cityDao.read(1));
        newAddress.setDistrict(districtDao.read(1));
        newAddress.setState(stateDao.read(1));

        address = addressDao.fetchOrCreate(newAddress);

        Assert.assertTrue("Non-existent record was not created",
                address.getId() > 0);

        addressDao.delete(address.getId());
    }
}
