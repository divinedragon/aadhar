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
package com.ignou.aadhar.constants;

/**
 * Static Constants used by the System.
 * @author Deepak Shakya
 *
 */
public interface Constants {

    /**
     * Total length of the generated UID.
     */
    public static final int UID_LENGTH = 15;

    /**
     * Default number of records to be displayed on the page in case this value
     * is not supplied in the request.
     */
    public static final Integer MAX_RECORDS_PER_PAGE = 15;

    public static final Integer START_PAGE = 1;
}
