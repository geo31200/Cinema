package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import cinema.data.Person;

class TestPerson {

	@Test
	void test() {
		Person jp = new Person ("Joachin Phoenix", LocalDate.of(1974, 10, 28));
		Person tp = new Person ("Todd philips");
		Person person3 = new Person ("Clint Eastwood", LocalDate.of(1974, 10, 28));
		
		System.out.println(jp + " : " + jp.getAge());
		System.out.println(tp + " : " + tp.getAge());
	}

	
	
}
