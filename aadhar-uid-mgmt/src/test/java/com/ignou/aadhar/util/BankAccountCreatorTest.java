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
package com.ignou.aadhar.util;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ignou.aadhar.util.json.JsonWrapper;

/**
 * Unit Testing class for StateDao interface.
 * @author Deepak Shakya
 *
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = { "classpath:spring-context.xml",
                                     "classpath:spring-tests.xml" })
public class BankAccountCreatorTest {

    private BankAccountCreator creatorObj;

    @Before
    public void init() {
        creatorObj = new BankAccountCreator();
    }

    @Test
    public void testValidCreateAccount() {

        JsonWrapper output = creatorObj.createAccount("1234567890");
        Assert.assertEquals("Success response not returned.",
                "SUCCESS", output.get("status"));
        Assert.assertTrue("No Account ID was returned in Success response",
                ((String) output.get("id")).length() > 0);
    }

    @Test
    public void testInvalidCreateAccount() {

        JsonWrapper output = creatorObj.createAccount("1234567890abcdefghijkl");
        Assert.assertEquals("Failure response not returned.",
                "FAILURE", output.get("status"));
        Assert.assertNull("Account ID returned for invalid UID", output.get("id"));
    }
}
