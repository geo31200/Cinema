package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cinema.data.Movie;
import cinema.data.Person;

class TestCinema {

	List<Movie> movies;
	List<Person> persons;
 	
	@BeforeEach
	void initData() 
	{
		persons = new ArrayList<Person>();
				
	Collections.addAll(persons,
				new Person ("Joachin Phoenix", LocalDate.of(1974, 10, 28)),
				new Person ("Gerard Darmond", LocalDate.of(1948, 02, 29)),
				new Person ("Todd philips", LocalDate.of(1970, 12, 20)),
				new Person ("Clint Eastwood", LocalDate.of(1974, 10, 28)),
				new Person("Bradley Cooper", LocalDate.of(1982, 02, 26)),
				new Person ("Gene Hackman", LocalDate.of(1930, 01, 30)),
				new Person ("Morgan Freeman", LocalDate.of(1937, 07, 01))
				

		);		
	
	var clint = persons.get(3);
	var todd = persons.get(2);
	
	movies = new ArrayList<>();
	Collections.addAll(movies, 
			new Movie("Joker", 2019, 165, todd),
			new Movie ("Parasite", 2019, 132),
			new Movie ("Interstellar", 2014, 169),
			new Movie ("Gran torino", 2008, 116, clint),
			new Movie ("Impitoyable", 1992, 131,  clint),
			new Movie ("American Sniper", 2014, 133, clint),
			new Movie ("Very Bad Trip", 2009, 100, todd),
			new Movie ("Avengers - Infinity war", 2018, 149),
			new Movie ("Avengers - Endgame",2019, 181),
			new Movie ("Avengers ", 2012, 143),
			new Movie ("Captain Marvel ", 2019, 123),
			new Movie ("Avengers - l'ère d'ultron", 2015, 141)
			);
	
	movies.get(0).addActor(persons.get(0));
	movies.get(4).addAllActors(persons.get(3), persons.get(5), persons.get(6));
	
	var actorsParasite = List.of
			( 
					new Person ("Kang-ho Song"),
					new Person ("Yeo-jeong Jo"),
					new Person ("Woo-sik Choi"),
					new Person ("Jeong-eun Lee ")
			);
	persons.addAll(actorsParasite);
	movies.get(1).addActors(actorsParasite);
	}
	
	@Test
	void testModifiableList()  {
	System.out.println(persons);
	persons.add(new Person("Bradley Cooper", LocalDate.of(1982, 02, 26)));
	}

	@Test // relie les films et les réalisateurs
	
	void displayMoviesForI(){
		
		for(int i=0; i < movies.size() ; i++)
		{
			var movie = movies.get(i);
			System.out.println(" - " + movie + " directed by " + movie.getDirector());
		}
	}
		@Test // relie les films et les réalisateurs
		
		void displayMoviesForEach(){
			
			System.out.println("*** Movie list***");
			for ( var movie: movies)
			{
				System.out.println(" - " + movie + " directed by " + movie.getDirector());
			}
			
	}
	
		
@Test // relie les films et les réalisateurs
		
		void totalDurationOfMoviesDirectedByClintEastwood()
{
			int totalDuration = 0;
			
			var clint = persons.get(3);
			
			for (var movie: movies)
			{
			if (movie.getDirector() == clint) {			
				totalDuration += movie.getDuration();	}
			}
			System.out.println("Total duration of movies directed by Clint Eastwood : " + totalDuration + "min");
	}
		
	

	
	
	@Test
	void testSortedMovies () 
	{
		SortedSet <Movie> sortedMovies = new TreeSet<>((m1,m2) -> -1);
		sortedMovies.addAll(movies);
		System.out.println(movies);
	}

	@Test
	void testSortMovies () 
	{Comparator.comparing(Movie::getYear, Comparator.reverseOrder()).thenComparing(Movie::getTitle); //= .thenComparing(m->m.get(title)
		Collections.sort(movies, Comparator.comparing(Movie::getYear, Comparator.reverseOrder()).thenComparing(Movie::getTitle)); //= .thenComparing(m->m.get(title)
		System.out.println(movies);
		
	}
	@Test
	void totalDurationOfMoviesDirectedByClintEastwoodStreaming() 
	{
		
		var clint = persons.get(3);
		int dureeTotale = movies.stream()
		.filter(m -> clint.equals(m.getDirector()))
		.mapToInt(Movie::getDuration)
//		.forEach(System.out::println);
		.sum();
		System.out.println("total Duration Of Movies Directed By Clint Eastwood :" + dureeTotale);
	}
	
