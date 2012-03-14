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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Abstract Entity for re-use of Timestamp columns of the database. Make sure
 * all the Timestamp columns of the database have the same name as provided in
 * this class.
 * @author Deepak Shakya
 *
 */
@MappedSuperclass
public class AbstractTimestampEntity {

    /**
     * Creation Date of the object in the database.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreationDate", nullable = false)
    private Date created;

    /**
     * Before persisting this object, create a new instance of the current
     * timestamp value and set it in the object.
     */
    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
