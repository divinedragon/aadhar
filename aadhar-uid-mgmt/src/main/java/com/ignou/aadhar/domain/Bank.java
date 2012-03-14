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
 * Bank domain class corresponding to banks table in the database.
 * @author Deepak Shakya
 *
 */

@Entity
@Table(name = "banks")
public class Bank implements Serializable {

    /**
     * Auto-generated serialization id.
     */
    private static final long serialVersionUID = 5343254175929409013L;

    /**
     * Id value for each bank.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Name of the Bank.
     */
    @Column(name = "Name")
    private String name;

    /**
     * URL of the Bank on which transaction requests will be made.
     */
    @Column(name = "Url")
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
