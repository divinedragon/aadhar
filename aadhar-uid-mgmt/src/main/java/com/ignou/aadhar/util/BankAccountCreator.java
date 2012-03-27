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

import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import com.ignou.aadhar.util.json.JsonMarshaller;
import com.ignou.aadhar.util.json.JsonWrapper;

/**
 * Utility class to create bank account for the UID during his/her registration
 * by web service call to the bank's website.
 * @author Deepak Shakya
 *
 */
public class BankAccountCreator {

    /**
     * Bank Web Service URL.
     */
    private static final String webServiceUrl = "http://localhost:8080/DummyBankWebService/bankService";

    /**
     * Username for the Web Service.
     */
    private static final String webServiceUsername = "dragon";

    /**
     * Password for the Web Service.
     */
    private static final String webServicePassword = "divinedragon";

    /**
     * Makes the Create Account Web Service Call to the Bank Web Service and
     * fetches the Account ID created at the bank.
     * @param uid UID for which Account ID needs to be created.
     * @return Map containing the STATUS and ID(Account ID) created.
     */
    public JsonWrapper createAccount(String uid) {

        JsonWrapper content = null;

        try {
            /* Create the Connection for Web Service Call */
            SOAPConnectionFactory connfactory = SOAPConnectionFactory.newInstance();
            SOAPConnection conn = connfactory.createConnection();

            /* Create the message which will be sent during web service call */
            MessageFactory msgFactory = MessageFactory.newInstance();
            SOAPMessage message = msgFactory.createMessage();

            /* Add message headers */
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();

            envelope.addAttribute(new QName("xmlns:ws"), "http://dummybank.ignou.com/");
            SOAPBody body = envelope.getBody();

            /* Add message details */
            SOAPElement bodyElement = body.addChildElement(envelope.createName("ws:createAccount", "", ""));
            bodyElement.addChildElement("arg0").addTextNode(webServiceUsername);
            bodyElement.addChildElement("arg1").addTextNode(webServicePassword);
            bodyElement.addChildElement("arg2").addTextNode(uid);
            message.saveChanges();

            /* Call the web service and send the message and wait for reply */
            SOAPMessage reply = conn.call(message, webServiceUrl);

            /* Transform the Response to save the response content in String */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            Source sourceContent = reply.getSOAPPart().getContent();

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(sourceContent, result);

            String strResponse = writer.toString();

            /* Apply the pattern on the response string to fetch the section
             * of text which has the STATUS and ACCOUNTID values.
             */
            Pattern pattern = Pattern.compile("<return>(.*?)<\\/return>");
            Matcher matcher = pattern.matcher(strResponse);

            /* Check if we found the Response or not */
            if (matcher.find()) {
                /* We found the response. Extract the content and return back
                 * the Account ID.
                 */
                String json = matcher.group(1);

                content = (JsonWrapper) JsonMarshaller.parse(json, JsonWrapper.class);
            } else {
                /* Account ID was not found. Send back the FAILURE code */
                content = new JsonWrapper();
                content.addParam("status", "FAILURE");
            }
        } catch (Exception e) {
            /* Error occurred while making web service call.
             * Send back the FAILURE code.
             */
            e.printStackTrace();
            content = new JsonWrapper();
            content.addParam("status", "FAILURE");
        }

        return content;
    }
}
