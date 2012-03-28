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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Email sending utility class.
 * @author Deepak Shakya
 *
 */
public class EmailSender {

    /**
     * Sends the email for the specified parameter values.
     * @param to Recipient of the email.
     * @param subject Subject of the email.
     * @param mailContent HTML content of the email.
     */
    public void send(String to, String subject, String mailContent) {

        /* Load the Mail configuration properties */
        Properties mailProperties = new Properties();
        InputStream in = this.getClass().getClassLoader()
                            .getResourceAsStream("mail.properties");

        try {
            mailProperties.load(in);

            /* Get the mail session object */
            Session session = Session.getDefaultInstance(mailProperties);

            /* Create a default MimeMessage object. */
            MimeMessage message = new MimeMessage(session);

            /* Set From: header field of the header. */
            message.setFrom(new InternetAddress(mailProperties
                                            .getProperty("mail.smtp.from")));

            /* Set To: header field of the header. */
            message.addRecipient(Message.RecipientType.TO,
                                                    new InternetAddress(to));

            /* Set Subject: header field */
            message.setSubject(subject);

            /* Actual HTML content */
            message.setContent(mailContent, "text/html");

            String strHost = mailProperties.getProperty("mail.smtp.host");
            String strUsername = mailProperties.getProperty("mail.smtp.user");
            String strPass = mailProperties.getProperty("mail.smtp.password");

            /* Send message */
            Transport transport = session.getTransport("smtp");
            transport.connect(strHost, strUsername, strPass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
