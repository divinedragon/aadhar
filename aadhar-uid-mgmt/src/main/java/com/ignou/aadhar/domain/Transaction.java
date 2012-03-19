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
 * Transaction domain class corresponding to transaction table in the database.
 * @author Deepak Shakya
 *
 */
@Entity
@Table(name = "transaction")
public class Transaction
                    extends AbstractTimestampEntity implements Serializable {

    /**
     * Auto-generated serialization id.
     */
    private static final long serialVersionUID = -3581823408778986978L;

    /**
     * Id value for each state.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Citizen for which this transaction is recorded.
     */
    @ManyToOne
    @JoinColumn (name = "UID", referencedColumnName = "UID")
    private Citizen citizen;

    /**
     * Approved Service Provider who requested this transaction on behalf of
     * the citizen.
     */
    @ManyToOne
    @JoinColumn (name = "ServiceProviderId", referencedColumnName = "id")
    private ServiceProvider serviceProvider;

    /**
     * Transaction ID of the Service Provider which is used by the Service
     * Provider to track the transaction at its end.
     */
    @Column (name = "ServiceProviderTransactionId")
    private String spTransactionId;

    /**
     * Transaction ID of the Bank which is used by the Bank to track the
     * transaction at its end.
     */
    @Column (name = "BankTransactionId")
    private String bankTransactionId;

    /**
     * Status of the transaction if it got completed successfully or not.
     */
    @Column (name = "Status")
    private String status;

    /**
     * Transaction amount which is debited from citizen's account to service
     * provider's account.
     */
    @Column (name = "Amount")
    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getSpTransactionId() {
        return spTransactionId;
    }

    public void setSpTransactionId(String spTransactionId) {
        this.spTransactionId = spTransactionId;
    }

    public String getBankTransactionId() {
        return bankTransactionId;
    }

    public void setBankTransactionId(String bankTransactionId) {
        this.bankTransactionId = bankTransactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
