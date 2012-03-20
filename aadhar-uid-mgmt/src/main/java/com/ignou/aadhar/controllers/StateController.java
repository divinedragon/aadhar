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
import com.ignou.aadhar.domain.State;
import com.ignou.aadhar.service.StateService;
import com.ignou.aadhar.util.json.JsonRequestValidator;
import com.ignou.aadhar.util.json.JsonWrapper;

/**
 * Controller to handle the requests for managing cities in the database.
 * @author Deepak Shakya
 *
 */
@Controller
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateService stateService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {

        /* Lets create an empty state object to store the details */
        State newState = new State();

        /* Add the empty state and all the states in the model so that they are
         * available on the form.
         */
        model.addAttribute("newState", newState);

        /* Re-direct the user to the form */
        return "state/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createState(@Valid State newState, BindingResult result)
                                    throws Exception {

        /* Check if there was any error while binding the state object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */

            return "state/create";
        }

        /* No error while binding the form data to java objects */
        /* Lets save the new state into the database */
        State dbState = stateService.add(newState);

        /* State added successfully. Lets re-direct to view page */
        return "redirect:/state/" + dbState.getId();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String viewById(@PathVariable Integer id, Model model) {

        /* Get the state record from the database */
        State state = stateService.read(id);

        /* Check if the record was fetched successfully */
        if (state == null || state.getId() == null) {
            /* The record for the corresponding id does not exist */
            model.addAttribute("msg", "No State details exist for ID - " + id);
            return "common/error";
        }

        /* Add this state to model to make it available for the view page */
        model.addAttribute("dbState", state);

        return "state/view";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editForm(@RequestParam Integer id, Model model) {

        /* Read the state object from the database */
        State state = stateService.read(id);

        /* Check if the record was fetched successfully */
        if (state == null || state.getId() == null) {
            /* The record for the corresponding id does not exist */
            model.addAttribute("msg", "No State details exist for ID - " + id);
            return "common/error";
        }

        /* Save the database state to the model along with the states */
        model.addAttribute("editState", state);

        /* Re-direct the user to the form */
        return "state/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid State editState, BindingResult result)
                                                     throws Exception {

        /* Check if there was any error while binding the state object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */
            return "state/edit?id=" + editState.getId();
        }

        /* No error while binding the form data to java objects */
        /* Lets save the new state into the database */
        State dbState = stateService.modify(editState);

        /* State added successfully. Lets re-direct to view page */
        return "redirect:/state/" + dbState.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Integer id, Model model) {

        /* Read the state object from the database */
        State state = stateService.read(id);

        /* We will delete the object only if we were able to fetch it from the
         * database.
         */
        if (state != null && state.getId() != null) {
            stateService.delete(state.getId());
        }

        /* Re-direct the user to the form */
        return "redirect:/state/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "state/grid";
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

        /* Also, if there is no search parameter provided, we will assign state
         * as the default search parameter.
         */
        if (searchField == null || searchField.isEmpty()) {
            searchField = "state";
        }

        /* Lets start preparing the JSON output */
        StringBuilder outputData = new StringBuilder();
        outputData.append("{ \"page\" : \"" + startIndex + "\", ");
        outputData.append("\"rp\" : \"" + recordCount + "\", ");
        outputData.append("\"sortname\" : \"" + sortField + "\", ");
        outputData.append("\"sortorder\" : \"" + sortOrder + "\", ");
        outputData.append("\"" + searchField + "\": \"" + searchValue + "\" }");

        JsonWrapper jsonData = getStateList(outputData.toString());

        /* Add the additional parameters required at the UI */
        jsonData.addParam("pageCount", page);
        if (!jsonData.containsKey("total")) {
            jsonData.addParam("total", 0);
        }

        return jsonData;
    }

    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    @ResponseBody
    private JsonWrapper getStateList(@PathVariable String filter) {

        JsonWrapper jsonData;

        String[] validParams = {"page", "rp", "sortname", "sortorder", "state"};

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
            if (paramMap.get("state") != null) {
                searchField = "state";
                searchValue = paramMap.get("state");
            }

            /* Lets fetch the records from the database for the search condition
             * provided in the request.
             */
            List<Map<String, Object>> states = stateService.getStates(
                                                    searchField, searchValue,
                                                    pageNumber, recordsPerPage,
                                                    sortField, sortOrder);

            /* Check if any records were fetched successfully */
            if (states != null && !states.isEmpty()) {

                /* Convert the state data as Json Wrapper instance */
                jsonData = new JsonWrapper(states, "Success");
                jsonData.put("total", states.get(0).get("totalCount"));
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
