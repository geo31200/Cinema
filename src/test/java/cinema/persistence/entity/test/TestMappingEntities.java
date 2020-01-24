package cinema.persistence.entity.test;

/**
 * * this not a unit case
 */
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMappingEntities {

	@Autowired
	PersonRepository repoPersons;
	
	@Autowired
	MovieRepository repoMovie;
	
	@Autowired
	EntityManager entitymanager;
	
	@Rollback(false)
	@Test
	void testSaveData() {
	var joaq =	new Person ("Joachin Phoenix", LocalDate.of(1974, 10, 28));
	var gege =	new Person ("Gerard Darmond", LocalDate.of(1948, 02, 29));
	var todd =	new Person ("Todd philips", LocalDate.of(1970, 12, 20));
	var clint =	new Person ("Clint Eastwood", LocalDate.of(1930, 10, 28));
	var brad =	new Person("Bradley Cooper", LocalDate.of(1982, 02, 26));
	var gene =	new Person ("Gene Hackman", LocalDate.of(1930, 01, 30));
	var morgan = new Person ("Morgan Freeman", LocalDate.of(1937, 07, 01));
	
	var persons = List.of(joaq,todd,clint,brad,gene,morgan);	
		persons.forEach(repoPersons::save);
		
			var joker =	new Movie("Joker", 2019, 165, todd);
			var	parasite = new Movie ("Parasite", 2019, 132);
			var interstellar = new Movie ("Interstellar", 2014, 169);
			var grantorino = new Movie ("Gran torino", 2008, 116, clint);
			var impitoyable = new Movie ("Impitoyable", 1992, 131,  clint);
			var americansniper = new Movie ("American Sniper", 2014, 133, clint);
			var vbt = new Movie ("Very Bad Trip", 2009, 100, todd);
			var avengers3 =	new Movie ("Avengers - Infinity war", 2018, 149);
			var avengers4 =	new Movie ("Avengers - Endgame",2019, 181);
			var avengers1 = new Movie ("Avengers ", 2012, 143);
			var captain = new Movie ("Captain Marvel ", 2019, 123);
			var lpp = new Movie ("Les pleins pouvoirs", 1997, 120); 
			var avengers2 = new Movie ("Avengers - l'ères d'ultron", 2015, 141);
			
			var movies = List.of(joker, parasite,interstellar,grantorino,impitoyable,americansniper,vbt,avengers3);
			movies.forEach(repoMovie::save);
			
			
	}
	@Rollback(false)
	@Test
	void testInsert() 
	{
		var movies = repoMovie.findByTitle("Interstellar");
		if (movies.size()>0) 
		{
			var interstellar = movies.stream()
					.findFirst().get();
			var chris = new Person ("Christopher Nolan", LocalDate.of(1970, 07, 30));
			repoPersons.save(chris);
			interstellar.setDirector(chris);
		}
		
		
	}
	
	@Rollback(false)
	@Test
	void testSelectMovieWithDirector() 
	{
		var movies = repoMovie.findByTitle("Interstellar");
		if (movies.size()>0) 
		{
			var interstellar = movies.stream()
					.findFirst().get();
			var director = interstellar.getDirector();
		System.out.println(director);
	}
	
	}
	
	@Rollback(false)
	@Test
	void test3 () 
	{
		var bat = new Movie("The dark knight", 2008, 165);
		var chris = repoPersons.findByName("Christopher Nolan").stream().findFirst().get();
		repoMovie.save(bat);
		bat.setDirector(chris);
		repoMovie.flush();
	}	
	
	
	@Rollback(false)
	@Test
	void testselectByDirector () 
	{
	
		var movie = repoMovie.findByDirectorNameEndingWithIgnoreCase("eastwood");
		var nolan = repoPersons.findByName("Christopher Nolan").stream().findFirst().get();
		
		var movie1 = repoMovie.findByDirector(nolan);
		System.out.println(movie1);
	System.out.println(movie);
	}	
	
	
	
	
	
	@Test
	void testselectPersonByYear () 
	{
		var data = repoPersons.findByBirthdateYear(1930);
		System.out.println(data);
	}
	
	@Rollback(false)
	@Test
	void MovieWithActor() 
	{
		var impitoyable = repoMovie.findByTitle("impitoyable").stream().findFirst().get();
		var clint = repoPersons.findByName("Clint Eastwood").stream().findFirst().get();
		var gene = repoPersons.findByName("Gene Hackman").stream().findFirst().get();
		impitoyable.setActors(List.of(clint,gene));
		repoMovie.flush();

	} 
	
	@Rollback(false)
	@Test
	void MovieaddActor() 
	{
		var impitoyable = repoMovie.findByTitle("impitoyable").stream().findFirst().get();
		var morgan = repoPersons.findByName("Morgan Freeman").stream().findFirst().get();
		impitoyable.getActors().add(morgan);
		repoMovie.flush();
	}
	
	@Test
	void testLazyActors () 
	{
		var impitoyable = repoMovie.findByTitle("impitoyable").stream().findFirst().get();
		var actors = impitoyable.getActors();
		System.out.println(actors);

	}
	
	
	
}