	@Test
	void AvengersFirtYears() 
	{
		var FirstYears = movies.stream()
		.filter(m -> m.getTitle().contains("Avengers"))
		.mapToInt(Movie::getYear)
		.min();
		System.out.println(" La première année de sortie d'Avengers est : " + FirstYears);
	}
	
	@Test
	void AvengersStats() 
	{
		var Stats = movies.stream()
		.filter(m -> m.getTitle().contains("Avengers"))
		.mapToInt(Movie::getYear)
		.summaryStatistics();
		System.out.println(" La dernière année de sortie d'Avengers est : " + Stats.getMin());
		System.out.println(" La dernière année de sortie d'Avengers est : " + Stats.getMax());

	}
	
	@Test
	void TestListeChronologiqueAvenfersMovie() 
	{
		var avengersMovies = movies.stream()
		.filter(m -> m.getTitle().contains("Avengers"))
		.collect(Collectors.toCollection(()->new TreeSet <> (Comparator.comparing(Movie::getYear)))); // collect attend un Supllier <T> : () -> T
		System.out.println(avengersMovies);
	}
	
	@Test
	void titlesAvengersMovies () {
		var joinedTitles = movies.stream()
				.filter(m ->m.getTitle().contains("Avengers"))
				.map(Movie::getTitle)
				.collect(Collectors.joining(", "));
		System.out.println(joinedTitles);
		
	}
	
	@Test
	void testLimit ()
	{
		movies.stream()
		.filter(m -> m.getYear() > 1900)
		.limit(16)
		.collect(Collectors.toCollection(()->new TreeSet <> (Comparator.comparing(Movie::getYear))))
		.forEach(System.out::println);
	}
	
	@Test
	void ListfilmsPlusDe2heures() 
	{
		long nbFilms = movies.stream()
		.filter(m -> m.getDuration() >= 120)
		.count();
		System.out.println(" le nombre de films de plus de 2 heures est de : " + nbFilms);
	}
	
	@Test
	void titreLePlusLong()
	{
		var maxlength = movies.stream()
		.map(Movie::getTitle)
		.mapToInt(String::length)
		.max()
		.getAsInt();
		System.out.println("Le film avec le titre le plus long est : " + maxlength);
		var titleMaxLength = movies.stream()
		.map(Movie::getTitle)
		.filter(t -> t.length() == maxlength)
		.collect( Collectors.toList());
		System.out.println(titleMaxLength);
	}
	
	@Test
	void nbMovieByYear() 
	{
	var res = movies.stream()
		.collect(Collectors.groupingBy(Movie::getYear, Collectors.counting()));
		System.out.println(res);
	}
	
	@Test
	void nbMovieBydirector() 
	{
	var res = movies.stream()
		.filter(m ->  Objects.nonNull(m.getDirector()))
		.collect(Collectors.groupingBy(Movie::getDirector, Collectors.counting()));
		System.out.println(res);
	}
	
	@Test
	void FilmographBydirector() 
	{
	var res = movies.stream()
		.filter(m ->  Objects.nonNull(m.getDirector()))
		.collect(Collectors.groupingBy(Movie::getDirector, 
				Collectors.toCollection(() -> new TreeSet<> (Comparator.comparing(Movie::getYear, 
				Comparator.reverseOrder())))));
		System.out.println(res);
	}
	
	@Test
	void personsByDecade() 
	{
	
	var res	= persons.stream()
	.collect(Collectors.groupingBy(p ->p.getBirthdate().getYear() / 10));
	System.out.println(res);	
	}
	
	@Test
	void testParasite ( ) 
	{
		movies.stream()
		.filter(m -> m.getTitle().equals("Parasite"))
		.findFirst()
		.ifPresent(System.out::print);
	}
	
	@Test
	void testParasite2() 
	{
		var movie = movies.get(1);
		var acteurs = movie.streamActors()
		.map(Person::getName)
		.collect(Collectors.joining(", "));
		System.out.println(acteurs);
		
	}
	@Test
	void testParasiteIterable() 
	{
		var movie = movies.get(1);
		var acteurs = movie.streamActors()
		.map(Person::getName)
		.collect(Collectors.joining(", "));
		System.out.println(acteurs);
		
	}
	
}
