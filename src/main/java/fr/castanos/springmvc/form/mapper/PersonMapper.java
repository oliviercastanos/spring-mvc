package fr.castanos.springmvc.form.mapper;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import fr.castanos.common.domain.Gender;
import fr.castanos.common.domain.Person;
import fr.castanos.springmvc.form.PersonForm;

@Service
public class PersonMapper {
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public PersonForm toPersonForm(Person input) {
		if (input == null)
			return null;
		PersonForm form = new PersonForm();
		form.setBirthDate(formatter.format(input.getBirthDate()));
		form.setFirstName(input.getFirstName());
		form.setGender(input.getGender().toString());
		form.setId(input.getId());
		form.setLastName(input.getLastName());
		return form;
	}

	public Person toPerson(PersonForm input) {
		if (input == null)
			return null;
		Person person = new Person();
		try {
			person.setBirthDate(formatter.parse(input.getBirthDate()));

		} catch (Exception e) {
		}
		person.setFirstName(input.getFirstName());
		if (input.getGender() != null)
			person.setGender(Gender.valueOf(input.getGender()));
		person.setId(input.getId());
		person.setLastName(input.getLastName());
		return person;
	}

	public Person toPerson(String firstName, String lastName, String birthDate, String gender) {

		Person person = new Person();
		try {
			person.setBirthDate(formatter.parse(birthDate));

		} catch (Exception e) {
		}
		person.setFirstName(firstName);
		if (gender != null)
			person.setGender(Gender.valueOf(gender));

		person.setLastName(lastName);
		return person;
	}
}
