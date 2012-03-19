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

/**
 * Editor class for Password fields to allow Spring to generate MD5 passwords
 * for the password values.
 * @author Deepak Shakya
 *
 */
public class PasswordEditor extends PropertyEditorSupport {

    /**
     * Sets the state object value based on the id selected on the form.
     * @param text id value for which the corresponding state has to be set.
     */
    @Override
    public void setAsText(String text) {
        return;
    }

    /**
     * Returns the state id of the state which is selected by the user.
     * @return Id value of the State
     */
    @Override
    public String getAsText() {
        return null;
    }
}
