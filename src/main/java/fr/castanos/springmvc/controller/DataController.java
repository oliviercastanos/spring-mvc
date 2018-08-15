package fr.castanos.springmvc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.castanos.common.domain.Gender;
import fr.castanos.common.domain.Person;

@RestController
@RequestMapping("/data")
public class DataController {

	/********************** GET METHODS **********************/
	@RequestMapping("/{id}")
	public @ResponseBody ResponseEntity<Person> getPersonById(@PathVariable("id") long id) {
		return new ResponseEntity<Person>(new Person(id, "firstName_" + id, "lastName_" + id, new Date(), Gender.UNDEFINED), HttpStatus.OK);
	}

	@RequestMapping("")
	public @ResponseBody ResponseEntity<List<Person>> getPersons() {
		List<Person> list = new ArrayList<Person>();
		for (int id = 0; id < 10; id++) {
			list.add(new Person(id, "firstName_" + id, "lastName_" + id, new Date(), Gender.UNDEFINED));
		}
		return new ResponseEntity<List<Person>>(list, HttpStatus.OK);
	}
	/********************** POST METHODS **********************/
	/********************** PUT METHODS **********************/
	/********************** DELETE METHODS **********************/


}
