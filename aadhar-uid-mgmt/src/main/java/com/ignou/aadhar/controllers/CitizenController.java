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
import com.ignou.aadhar.domain.Citizen;
import com.ignou.aadhar.service.CitizenService;
import com.ignou.aadhar.util.json.JsonRequestValidator;
import com.ignou.aadhar.util.json.JsonWrapper;

/**
 * Controller to handle the requests for managing Citizens in the database.
 * @author Deepak Shakya
 *
 */
@Controller
@RequestMapping("/citizen")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {

        /* Lets create an empty citizen object to store the details */
        Citizen newCitizen = new Citizen();

        /* Add the empty citizen in the model so that it is
         * available on the form.
         */
        model.addAttribute("newCitizen", newCitizen);
        model.addAttribute("genders", citizenService.getGenders());

        /* Re-direct the user to the form */
        return "citizen/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCitizen(@Valid Citizen newCitizen,
                                        BindingResult result) throws Exception {

        /* Check if there was any error while binding the citizen object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */

            return "citizen/create";
        }

        /* No error while binding the form data to java objects */
        /* Lets save the new Citizen into the database */
        Citizen dbCitizen = citizenService.add(newCitizen);

        /* Citizen added successfully. Lets re-direct to view page */
        return "redirect:/citizen/" + dbCitizen.getId();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String viewById(@PathVariable Integer id, Model model) {

        /* Get the Citizen record from the database */
        Citizen citizen = citizenService.read(id);

        /* Check if the record was fetched successfully */
        if (citizen == null || citizen.getId() == null) {
            /* The record for the corresponding id does not exist */
            model.addAttribute("msg", "No Citizen details exist for ID"
                                        + " - " + id);
            return "common/error";
        }

        /* Add this citizen to model to make it available for the view page */
        model.addAttribute("dbServiceProvider", citizen);

        return "citizen/view";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editForm(@RequestParam Integer id, Model model) {

        /* Read the Citizen object from the database */
        Citizen citizen = citizenService.read(id);

        /* Check if the record was fetched successfully */
        if (citizen == null || citizen.getId() == null) {
            /* The record for the corresponding id does not exist */
            model.addAttribute("msg", "No Citizen details exist for ID"
                                        + " - " + id);
            return "common/error";
        }

        /* Save the database citizen object to the model */
        model.addAttribute("editCitizen", citizen);

        /* Re-direct the user to the form */
        return "citizen/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid Citizen editCitizen,
                                    BindingResult result) throws Exception {

        /* Check if there was any error while binding the Citizen object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */
            return "citizen/edit?id=" + editCitizen.getId();
        }

        /* No error while binding the form data to java objects */
        /* Lets save the Citizen into the database */
        Citizen dbCitizen = citizenService.modify(editCitizen);

        /* Citizen added successfully. Lets re-direct to view page */
        return "redirect:/citizen/" + dbCitizen.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Integer id, Model model) {

        /* Read the Citizen object from the database */
        Citizen citizen = citizenService.read(id);

        /* We will delete the object only if we were able to fetch it from the
         * database.
         */
        if (citizen != null && citizen.getId() != null) {
            citizenService.delete(citizen.getId());
        }

        /* Re-direct the user to the form */
        return "redirect:/citizen/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "citizen/grid";
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

        /* Also, if there is no search parameter provided, we will assign
         * name as the default search parameter.
         */
        if (searchField == null || searchField.isEmpty()) {
            searchField = "name";
        }

        /* Lets start preparing the JSON output */
        StringBuilder outputData = new StringBuilder();
        outputData.append("{ \"page\" : \"" + startIndex + "\", ");
        outputData.append("\"rp\" : \"" + recordCount + "\", ");
        outputData.append("\"sortname\" : \"" + sortField + "\", ");
        outputData.append("\"sortorder\" : \"" + sortOrder + "\", ");
        outputData.append("\"" + searchField + "\": \"" + searchValue + "\" }");

        JsonWrapper jsonData = getCitizenList(outputData.toString());

        /* Add the additional parameters required at the UI */
        jsonData.addParam("pageCount", page);
        if (!jsonData.containsKey("total")) {
            jsonData.addParam("total", 0);
        }

        return jsonData;
    }

    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    @ResponseBody
    private JsonWrapper getCitizenList(@PathVariable String filter) {

        JsonWrapper jsonData;

        String[] validParams = {"page", "rp", "sortname", "sortorder", "name",
                "requestUrl", "responseUrl", "accountNumber", "bankIFSCCode"};

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
            if (paramMap.get("name") != null) {
                searchField = "name";
                searchValue = paramMap.get("name");

            } else if (paramMap.get("requestUrl") != null) {
                searchField = "requestUrl";
                searchValue = paramMap.get("requestUrl");

            } else if (paramMap.get("responseUrl") != null) {
                searchField = "responseUrl";
                searchValue = paramMap.get("responseUrl");

            } else if (paramMap.get("accountNumber") != null) {
                searchField = "accountNumber";
                searchValue = paramMap.get("accountNumber");

            } else if (paramMap.get("bankIFSCCode") != null) {
                searchField = "bankIFSCCode";
                searchValue = paramMap.get("bankIFSCode");
            }

            /* Lets fetch the records from the database for the search condition
             * provided in the request.
             */
            List<Map<String, Object>> citizens = citizenService
                               .getCitizens(searchField, searchValue,
                                       pageNumber, recordsPerPage, sortField,
                                       sortOrder);

            /* Check if any records were fetched successfully */
            if (citizens != null && !citizens.isEmpty()) {

                /* Convert the serviceProvider data as Json Wrapper instance */
                jsonData = new JsonWrapper(citizens, "Success");
                jsonData.put("total", citizens.get(0).get("totalCount"));
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
