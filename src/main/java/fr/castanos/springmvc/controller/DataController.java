package fr.castanos.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.castanos.common.domain.Person;
import fr.castanos.springmvc.form.PersonForm;
import fr.castanos.springmvc.service.DataService;

@RestController
@RequestMapping("/data")
public class DataController {
	@Autowired
	private DataService service;

	/*************************
	 * GET METHODS
	 ************************/

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
		return new ResponseEntity<Person>(this.service.getPersonById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Person>> getPersons() {

		return new ResponseEntity<List<Person>>(this.service.getAllPersons(), HttpStatus.OK);
	}

	/*************************
	 * POST METHODS
	 ************************/

	@RequestMapping(value = "/createForm", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> create(@ModelAttribute("personForm") PersonForm form) {
		try {
			Person person = this.service.create(form);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/createJSON", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> createFromJSON(@RequestBody PersonForm form) {
		try {
			Person person = this.service.create(form);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/createData", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> createFromJSON(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("birthDate") String birthDate, @RequestParam("gender") String gender) {
		try {
			Person person = this.service.create(firstName, lastName, birthDate, gender);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/*************************
	 * PUT METHODS
	 ************************/

	@RequestMapping(value = "/updateForm/{id}", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> update(@PathVariable("id") long id, @ModelAttribute("personForm") PersonForm form) {
		try {
			Person person = this.service.update(id, form);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateJSON/{id}", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> updateFromJSON(@PathVariable("id") long id, @RequestBody PersonForm form) {
		try {
			Person person = this.service.update(id, form);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/updateData/{id}", method = RequestMethod.POST)
	public ResponseEntity<? extends Object> updateFromJSON(@PathVariable("id") long id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("birthDate") String birthDate, @RequestParam("gender") String gender) {
		try {
			Person person = this.service.update(id, firstName, lastName, birthDate, gender);
			return new ResponseEntity<Person>(person, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/*************************
	 * DELETE METHODS
	 ************************/
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<? extends Object> delete(@PathVariable("id") long id) {
		try {
			boolean b = this.service.delete(id);
			return new ResponseEntity<Boolean>(b, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
