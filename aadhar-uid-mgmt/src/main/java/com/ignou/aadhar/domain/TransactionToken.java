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
 * TransactionToken domain class corresponding to transaction_tokens table in
 * the database.
 * @author Deepak Shakya
 *
 */

@Entity
@Table(name = "transaction_tokens")
public class TransactionToken implements Serializable {

    /**
     * Auto-generated serialization id.
     */
    private static final long serialVersionUID = -8254860157884310073L;

    /**
     * Id value for each Address record.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Transaction reference.
     */
    @ManyToOne
    @JoinColumn (name = "TransactionId", referencedColumnName = "id")
    private Transaction transaction;

    /**
     * UID Reference.
     */
    @ManyToOne
    @JoinColumn (name = "UID", referencedColumnName = "uid")
    private Citizen citizen;

    /**
     * Authentication Token.
     */
    @Column(name = "Token", nullable = false)
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
