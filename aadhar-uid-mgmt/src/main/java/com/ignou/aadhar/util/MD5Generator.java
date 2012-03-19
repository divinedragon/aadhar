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
package com.ignou.aadhar.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5 Generator for encrypting the password values while storing them in the
 * database.
 * @author Deepak Shakya
 *
 */
public class MD5Generator {

    public static String encode(String data) throws Exception {

        /* Check the validity of data */
        if (data == null && data.isEmpty()) {
            throw new IllegalArgumentException("Null value provided for MD5 Encoding");
        }

        /* Get the instances for a given digest scheme MD5 or SHA */
        MessageDigest m = MessageDigest.getInstance("MD5");

        /* Generate the digest. Pass in the text as bytes, length to the
         * bytes(offset) to be hashed; for full string pass 0 to text.length()
         */
        m.update(data.getBytes(), 0, data.length());

        /* Get the String representation of hash bytes, create a big integer
         * out of bytes then convert it into hex value
         * (16 as input to toString method)
         */
        String digest = new BigInteger(1, m.digest()).toString(16);

        return digest;
    }
}
