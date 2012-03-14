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
 * Address domain class corresponding to address table in the database.
 * @author Deepak Shakya
 *
 */

@Entity
@Table(name = "address")
public class Address implements Serializable {

    /**
     * Auto-generated serialization id.
     */
    private static final long serialVersionUID = 8049934894829093112L;

    /**
     * Id value for each Address record.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Care Of field. Just in case used for addresses.
     */
    @Column(name = "CO")
    private String careOf;

    /**
     * Address Line 1 to provide initial address details like House No. etc.
     */
    @Column(name = "Line1", nullable = false)
    private String addressLine1;

    /**
     * Address Line 2 to provide address details like society name etc.
     */
    @Column(name = "Line2", nullable = true)
    private String addressLine2;

    /**
     * Address Line 3 to provide address details like locality name etc.
     */
    @Column(name = "Line3", nullable = true)
    private String addressLine3;

    /**
     * Area to which this address belongs to.
     */
    @Column(name = "Area", nullable = false)
    private String area;

    /**
     * City in which this address resides.
     */
    @ManyToOne
    @JoinColumn (name = "CityId", referencedColumnName = "id")
    private City city;

    /**
     * District in which this address resides.
     */
    @ManyToOne
    @JoinColumn (name = "DistrictId", referencedColumnName = "id")
    private District district;

    /**
     * State in which this address resides.
     */
    @ManyToOne
    @JoinColumn (name = "StateId", referencedColumnName = "id")
    private State state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCareOf() {
        return careOf;
    }

    public void setCareOf(String careOf) {
        this.careOf = careOf;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
