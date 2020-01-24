package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestMovie {

	@Test
	void testFirst() 
	{
		Movie movie = new Movie("Joker", 2019, 165);
		Movie movie2 = new Movie ("Parasite", 2019, 132);
		Movie movie3 = new Movie ("Interstellar", 2014);
		
		List<Movie> movies = List.of(movie, movie2, movie3);

		Movie oneMovie = movies.get(0);
		
		System.out.println("Movies:" + movies);
		
			// add director
		Person tp = new Person ("Todd philips");
		movie.setDirector(tp);
		System.out.println(movie + "r�alis� par : " + movie.getDirector().getName());
		
		Person clint = new Person("Clint Eastwood", LocalDate.of(1930, 5, 31) );
		System.out.println(clint + "a " + clint.getAge().getAsInt() + "ans");
		Movie movieGT = new Movie ("Gran Torino", 2008, 116, clint);
		Movie movieI = new Movie ("Impitoyable", 1992, clint);
		System.out.println("Director of " + movieGT + " : " + movieGT.getDirector());
		
	}

	
	@Test
	void testEquals()
	{
		Movie movieJ = new Movie("Joker", 2019, 165);
		Movie movieP = new Movie ("Parasite", 2019, 132);
		Movie movie = movieJ;
		System.out.println(movieJ == movieP);
		System.out.println(movieJ == movieJ);
		System.out.println(movie == movieJ);
		
	}
	
	
	@Test
	void testEquals2()
	{
		Movie movieChaosI = new Movie("Chaos", 2005, 100);
		Movie movieChaosII = new Movie ("Chaos", 2005, 132);
		
		System.out.println(movieChaosI.equals(movieChaosII));
		
		
	}
	
	
	
}
 