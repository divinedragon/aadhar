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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ignou.aadhar.constants.Constants;
import com.ignou.aadhar.domain.City;
import com.ignou.aadhar.domain.State;
import com.ignou.aadhar.editors.StateEditor;
import com.ignou.aadhar.service.CityService;
import com.ignou.aadhar.service.StateService;
import com.ignou.aadhar.service.impl.StateServiceImpl;
import com.ignou.aadhar.util.json.JsonRequestValidator;
import com.ignou.aadhar.util.json.JsonWrapper;

/**
 * Controller to handle the requests for managing cities in the database.
 * @author Deepak Shakya
 *
 */
@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private StateService stateService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        /* Let's bind the conversion mechanism for States so that they are
         * mapped correctly in the state attribute of City class.
         */
        dataBinder.registerCustomEditor(State.class,
              new StateEditor(((StateServiceImpl) stateService).getStateDao()));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {

        /* Lets create an empty city object to store the details */
        City newCity = new City();

        /* Lets fetch all the states in the database */
        List<State> states = stateService.list();

        /* Add the empty city and all the states in the model so that they are
         * available on the form.
         */
        model.addAttribute("newCity", newCity);
        model.addAttribute("states", states);

        /* Re-direct the user to the form */
        return "city/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCity(@Valid City newCity, BindingResult result)
                                    throws Exception {

        /* Check if there was any error while binding the state object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */
            return "city/create";
        }

        /* No error while binding the form data to java objects */
        /* Lets save the new city into the database */
        City dbCity = cityService.add(newCity);

        /* City added successfully. Lets re-direct to view page */
        return "redirect:/city/" + dbCity.getId();
    }

    @RequestMapping(value = "/createAjax", method = RequestMethod.POST)
    public JsonWrapper createAjaxCity(@Valid City newCity, String forAjax,
                                BindingResult result) throws Exception {

        String returnPage = createCity(newCity, result);

        JsonWrapper output = new JsonWrapper();

        /* Check if the record was created or not */
        if ("city/create".equals(returnPage)) {
            /* The user is not being re-directed. Set the error */
            output.put("result", "error");
            output.put("message", "Failed to create new city record.");
        } else {
            output.put("result", "success");
            output.put("message", "New city record created successfully.");
        }

        return output;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String viewById(@PathVariable Integer id, Model model) {

        /* Get the city record from the database */
        City city = cityService.read(id);

        /* Add this city to model to make it available for the view page */
        model.addAttribute("dbCity", city);

        return "city/view";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editForm(@RequestParam Integer id, Model model) {

        /* Read the city object from the database */
        City city = cityService.read(id);

        /* Lets fetch all the states in the database */
        List<State> states = stateService.list();

        /* Save the database city to the model along with the states */
        model.addAttribute("editCity", city);
        model.addAttribute("states", states);

        /* Re-direct the user to the form */
        return "city/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid City editCity, BindingResult result)
                                                     throws Exception {

        /* Check if there was any error while binding the state object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */

            return "city/edit?id=" + editCity.getId();
        }

        /* No error while binding the form data to java objects */
        /* Lets save the new city into the database */
        City dbCity = cityService.modify(editCity);

        /* City added successfully. Lets re-direct to view page */
        return "redirect:/city/" + dbCity.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Integer id, Model model) {

        /* Read the city object from the database */
        City city = cityService.read(id);

        /* We will delete the object only if we were able to fetch it from the
         * database.
         */
        if (city != null && city.getId() != null) {
            cityService.delete(city.getId());
        }

        /* Re-direct the user to the form */
        return "redirect:/city/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "city/grid";
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

        /* Also, if there is no search parameter provided, we will assign city
         * as the default search parameter.
         */
        if (searchField == null || searchField.isEmpty()) {
            searchField = "city";
        }

        /* Lets start preparing the JSON output */
        StringBuilder outputData = new StringBuilder();
        outputData.append("{ \"page\" : \"" + startIndex + "\", ");
        outputData.append("\"rp\" : \"" + recordCount + "\", ");
        outputData.append("\"sortname\" : \"" + sortField + "\", ");
        outputData.append("\"sortorder\" : \"" + sortOrder + "\", ");
        outputData.append("\"" + searchField + "\": \"" + searchValue + "\" }");

        JsonWrapper jsonData = getCityList(outputData.toString());

        /* Add the additional parameters required at the UI */
        jsonData.addParam("pageCount", page);
        if (!jsonData.containsKey("total")) {
            jsonData.addParam("total", 0);
        }

        return jsonData;
    }

    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    @ResponseBody
    private JsonWrapper getCityList(@PathVariable String filter) {

        JsonWrapper jsonData;

        String[] validParams = {"page","rp","sortname","sortorder","city","state"};

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
            if (paramMap.get("city") != null) {
                searchField = "city";
                searchValue = paramMap.get("city");
            }

            if (paramMap.get("state") != null) {
                searchField = "state";
                searchValue = paramMap.get("state");
            }

            /* Lets fetch the records from the database for the search condition
             * provided in the request.
             */
            List<Map<String, Object>> cities = cityService.getCities(
                                                    searchField, searchValue,
                                                    pageNumber, recordsPerPage,
                                                    sortField, sortOrder);

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
