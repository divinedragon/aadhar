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

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ignou.aadhar.domain.City;
import com.ignou.aadhar.domain.State;
import com.ignou.aadhar.editors.StateEditor;
import com.ignou.aadhar.service.CityService;
import com.ignou.aadhar.service.StateService;
import com.ignou.aadhar.service.impl.StateServiceImpl;

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

        cityService.delete(id);

        /* Re-direct the user to the form */
        return "redirect:/city/create";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {

        return "city/grid";
    }
}
