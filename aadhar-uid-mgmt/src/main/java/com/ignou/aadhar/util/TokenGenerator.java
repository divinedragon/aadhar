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

/**
 * Token generator for TransactionTokens.
 * @author Deepak Shakya
 *
 */
public class TokenGenerator {

    /**
     * Token length
     */
    private static final int TOKEN_LENGTH = 6; 

    /**
     * Method which returns a token.
     * @return Generated token.
     */
    public static String getToken() {

        Double token = Math.random() * 1000000000;

        String strToken = token.intValue() + "";
        
        return strToken.substring(0, TOKEN_LENGTH);
    }
}
