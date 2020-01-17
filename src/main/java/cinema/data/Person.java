package cinema.data;

import java.time.LocalDate;
import java.util.Objects;
import java.util.OptionalInt;

public class Person {

	private String name;
	private LocalDate birthdate;
	
	
	
	public Person(String name, LocalDate birthdate) {
		super();
		this.name = name;
		this.birthdate = birthdate;
	}

	
	public Person(String name) {
		
		this(name, null);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getBirthdate() {
		return birthdate;
	}


	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}


	@Override
	public String toString() {
		return name + "(" + Objects.toString(birthdate,"unknown") +")";
	}
	
	public OptionalInt getAge() 
	{
		if (Objects.isNull(birthdate)) {
			
			return OptionalInt.empty();
		};
		
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.of(today.getYear(), birthdate.getMonth(), birthdate.getDayOfMonth());
		
		int deltaYear = today.getYear() - birthdate.getYear() ;
		
		if (today.compareTo(birthday) < 0) 
		{
			deltaYear--; // egale -1
		}
		return OptionalInt.of(deltaYear);
	}
	
}
