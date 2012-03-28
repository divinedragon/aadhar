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

import org.junit.Assert;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ignou.aadhar.domain.ServiceProvider;

/**
 * Unit Testing class for BankDao interface.
 *
 * @author Deepak Shakya
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml",
                                    "classpath:spring-tests.xml" })
public class ServiceProviderDaoTest extends HibernateDaoSupport {

    @Autowired
    public void setupSessionFactory(SessionFactory hibernateSessionFactory) {
        this.setSessionFactory(hibernateSessionFactory);
    }

    @Autowired
    private ServiceProviderDao serviceProviderDao;

    @Test
    public void testGetByNameAndPassword() {
        String validName = "iMatrix";
        String validPassword = "8621ffdbc5698829397d97767ac13db3";

        ServiceProvider valid = serviceProviderDao
                                .getByNameAndPassword(validName, validPassword);
        Assert.assertNotNull(valid);
        Assert.assertTrue("Valid Service Provider not fetched",
                                valid.getId() > 0);

        ServiceProvider invalid = serviceProviderDao
                                                .getByNameAndPassword("", "");
        Assert.assertNull("Invalid Service Provider record fetched", invalid);
    }
}
