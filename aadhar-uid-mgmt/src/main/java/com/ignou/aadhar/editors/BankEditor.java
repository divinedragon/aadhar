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
package com.ignou.aadhar.editors;

import java.beans.PropertyEditorSupport;

import com.ignou.aadhar.dao.GenericDao;
import com.ignou.aadhar.domain.Bank;

/**
 * Editor class for Bank to allow Spring map bank objects based on their ids
 * selected on the form by the user.
 * @author Deepak Shakya
 *
 */
public class BankEditor extends PropertyEditorSupport {

    /**
     * Using the GenericDao as we don't have to perform any specilized operation
     * from the bank table. We need to read the objects based on their id value
     * selected on the form.
     */
    private final GenericDao<Bank, Integer> bankDao;

    /**
     * Constructor to create the object.
     * @param bankDao BankDao object used for performing the database
     * operations.
     */
    public BankEditor(GenericDao<Bank, Integer> bankDao) {
        this.bankDao = bankDao;
    }

    /**
     * Sets the bank object value based on the id selected on the form.
     * @param text id value for which the corresponding state has to be set.
     */
    @Override
    public void setAsText(String text) {
        setValue(bankDao.read(Integer.parseInt(text, 10)));
    }

    /**
     * Returns the state id of the state which is selected by the user.
     * @return Id value of the State
     */
    @Override
    public String getAsText() {
        Bank bank = (Bank) getValue();

        if (bank == null) {
            return null;
        } else {
            return bank.getId().toString();
        }
    }
}
