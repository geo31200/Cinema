package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import cinema.persistence.entity.Person;

class TestPersonRepository {

	@Test
	void test() {
	
	List<Person> person = List.of(
			
			new Person ("Joachin Phoenix", LocalDate.of(1974, 10, 28)),
			new Person ("Gerard Darmond", LocalDate.of(1948, 02, 29)),
			new Person ("Todd philips", LocalDate.of(1970, 12, 20)),
			new Person ("Clint Eastwood", LocalDate.of(1974, 10, 28)),
			new Person("Bradley Cooper", LocalDate.of(1982, 02, 26)),
			new Person ("Gene Hackman", LocalDate.of(1930, 01, 30)),
			new Person ("Morgan Freeman", LocalDate.of(1937, 07, 01))
				
			
			);
		
				System.out.println(person);
	}

}
