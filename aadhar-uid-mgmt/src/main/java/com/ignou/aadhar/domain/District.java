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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * District domain class corresponding to district table in the database.
 * @author Deepak Shakya
 *
 */
@Entity
@Table(name = "district")
public class District implements Serializable {

    /**
     * Auto-generated serialization id.
     */
    private static final long serialVersionUID = 7208826300871628058L;

    /**
     * Id value for each district.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Name of the district.
     */
    @Column (name = "district")
    private String district;

    /**
     * State in which this district resides.
     */
    @ManyToOne
    @JoinColumn (name = "stateid", referencedColumnName = "id")
    private State state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
