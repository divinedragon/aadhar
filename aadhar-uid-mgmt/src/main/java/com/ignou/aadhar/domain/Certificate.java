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
 * Certificate domain class corresponding to certificates table in the database.
 * @author Deepak Shakya
 *
 */

@Entity
@Table(name = "certificates")
public class Certificate extends AbstractTimestampEntity implements Serializable {

    /**
     * Auto-generated serialization id.
     */
    private static final long serialVersionUID = 1046876081674828672L;

    /**
     * Id value for each Certificate.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Citizen to which this certificate belongs to.
     */
    @ManyToOne
    @JoinColumn (name = "UID", referencedColumnName = "UID")
    private Citizen citizen;

    /**
     * Type of the certificate.
     */
    @Column(name = "Type")
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
