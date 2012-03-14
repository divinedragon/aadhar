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

import com.ignou.aadhar.domain.Bank;

/**
 * Unit Testing class for BankDao interface.
 * 
 * @author Deepak Shakya
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml",
        "classpath:spring-tests.xml" })
public class BankDaoTest extends HibernateDaoSupport {

    @Autowired
    public void setupSessionFactory(SessionFactory hibernateSessionFactory) {
        this.setSessionFactory(hibernateSessionFactory);
    }

    @Autowired
    private BankDao bankDao;

    /**
     * Adding a new bank into the database and obtaining the id for the same. We
     * again fetch the same record in a different object and then compare if the
     * name of the bank for both the object matches or not.
     */
    @Test
    public void testBank() {

        String strDummyBank1 = "DummyBank";
        String strDummyBank2 = "EvenMoreDummyBank";
        String strDummyUrl = "http://www.google.co.in/";

        /* Testing add() */
        Bank javaBank = new Bank();
        javaBank.setName(strDummyBank1);
        javaBank.setUrl(strDummyUrl);

        Bank newBank = bankDao.add(javaBank);
        Assert.assertNotNull("add() returned a null object", newBank);
        Assert.assertTrue("add() method failed to insert new record.",
                newBank.getId() > 0);

        /* Testing read() */
        Bank dbBank = bankDao.read(newBank.getId());
        Assert.assertTrue("read() didnt' read the inserted record correctly",
                strDummyBank1.equals(dbBank.getName()));

        /* Testing modify() */
        dbBank.setName(strDummyBank2);
        Bank dbBankUpdated = bankDao.modify(dbBank);
        Assert.assertTrue("modify() did not update the record correctly",
                strDummyBank2.equals(dbBankUpdated.getName()));

        /* Testing list() */
        List<Bank> banks = bankDao.list();
        Assert.assertTrue("list() did not return any records.",
                banks.size() > 0);

        /* Testing delete() */
        bankDao.delete(newBank.getId());
        Bank nonExistentBank = bankDao.read(newBank.getId());
        Assert.assertNull("delete() didn't remove the record", nonExistentBank);
    }

    /**
     * Testing the Null value being passed for Bank Name.
     */
    @Test
    public void testNullBankName() {

        /*
         * Create the New Bank object with Null Bank Name and try saving into
         * the database. It should give an exception.
         */
        Bank bank = new Bank();
        bank.setUrl("http://www.google.co.in");

        try {
            bankDao.add(bank);
            Assert.fail("NULL Bank Name was saved in DB.");
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    /**
     * Testing the Null value being passed for Bank URL.
     */
    @Test
    public void testNullBankUrl() {

        /*
         * Create the New Bank object with Null Bank URL and try saving into the
         * database. It should give an exception.
         */
        Bank bank = new Bank();
        bank.setName("DummyBank");

        try {
            bankDao.add(bank);
            Assert.fail("NULL Bank URL was saved in DB.");
        } catch (Exception e) {
            Assert.assertNotNull(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
    }
}
