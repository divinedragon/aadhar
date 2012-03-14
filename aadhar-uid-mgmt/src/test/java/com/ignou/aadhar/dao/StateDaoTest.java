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

import com.ignou.aadhar.domain.State;

/**
 * Unit Testing class for StateDao interface.
 * @author Deepak Shakya
 *
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = { "classpath:spring-context.xml",
                                     "classpath:spring-tests.xml" })
public class StateDaoTest extends HibernateDaoSupport {

    @Autowired
    public void setupSessionFactory(SessionFactory hibernateSessionFactory) {
        this.setSessionFactory(hibernateSessionFactory);
    }

    @Autowired
    private StateDao StateDao;

    /**
     * Adding a new State into the database and obtaining the id for the same.
     * We again fetch the same record in a different object and then compare
     * if the name of the State for both the object matches or not.
     */
    @Test
    public void testState() {

        String strDummyState1 = "DummyState";
        String strDummyState2 = "EvenMoreDummyState";

        /* Testing add() */
        State javaState = new State();
        javaState.setState(strDummyState1);

        State newState = StateDao.add(javaState);
        Assert.assertNotNull("add() returned a null object", newState);
        Assert.assertTrue("add() method failed to insert new record.",
                newState.getId() > 0);

        /* Testing read() */
        State dbState = StateDao.read(newState.getId());
        Assert.assertTrue("read() didn't read the inserted record correctly",
                strDummyState1.equals(dbState.getState()));

        /* Testing modify() */
        dbState.setState(strDummyState2);
        State dbStateUpdated = StateDao.modify(dbState);
        Assert.assertTrue("modify() did not update the record correctly",
                strDummyState2.equals(dbStateUpdated.getState()));

        /* Testing list() */
        List<State> states = StateDao.list();
        Assert.assertTrue("list() did not return any records.",
                states.size() > 0);

        /* Testing delete() */
        StateDao.delete(newState.getId());
        State nonExistentState = StateDao.read(newState.getId());
        Assert.assertNull("delete() didnt remove the record", nonExistentState);
    }
}
