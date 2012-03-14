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
import javax.persistence.Table;

/**
 * ServiceProvider domain class corresponding to serviceproviders table in the
 * database.
 * @author Deepak Shakya
 *
 */

@Entity
@Table(name = "serviceproviders")
public class ServiceProvider implements Serializable {

    /**
     * Auto-generated serialization id.
     */
    private static final long serialVersionUID = 289974464495870048L;

    /**
     * Id value for each Service Provider record.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Name of the Service Provider.
     */
    @Column(name = "Name")
    private String name;

    /**
     * Password for the Service Provider. Used to authenticate the requests from
     * service provider while performing monetary transactions.
     */
    @Column(name = "ServicePassword")
    private String password;

    /**
     * The URL from which the request for transaction will be made.
     */
    @Column(name = "RequestUrl")
    private String requestUrl;

    /**
     * The URL to which the transaction statuses are sent back.
     */
    @Column(name = "ResponseUrl")
    private String responseUrl;

    /**
     * Account Number of the Service Provider configured while performing
     * transactions.
     */
    @Column(name = "AccountNumber")
    private String accountNumber;

    /**
     * Bank IFS Code required to perform the transaction successfully.
     */
    @Column(name = "BankIFSCode")
    private String bankIFSCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getResponseUrl() {
        return responseUrl;
    }

    public void setResponseUrl(String responseUrl) {
        this.responseUrl = responseUrl;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankIFSCode() {
        return bankIFSCode;
    }

    public void setBankIFSCode(String bankIFSCode) {
        this.bankIFSCode = bankIFSCode;
    }
}
