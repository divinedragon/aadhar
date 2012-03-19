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
import com.ignou.aadhar.domain.District;
import com.ignou.aadhar.domain.State;
import com.ignou.aadhar.editors.StateEditor;
import com.ignou.aadhar.service.DistrictService;
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
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @Autowired
    private StateService stateService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        /* Let's bind the conversion mechanism for States so that they are
         * mapped correctly in the state attribute of District class.
         */
        dataBinder.registerCustomEditor(State.class,
              new StateEditor(((StateServiceImpl) stateService).getStateDao()));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {

        /* Lets create an empty district object to store the details */
        District newDistrict = new District();

        /* Lets fetch all the states in the database */
        List<State> states = stateService.list();

        /* Add the empty district and all the states in the model so that they
         * are available on the form.
         */
        model.addAttribute("newDistrict", newDistrict);
        model.addAttribute("states", states);

        /* Re-direct the user to the form */
        return "district/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createDistrict(@Valid District newDistrict,
                                        BindingResult result) throws Exception {

        /* Check if there was any error while binding the state object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */

            return "district/create";
        }

        /* No error while binding the form data to java objects */
        /* Lets save the new district into the database */
        District dbDistrict = districtService.add(newDistrict);

        /* District added successfully. Lets re-direct to view page */
        return "redirect:/district/" + dbDistrict.getId();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String viewById(@PathVariable Integer id, Model model) {

        /* Get the district record from the database */
        District district = districtService.read(id);

        /* Add this district to model to make it available for the view page */
        model.addAttribute("dbDistrict", district);

        return "district/view";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editForm(@RequestParam Integer id, Model model) {

        /* Read the district object from the database */
        District district = districtService.read(id);

        /* Lets fetch all the states in the database */
        List<State> states = stateService.list();

        /* Save the database district to the model along with the states */
        model.addAttribute("editDistrict", district);
        model.addAttribute("states", states);

        /* Re-direct the user to the form */
        return "district/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid District editDistrict, BindingResult result)
                                                     throws Exception {

        /* Check if there was any error while binding the state object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */

            return "district/edit?id=" + editDistrict.getId();
        }

        /* No error while binding the form data to java objects */
        /* Lets save the new district into the database */
        District dbDistrict = districtService.modify(editDistrict);

        /* District added successfully. Lets re-direct to view page */
        return "redirect:/district/" + dbDistrict.getId();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Integer id, Model model) {

        /* Read the district object from the database */
        District district = districtService.read(id);

        /* We will delete the object only if we were able to fetch it from the
         * database.
         */
        if (district != null && district.getId() != null) {
            districtService.delete(district.getId());
        }

        /* Re-direct the user to the form */
        return "redirect:/district/create";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "district/grid";
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
         * district as the default search parameter.
         */
        if (searchField == null || searchField.isEmpty()) {
            searchField = "district";
        }

        /* Lets start preparing the JSON output */
        StringBuilder outputData = new StringBuilder();
        outputData.append("{ \"page\" : \"" + startIndex + "\", ");
        outputData.append("\"rp\" : \"" + recordCount + "\", ");
        outputData.append("\"sortname\" : \"" + sortField + "\", ");
        outputData.append("\"sortorder\" : \"" + sortOrder + "\", ");
        outputData.append("\"" + searchField + "\": \"" + searchValue + "\" }");

        JsonWrapper jsonData = getDistrictList(outputData.toString());

        /* Add the additional parameters required at the UI */
        jsonData.addParam("pageCount", page);
        if (!jsonData.containsKey("total")) {
            jsonData.addParam("total", 0);
        }

        return jsonData;
    }

    @RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
    @ResponseBody
    private JsonWrapper getDistrictList(@PathVariable String filter) {

        JsonWrapper jsonData;

        String[] validParams = {"page", "rp", "sortname", "sortorder",
                                    "district", "state"};

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
            if (paramMap.get("district") != null) {
                searchField = "district";
                searchValue = paramMap.get("district");
            }

            if (paramMap.get("state") != null) {
                searchField = "state";
                searchValue = paramMap.get("state");
            }

            /* Lets fetch the records from the database for the search condition
             * provided in the request.
             */
            List<Map<String, Object>> cities = districtService.getDistricts(
                                                    searchField, searchValue,
                                                    pageNumber, recordsPerPage,
                                                    sortField, sortOrder);

            /* Check if any records were fetched successfully */
            if (cities != null && !cities.isEmpty()) {

                /* Convert the district data as Json Wrapper instance */
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
