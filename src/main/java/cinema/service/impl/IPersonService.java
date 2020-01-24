package cinema.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;

import cinema.persistence.entity.Person;

public interface IPersonService {

	Set<Person>getfindByName(String name);
	Set<Person>getfindByNameContaining(String name);
	Set<Person>getfindByNameContainingIgnoreCase(String name);
	
	@Query("select p from Person p where extract(year from birthdate) = ?1")
	Set<Person>getfindByBirthdateYear(int year);
	List<Person> getAllPersons();
	
	
}
