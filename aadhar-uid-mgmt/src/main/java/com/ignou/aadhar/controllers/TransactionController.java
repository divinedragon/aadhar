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
package com.ignou.aadhar.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ignou.aadhar.constants.Constants;
import com.ignou.aadhar.constants.TransactionStatus;
import com.ignou.aadhar.domain.Citizen;
import com.ignou.aadhar.domain.ServiceProvider;
import com.ignou.aadhar.domain.Transaction;
import com.ignou.aadhar.domain.TransactionToken;
import com.ignou.aadhar.service.CitizenService;
import com.ignou.aadhar.service.ServiceProviderService;
import com.ignou.aadhar.service.TransactionService;
import com.ignou.aadhar.service.TransactionTokenService;
import com.ignou.aadhar.util.EmailSender;
import com.ignou.aadhar.util.MD5Generator;
import com.ignou.aadhar.util.TokenGenerator;
import com.ignou.aadhar.util.UIDGenerator;
import com.ignou.aadhar.util.json.JsonRequestValidator;
import com.ignou.aadhar.util.json.JsonWrapper;

/**
 * Controller to handle the requests for managing transactions as requested by
 * the Service Provider.
 * @author Deepak Shakya
 *
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private TransactionTokenService transactionTokenService;

    @Autowired
    private EmailSender mailSender;

    //@Autowired
    //private HttpServletRequest request;

    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public String getDemoForm(Model model) {
        return "transaction/demo";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
    public String getTransactionForm(
            @RequestParam (value = "clientTxnId") String clientTxnId,
            @RequestParam (value = "clientUsername") String clientUsername,
            @RequestParam (value = "clientPassword") String clientPassword,
            @RequestParam (value = "uid") String uid,
            @RequestParam (value = "amount") Double amount, Model model) {

        /* Check if Client Transaction ID is provided or not */
        if (clientTxnId == null || clientTxnId.isEmpty()) {
            model.addAttribute("msg", "No Transaction ID provided");
            return "common/error";
        }

        /* Check if the Client Username is provided or not */
        if (clientUsername == null || clientUsername.isEmpty()) {
            model.addAttribute("msg", "Service Provider username is mandatory."
                                        + " Not provided");
            return "common/error";
        }

        /* Check if the Client password is provided or not */
        if (clientPassword == null || clientPassword.isEmpty()) {
            model.addAttribute("msg", "Service Provider password is mandatory."
                                        + " Not provided");
            return "common/error";
        }

        /* Check if the UID is provided or not */
        if (uid == null || uid.isEmpty() || uid.trim().length() != 15
                || (!UIDGenerator.validateUID(uid))) {
            model.addAttribute("msg", "Invalid or empty UID provided");
            return "common/error";
        }

        /* Check if the amount value provided is corrent or not */
        if (amount == null || amount.doubleValue() == 0.0) {
            model.addAttribute("msg", "Invalid transaction amount");
            return "common/error";
        }

        /* Basic validation done. We will now fetch the Service Provider
         * details from the database.
         */
        ServiceProvider client = serviceProviderService
                                          .getByNameAndPassword(clientUsername,
                                                                clientPassword);

        /* Check if any Service Provider was returned or not */
        if (client == null) {
            model.addAttribute("msg", "Service Provider authentication failed");
            return "common/error";
        }

        //TODO: Validating that the originating URL and that saved in the
        //System.out.println("--> Request Originitated From : " + request.getHeader("Referer"));

        /* Lets now fetch the UID for which transaction need to be performed */
        Citizen citizen = citizenService.getByUID(uid);

        if (citizen == null) {
            model.addAttribute("msg", "UID provided does not exist.");
            return "common/error";
        }

        /* Authentication Done for Service Provider. Create the Transaction
         * object.
         */
        Transaction transaction = new Transaction();
        transaction.setCitizen(citizen);
        transaction.setServiceProvider(client);
        transaction.setAmount(amount);
        transaction.setSpTransactionId(clientTxnId);
        transaction.setCreated(new Date());
        transaction.setStatus(TransactionStatus.PENDING.getCode());

        /* Lets now save the Transaction in the database */
        Transaction dbTransaction = transactionService.add(transaction);

        /* Lets now create a TransactionToken and email the token on the
         * citizen's email id.
         */
        TransactionToken token = new TransactionToken();
        token.setCitizen(dbTransaction.getCitizen());
        token.setTransaction(dbTransaction);
        token.setToken(TokenGenerator.getToken());

        TransactionToken dbToken = transactionTokenService.add(token);

        /* Lets now send this token on the email */
        mailSender.send("justdpk@gmail.com", "Authentication Token",
                "You authentication token is : " + dbToken.getToken());

        /* Redirect the request to UID authentication page where the Citizen
         * will provide his/her credentials to completely validate this
         * transaction request from the Service Provider.
         */
        model.addAttribute("transaction", dbTransaction);

        return "transaction/uid_auth";
    }

    @RequestMapping(value = "/bankaction", method = RequestMethod.GET)
    public String getBankForm(@Valid Transaction transaction,
            @RequestParam (value = "txtPassword") String uidPassword,
            @RequestParam (value = "txtToken") String strToken,
                          BindingResult result, Model model) throws Exception {

        /* Check if there was any error while binding the transaction object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */
            model.addAttribute("msg", "Unexpected error occurred while "
                        + "processing your request. Please try again later");
            return "common/error";
        }

        /* Lets refetch the Transaction object */
        Transaction dbTransaction = transactionService.read(transaction.getId());

        /* Lets now validate the password provided by the citizen */
        /* Generate the MD5 Value of the password provided by the citizen */
        String md5Password = MD5Generator.encode(uidPassword);
        if (!dbTransaction.getCitizen().getPassword().equals(md5Password)) {
            /* Password did not match */
            model.addAttribute("error", "Incorrect password provided");
            model.addAttribute("transaction", dbTransaction);
            return "transaction/uid_auth";
        }

        /* Fetch the Token record for the transaction and uid */
        TransactionToken token = transactionTokenService
                                    .getTokenForTransactionIdAndUID(
                                           dbTransaction,
                                           dbTransaction.getCitizen());
        /* Check if the correct token was fetched */
        if (token == null || !token.getToken().equals(strToken)) {
            /* Token did not match */
            model.addAttribute("error", "Incorrect Token provided");
            model.addAttribute("transaction", dbTransaction);
            return "transaction/uid_auth";
        }

        /* Password is valid. Make the call to the Bank Service */
        model.addAttribute("amount", dbTransaction.getAmount());
        model.addAttribute("txnId", dbTransaction.getId());
        model.addAttribute("serviceProvider", dbTransaction.getServiceProvider());
        model.addAttribute("uid", dbTransaction.getCitizen().getUid());
        model.addAttribute("citizenAcccountNumber",
                dbTransaction.getCitizen().getAccount().getAccountNumber());

        return "transaction/bankservice";
    }

    @RequestMapping(value = "/bankresponse", method = RequestMethod.GET)
    public String getBankForm(
            @RequestParam (value = "txnId") Integer txnId,
            @RequestParam (value = "bankTxnId") String bankTxnId,
            @RequestParam (value = "status") String txnStatus,
            @RequestParam (value = "bankPassword") String bankPassword,
                                        Model model) throws Exception {

        /* Lets re-fetch the Transaction object */
        Transaction dbTransaction = transactionService.read(txnId);

        /* Save the status in the database */
        if (TransactionStatus.SUCCESS.getCode().equals(txnStatus)) {
            dbTransaction.setStatus(TransactionStatus.SUCCESS.getCode());
        } else {
            dbTransaction.setStatus(TransactionStatus.FAILED.getCode());
        }

        /* Save the Bank Transaction ID */
        dbTransaction.setBankTransactionId(bankTxnId);

        /* Commit changes to Database record */
        transactionService.modify(dbTransaction);

        model.addAttribute("status", txnStatus);

        return "transaction/status";
    }

    
    
    
    
    
    
    
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "transaction/grid";
    }

    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    public JsonWrapper listJson(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rp") Integer recordCount,
            @RequestParam(value = "sortname") String sortField,
            @RequestParam(value = "sortorder") String sortOrder,
            @RequestParam(value = "query") String searchValue,
            @RequestParam(value = "qtype") String searchField) {

        /* If there is no recordCount value, lets set from our side */
        if (recordCount == null || recordCount == 0) {
            recordCount = Constants.MAX_RECORDS_PER_PAGE;
        }

        /* Also, set the page index to start page if not provided. Otherwise, we
         * will calculate the corresponding record offset.
         */
        int startIndex = 0;
        if (page == null || page == 0) {
            page = Constants.START_PAGE;
        } else {
            startIndex = (page - 1) * recordCount;
        }

        /* Also, if there is no search parameter provided, we will assign uid
         * as the default search parameter.
         */
        if (searchField == null || searchField.isEmpty()) {
            searchField = "uid";
        }

        /* Lets start preparing the JSON output */
        StringBuilder outputData = new StringBuilder();
        outputData.append("{ \"page\" : \"" + startIndex + "\", ");
        outputData.append("\"rp\" : \"" + recordCount + "\", ");
        outputData.append("\"sortname\" : \"" + sortField + "\", ");
        outputData.append("\"sortorder\" : \"" + sortOrder + "\", ");
        outputData.append("\"" + searchField + "\": \"" + searchValue + "\" }");

        JsonWrapper jsonData = getTransactionList(outputData.toString());

        /* Add the additional parameters required at the UI */
        jsonData.addParam("pageCount", page);
        if (!jsonData.containsKey("total")) {
            jsonData.addParam("total", 0);
        }

        return jsonData;
    }

    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    @ResponseBody
    private JsonWrapper getTransactionList(@PathVariable String filter) {

        JsonWrapper jsonData;

        String[] validParams = { "page", "rp", "sortname", "sortorder", "uid",
                                 "name", "serviceprovider", "clientTxnId",
                                 "bankTxnId", "status" };

        try {
            Map<String, String> paramMap = JsonRequestValidator
                                                .validateRequestParams(filter,
                                                        validParams);

            String searchField = null;
            String searchValue = null;
            String sortField = paramMap.get("sortname");
            String sortOrder = paramMap.get("sortorder");

            /* Get the recordsPerPage value */
            Integer recordsPerPage = null;
            if (paramMap.get("rp") != null) {
                recordsPerPage = new Integer(paramMap.get("rp"));
            }

            Integer pageNumber = null;
            if (paramMap.get("page") != null) {
                pageNumber = new Integer(paramMap.get("page"));
            }

            /* Determine which field is being searched */
            if (paramMap.get("uid") != null) {
                searchField = "uid";
                searchValue = paramMap.get("uid");

            } else if (paramMap.get("serviceprovider") != null) {
                searchField = "serviceprovider";
                searchValue = paramMap.get("serviceprovider");

            } else if (paramMap.get("clientTxnId") != null) {
                searchField = "clientTxnId";
                searchValue = paramMap.get("clientTxnId");

            } else if (paramMap.get("bankTxnId") != null) {
                searchField = "bankTxnId";
                searchValue = paramMap.get("bankTxnId");

            } else if (paramMap.get("status") != null) {
                searchField = "status";
                searchValue = paramMap.get("status");
            }

            /* Lets fetch the records from the database for the search condition
             * provided in the request.
             */
            List<Map<String, Object>> cities = transactionService
                                                .getTransactions(searchField,
                                                    searchValue, pageNumber,
                                                    recordsPerPage, sortField,
                                                    sortOrder);

            /* Check if any records were fetched successfully */
            if (cities != null && !cities.isEmpty()) {

                /* Convert the city data as Json Wrapper instance */
                jsonData = new JsonWrapper(cities, "Success");
                jsonData.put("total", cities.get(0).get("totalCount"));
            } else {

                /* Because no records were fetched, we will return empty list */
                jsonData = new JsonWrapper(new ArrayList(), "Failure",
                                    "No records found for search parameters");
            }

            jsonData.put("page", pageNumber);
        } catch (Exception e) {
            jsonData = new JsonWrapper("Failure", e.getMessage());
            e.printStackTrace();
        }

        return jsonData;
    }
    
    
    
    
    
    
    
    
    
    
    
}
