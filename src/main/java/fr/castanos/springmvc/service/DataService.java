package fr.castanos.springmvc.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.castanos.common.domain.Gender;
import fr.castanos.common.domain.Person;
import fr.castanos.springmvc.form.PersonForm;
import fr.castanos.springmvc.form.mapper.PersonMapper;

@Service
public class DataService {
	@Autowired
	private PersonMapper mapper;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public Person create(PersonForm form) {
		Person person = this.mapper.toPerson(form);
		/*********** SAVE ***/
		person.setId(System.currentTimeMillis());
		return person;
		//

	}

	public Person create(String firstName, String lastName, String birthDate, String gender) {
		Person person = this.mapper.toPerson(firstName, lastName, birthDate, gender);
		/*********** SAVE ***/
		person.setId(System.currentTimeMillis());
		return person;
		//

	}

	/*************************************************************************************/
	public Person update(long id, PersonForm form) {
		/*********** SAVE ***/
		Person person = this.getPersonById(id);
		try {
			person.setBirthDate(formatter.parse(form.getBirthDate()));

		} catch (Exception e) {
		}
		person.setFirstName(form.getFirstName());
		try {
			person.setGender(Gender.valueOf(form.getGender()));
		} catch (Exception e) {
		}
		person.setLastName(form.getLastName()); /*********** SAVE ***/

		return person;

	}

	/*************************************************************************************/
	public Person update(long id, String firstName, String lastName, String birthDate, String gender) {
		/*********** SAVE ***/
		Person person = this.getPersonById(id);
		try {
			person.setBirthDate(formatter.parse(birthDate));

		} catch (Exception e) {
		}
		person.setFirstName(firstName);
		try {
			person.setGender(Gender.valueOf(gender));
		} catch (Exception e) {
		}
		person.setLastName(lastName); /*********** SAVE ***/

		return person;

	}

	/*************************************************************************************/

	public Person getPersonById(long id) {
		return new Person(id, "firstName_" + id, "lastName_" + id, new Date(), Gender.UNDEFINED);
	}

	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		for (long i = 1; i < 10; i++) {
			persons.add(this.getPersonById(i));

		}
		return persons;
	}

	public boolean delete(long id) {
		if (this.getPersonById(id) != null) {
			//// DELETE
			return true;
		}
		return false;

	}
}
