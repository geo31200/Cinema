package cinema.persistence.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;
import cinema.persistence.repository.MovieRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestMovieRepository {

	@Autowired
	MovieRepository repoMovie;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	void testInsert() {
		Movie movie = new Movie("Joker", 2019);
		repoMovie.save(movie);
		var id = movie.getIdMovie();
		assertNotNull(id);
		System.out.println("Id new movie : " + movie.getIdMovie());
	
	}

	@Test
	void testSelectAll()
	{
	 List<Movie> data = List.of(
			 new Movie ("Joker", 2019), 
			 new Movie ("Parasite", 2019, 132),
				new Movie ("Interstellar", 2014, 169),
				new Movie ("Gran torino", 2008, 116)
				);
				data.forEach(entityManager::persist);
				// when
				var dataRead = repoMovie.findAll();
				//then
				dataRead.forEach(System.out::println);
				assertEquals(data.size(), dataRead.size());
				
			assertTrue
			(dataRead.stream()
			.map(Movie::getTitle)
			.allMatch(t1 -> data.stream().map(Movie::getTitle).anyMatch(t2 -> t2.equals(t1)))
			);
	}
	
	
	@Test
	void TestFindById () 
	{
		//given
		Movie movie = new Movie ("Joker", 2019);
		entityManager.persist(movie);
		var id = movie.getIdMovie();
				//when
		var movieReadOpt = repoMovie.findById(id);
		System.out.println(movieReadOpt);
		assertAll(
		() ->assertTrue(movieReadOpt.isPresent()),
		() ->assertEquals(movie.getTitle(), movieReadOpt.get().getTitle())
		);
	}
	
	@Test
	void TestFindByTitle() 
	{
		//given
		String title = "Le Roi Lion";
		List<Movie> data = List.of(
				 new Movie ("Joker", 2019), 
				 new Movie (title, 2019),
					new Movie (title, 1994),
					new Movie ("Gran torino", 2008)
					);
		data.forEach(entityManager::persist);
		// when
		var dataRead = repoMovie.findByTitle(title);
		//then
		System.out.println(dataRead);
	}
	
	@Test
	void TestFindByYear() 
	{
		//given
				int year = 2019;
				List<Movie> data = List.of(
						 new Movie ("Joker", 2019), 
						 new Movie ("Le roi Lion", 2019),
							new Movie ("Le roi Lion", 1994),
							new Movie ("Gran torino", 2008)
							);
				data.forEach(entityManager::persist);
				// when
				var dataRead = repoMovie.findByYear(2019);
				//then
				System.out.println("année : " + dataRead);
		
	}
	
	@Test
	void findByYearGreaterThan() 
	{
		//given
				
				List<Movie> data = List.of(
						 new Movie ("Joker", 2019), 
						 new Movie ("Le roi Lion", 2019),
							new Movie ("Le roi Lion", 1994),
							new Movie ("Gran torino", 2008)
							);
				data.forEach(entityManager::persist);
				// when
				var dataRead = repoMovie.findByYearGreaterThan(2010);
				//then
				System.out.println("année : " + dataRead);
		
	}
	
	@Test
	void findByYearBetween() 
	{
		//given
				int year = 2002;
				int year1 = 2019;
				List<Movie> data = List.of(
						 new Movie ("Joker", 2019), 
						 new Movie ("Le roi Lion", 2019),
							new Movie ("Le roi Lion", 1994),
							new Movie ("Gran torino", 2008)
							);
				data.forEach(entityManager::persist);
				// when
				var dataRead = repoMovie.findByYearBetween(2002,2019);
				//then
				System.out.println("année : " + dataRead);
				assertAll(
				 () -> assertEquals(3, dataRead.size()),
				() -> assertTrue(dataRead.stream().mapToInt(Movie::getYear).allMatch(y -> (y >=year) && (y <=year1))));
		
	}
	
	@Test
	void findByTitleAndYear() 
	{
		//given
				String title = "le roi lion";
				int year = 2019;
				List<Movie> data = List.of(
						 new Movie ("Joker", 2019), 
						 new Movie ("Le roi Lion", 2019),
							new Movie ("Le roi Lion", 1994),
							new Movie ("Gran torino", 2008)
							);
				data.forEach(entityManager::persist);
				// when
				var dataRead = repoMovie.findByTitleAndYear("Le roi Lion",2019);
				//then
				System.out.println("année : " + dataRead);
		
	}
	
	@Test
	void saveMovieWithDirector() 
	{
		Person person = new Person ("Todd philips", LocalDate.of(1970, 12, 20));
		Movie movie = new Movie("Joker", 2019);
		entityManager.persist(person);
		repoMovie.save(movie);
		System.out.println(movie);
		System.out.println(person);
	}
	
	@Test
	void testfindByActorsNameEndingWith() 
	{
		//given
		
		var lion =  new Movie ("Le Roi Lion", 2019);
		 var arme = new Movie ("L'arme Fatale", 1987);
		 var mad = new Movie ("Mad Max", 1978);
		
		var movies = List.of(lion, arme, mad);
		
		movies.forEach(entityManager::persist);
		
		var mel = new Person ("Mel Gibson");
		var whoopi = new Person ("Whoopi Golberg");
		var danny = new Person("Danny Glover");
		
		entityManager.persist(mel);
		entityManager.persist(whoopi);
		entityManager.persist(danny);
		entityManager.flush();
		
		lion.getActors().add(whoopi);
		mad.getActors().add(mel);
		
		Collections.addAll(arme.getActors(), mel,danny);
		entityManager.flush();
		
		//when
		
		var moviesWithMel = repoMovie.findByActorsNameEndingWithIgnoreCase("Gibson");
		
		//then
		assertAll(
				() -> assertTrue(moviesWithMel.contains(mad)),
				() ->assertTrue(moviesWithMel.contains(arme)),
				() -> assertFalse(moviesWithMel.contains(lion)));
		
	}
	
}

