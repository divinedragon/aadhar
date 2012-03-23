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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ignou.aadhar.constants.Constants;
import com.ignou.aadhar.constants.UIDStates;
import com.ignou.aadhar.constants.UIDTypes;
import com.ignou.aadhar.domain.Address;
import com.ignou.aadhar.domain.Bank;
import com.ignou.aadhar.domain.Citizen;
import com.ignou.aadhar.domain.City;
import com.ignou.aadhar.domain.District;
import com.ignou.aadhar.domain.State;
import com.ignou.aadhar.editors.BankEditor;
import com.ignou.aadhar.editors.CityEditor;
import com.ignou.aadhar.editors.DistrictEditor;
import com.ignou.aadhar.editors.StateEditor;
import com.ignou.aadhar.service.AddressService;
import com.ignou.aadhar.service.BankService;
import com.ignou.aadhar.service.CitizenService;
import com.ignou.aadhar.service.CityService;
import com.ignou.aadhar.service.DistrictService;
import com.ignou.aadhar.service.StateService;
import com.ignou.aadhar.service.impl.BankServiceImpl;
import com.ignou.aadhar.service.impl.CityServiceImpl;
import com.ignou.aadhar.service.impl.DistrictServiceImpl;
import com.ignou.aadhar.service.impl.StateServiceImpl;
import com.ignou.aadhar.util.UIDGenerator;
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

    @Autowired
    private BankService bankService;

    @Autowired
    private StateService stateService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        /* Let's bind the conversion mechanism for Banks so that they are
         * mapped correctly in the bank attribute.
         */
        dataBinder.registerCustomEditor(Bank.class,
              new BankEditor(((BankServiceImpl) bankService).getBankDao()));

        /* Also, we need to convert the dateOfBirth in string format to Date
         * object.
         */
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        dataBinder.registerCustomEditor(Date.class, editor);

        /* Add the State converter for mapping state objects */
        dataBinder.registerCustomEditor(State.class,
              new StateEditor(((StateServiceImpl) stateService).getStateDao()));

        /* Add the District converter for mapping district objects */
        dataBinder.registerCustomEditor(District.class,
              new DistrictEditor(((DistrictServiceImpl) districtService)
                                                            .getDistrictDao()));

        /* Add the City converter for mapping city objects */
        dataBinder.registerCustomEditor(City.class,
              new CityEditor(((CityServiceImpl) cityService).getCityDao()));
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {

        /* Lets create an empty citizen object to store the details */
        Citizen newCitizen = new Citizen();

        /* Add the empty citizen in the model so that it is
         * available on the form.
         */
        model.addAttribute("newCitizen", newCitizen);
        model.addAttribute("genders", citizenService.getGenders());
        model.addAttribute("banks", bankService.list());
        model.addAttribute("accessRoles", citizenService.getAccessRoles());
        model.addAttribute("states", stateService.list());

        /* Re-direct the user to the form */
        return "citizen/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createCitizen(@Valid Citizen newCitizen,
                           BindingResult result, Model model) throws Exception {

        /* Check if there was any error while binding the citizen object */
        if (result.hasErrors()) {

            /* There was some error while binding the form data to java objects.
             * Lets re-direct the user back to the form.
             */
            for(FieldError error :result.getFieldErrors()) {
                System.out.println("--> " + error.getField() + " - " + error.getDefaultMessage());
            }

            model.addAttribute("newCitizen", newCitizen);
            model.addAttribute("genders", citizenService.getGenders());
            model.addAttribute("banks", bankService.list());
            model.addAttribute("accessRoles", citizenService.getAccessRoles());
            model.addAttribute("states", stateService.list());

            return "citizen/create";
        }

        /* Let's generate the UID for this citizen */
        newCitizen.setUid(UIDGenerator.generateUID(UIDTypes.CITIZEN));

        /* Also, set the created date for now's date */
        newCitizen.setCreated(new Date());

        /* During registration, the status would be pending */
        newCitizen.setStatus(UIDStates.PENDING.getCode());

        /* No error while binding the form data to java objects */
        /* Lets save the new Citizen into the database */
        //Citizen dbCitizen = citizenService.add(newCitizen);
        Citizen dbCitizen = createCitizenDetails(newCitizen);

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
        model.addAttribute("genders", citizenService.getGenders());
        model.addAttribute("accessRoles", citizenService.getAccessRoles());

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

    private Citizen createCitizenDetails(Citizen citizen) {
        /* Lets check if both Local Address and Permanent Address values are
         * same. Then will have to create just one record and the reference
         * would be set for both Local and Permanent addresses.
         */
        Address dbLocalAddress = null;
        Address dbPermanentAddress = null;
        if (isAddressSame(citizen.getLocalAddress(),
                citizen.getPermanentAddress())) {
            /* Both Address are Same. Lets create one of them and reference
             * local and permanent address of citizen object to the same
             * address instance.
             */
            Address dbAddress = addressService.add(citizen.getLocalAddress());
            dbPermanentAddress = dbLocalAddress = dbAddress;

        } else {
            /* Local and Permanent Address are different. Insert each of those
             * set the individual references.
             */
            dbLocalAddress = addressService.add(citizen.getLocalAddress());
            dbPermanentAddress = addressService.add(citizen.getPermanentAddress());
        }

        citizen.setLocalAddress(dbLocalAddress);
        citizen.setPermanentAddress(dbPermanentAddress);
        return null;
    }

    private boolean isAddressSame(Address local, Address permanent) {
        
        return (local.getCareOf().equals(permanent.getCareOf())
                && local.getAddressLine1().equals(permanent.getAddressLine1())
                && local.getAddressLine2().equals(permanent.getAddressLine2())
                && local.getAddressLine3().equals(permanent.getAddressLine3())
                && local.getArea().equals(permanent.getArea()));
        
    }
}
