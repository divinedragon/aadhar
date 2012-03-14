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
package com.ignou.aadhar.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Account domain class corresponding to accounts table in the database.
 * @author Deepak Shakya
 *
 */

@Entity
@Table(name = "accounts")
public class Account extends AbstractTimestampEntity implements Serializable {

    /**
     * Auto-generated serialization id.
     */
    private static final long serialVersionUID = -5493088653336196297L;

    /**
     * Id value for each account.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Unique Account Number for each account.
     */
    @Column(name = "AccountNumber")
    private String accountNumber;

    /**
     * Bank with which this account is associated.
     */
    @ManyToOne
    @JoinColumn (name = "BankId", referencedColumnName = "id")
    private Bank bank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }
}
