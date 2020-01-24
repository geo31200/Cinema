package cinema.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import cinema.persistence.entity.Movie;

public interface IMovieService 
{
	List<Movie> getAllMovies();
	Optional<Movie> getmovieById(int idMovie); 
	Set<Movie> getmovieByTiltle(String title) ;
	Set<Movie> getmovieByTiltleAndYear(String title,  int year);
	Set<Movie> getmovieByYear(int year, int years);
	Set<Movie> getmovieByDirector(String name );
	Set<Movie> getmovieByDirectoriD(int idDirector );
	Set<Movie> getmovieByActor(String name);
	Set<Movie> getfindByActorId(int idPerson);
}
