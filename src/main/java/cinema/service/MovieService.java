package cinema.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cinema.persistence.entity.Movie;
import cinema.persistence.repository.MovieRepository;
import cinema.persistence.repository.PersonRepository;
import cinema.service.impl.IMovieService;



@Service
@Transactional
public class MovieService implements IMovieService
{

	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	PersonRepository personRepository;
	
	
	@Override
	public List<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return movieRepository.findAll();
	}

	@Override
	public Optional<Movie> getmovieById(int idMovie) {
		// TODO Auto-generated method stub
		return movieRepository.findById(idMovie);
	}

	@Override
	public Set<Movie> getmovieByTiltle(String title) {
		// TODO Auto-generated method stub
		return movieRepository.findByTitleContainingIgnoreCase(title);
	}
    
	
	@Override
	public Set<Movie> getmovieByTiltleAndYear(String title, int year) {
		// TODO Auto-generated method stub
		return movieRepository.findByTitleAndYear(title, year);
	}

	@Override
	public Set<Movie> getmovieByYear(int year, int years) {
		// TODO Auto-generated method stub
		return movieRepository.findByYearBetween(year, years);
	}

	@Override
	public Set<Movie> getmovieByDirector(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Movie> getmovieByDirectoriD(int idDirector) {
		// TODO Auto-generated method stub
		var directorOpt = personRepository.findById(idDirector);
		return directorOpt.map(d -> movieRepository.findByDirector(d)).orElseGet(()->Collections.emptySet());
	}

	@Override
	public Set<Movie> getmovieByActor(String name) {
		// TODO Auto-generated method stub
		return movieRepository.findByActorsNameEndingWithIgnoreCase(name);
	}

	@Override
	public Set<Movie> getfindByActorId(int idPerson) {
		// TODO Auto-generated method stub
		return movieRepository.findByActorsIdPerson(idPerson);
	}

	
	
}
