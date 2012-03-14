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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Citizen domain class corresponding to people table in the database.
 * @author Deepak Shakya
 *
 */

@Entity
@Table(name = "citizen")
public class Citizen extends AbstractTimestampEntity implements Serializable {

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
     * UID generated for this citizen object.
     */
    @Column (name = "UID", nullable = false)
    private String uid;

    /**
     * Password for this citizen object.
     */
    @Column (name = "Password", nullable = false)
    private String password;

    /**
     * Name of the citizen.
     */
    @Column (name = "Name", nullable = false)
    private String name;

    /**
     * Gender of the citizen.
     */
    @Column (name = "Gender", nullable = false)
    private String gender;

    /**
     * Date of Birth of the citizen.
     */
    @Column (name = "DateOfBirth", nullable = false)
    private Date dateOfBirth;

    /**
     * Local address of the citizen.
     */
    @ManyToOne
    @JoinColumn (name = "LocalAddressId", referencedColumnName = "id")
    private Address localAddress;

    /**
     * Permanent address of the citizen.
     */
    @ManyToOne
    @JoinColumn (name = "PermanentAddressId", referencedColumnName = "id")
    private Address permanentAddress;

    /**
     * Email address of the citizen.
     */
    @Column (name = "Email", nullable = false)
    private String email;

    /**
     * Mobile number of the citizen.
     */
    @Column (name = "Mobile", nullable = false)
    private String mobile;

    /**
     * Account opened for all monetary transactions performed.
     */
    @ManyToOne
    @JoinColumn (name = "AccountId", referencedColumnName = "id")
    private Account account;

    /**
     * Access Role of the citizen. Provides different modes of access to this
     * system.
     */
    @Column (name = "AccessRole", nullable = false)
    private String accessRole;

    /**
     * Status of the citizen.
     */
    @Column (name = "Status", nullable = false)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(Address localAddress) {
        this.localAddress = localAddress;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(String accessRole) {
        this.accessRole = accessRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
