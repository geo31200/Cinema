package cinema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import cinema.persistence.entity.Person;
import cinema.service.impl.IPersonService;


public class PersonController {

	@Autowired
	IPersonService personService;
	
@PostMapping
Person addPerson (Person person) 
{
	return person;
}

@GetMapping
List<Person> getAllPersons() 
{
	return personService.getAllPersons();
	}

}
