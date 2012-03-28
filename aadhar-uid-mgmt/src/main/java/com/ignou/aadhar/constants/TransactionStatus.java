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
package com.ignou.aadhar.constants;

/**
 * Enumeration to define the states for a Transaction.
 * @author Deepak Shakya
 *
 */
public enum TransactionStatus {

    /**
     * State - FAILED.
     */
    FAILED("FAILED"),

    /**
     * State - SUCCESS.
     */
    SUCCESS("SUCCESS"),

    /**
     * State - PENDING.
     */
    PENDING("PENDING");

    private TransactionStatus(String code) {
        setCode(code);
    }

    private String code;

    private void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
