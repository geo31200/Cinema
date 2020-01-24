package cinema.persistence.repository; //pour chaque entité il faut un repository

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Person;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

	Set<Movie>findByTitle(String title);
	
	Set<Movie>findByTitleContainingIgnoreCase(String title);
	
	Set<Movie>findByYear(int year);
	
	Set<Movie>findByYearGreaterThan(int year);
	
	Set<Movie>findByYearBetween(int year, int years);
	
	Set<Movie>findByTitleAndYear(String title, int year);
	
	Set<Movie>findByDirector(Person director);
	
	Set<Movie>findByDirectorNameEndingWithIgnoreCase(String name);

	Set<Movie>findByDirectorName(String name);
	
	Set<Movie>findByActorsIdPerson(int idPerson);
	
	Set<Movie>findByActors(Person actor);
	
	Set<Movie>findByActorsName(String name);
	
	Set<Movie>findByActorsNameEndingWithIgnoreCase(String name);

}
