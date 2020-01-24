package cinema.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.persistence.entity.Movie;
import cinema.service.impl.IMovieService;

@RestController
@RequestMapping("/api/movie")
public class MovieController 
{
	
	@Autowired
	IMovieService movieservice;
	
	@GetMapping
	@ResponseBody
	public List<Movie> allMovies () 
	{
	return movieservice.getAllMovies();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<Movie> movieById(@PathVariable("id")int idMovie) 
	{
		return movieservice.getmovieById(idMovie);
		
	}
	
	@GetMapping("/title")
	@ResponseBody
	public Set<Movie> movieByTiltle(@RequestParam("t") String title) 
	{
		return movieservice.getmovieByTiltle(title);
		
	}
	
	@GetMapping("/titleandyear")
	@ResponseBody
	public Set<Movie> movieByTiltleAndYear(@RequestParam("t") String title, @RequestParam("y") int year) 
	{
		return movieservice.getmovieByTiltleAndYear(title, year);
	}
	
	@GetMapping("/betweenyear")
	@ResponseBody
	public Set<Movie> movieByYear(@RequestParam("y") int year, @RequestParam("ys") int years) 
	{
		return movieservice.getmovieByYear(year, years);
	}
	
	@GetMapping("/director")
	@ResponseBody
	public Set<Movie> movieByDirector(@RequestParam("n") String name )
	{
		return movieservice.getmovieByDirector(name);
	}
	
	@GetMapping("/idDirector")
	@ResponseBody
	public Set<Movie> movieByDirectoriD(@RequestParam("d") int idDirector )
	{
		return movieservice.getmovieByDirectoriD(idDirector);
	}
	
	@GetMapping("/actor")
	@ResponseBody
	public Set<Movie> movieByActor(@RequestParam("n") String name)
	{
		return movieservice.getmovieByActor(name);
	}
	
	@GetMapping("/idActor")
	@ResponseBody
	public Set<Movie> findByActorId(@RequestParam("a") int idPerson)
	{
		return movieservice.getfindByActorId(idPerson);
	}
	
//	@PostMapping
//	@ResponseBody
//	public Movie addMovie (@RequestBody Movie movie) 
//	{
//		Movie movieSaved = movieRepository.save(movie);
//		movieRepository.flush();
//		return movieSaved;
//	}
//	
//	@PutMapping("/modify")
//	@ResponseBody
//	public Optional<Movie> modifyMovie (@RequestBody Movie movie) 
//	{
//		var optMovie = movieRepository.findById(movie.getIdMovie());
//		
//		optMovie.ifPresent(m-> {
//			m.setTitle(movie.getTitle());
//			m.setYear(movie.getYear());
//			m.setDuration(movie.getDuration());
//			m.setDirector(movie.getDirector());
//			movieRepository.flush();
//		});
//		
//		return optMovie;
//	}
//	
//	@PutMapping("/addActor")
//	@ResponseBody
//	public Optional<Movie> addactor(@RequestParam ("a") int idActor, @RequestParam ("m") int idMovie) 
//	{
//		var optMovie = movieRepository.findById(idMovie);
//		var optActor = personRepository.findById(idActor);
//
//		if (optMovie.isPresent() && optActor.isPresent() ) 
//		{
//			optMovie.get().getActors().add(optActor.get());
//			movieRepository.flush();
//		}
//	
//		return optMovie;
//	}
//	
//	@PutMapping("/setDirector")
//	@ResponseBody
//	public Optional<Movie> setDirector(@RequestParam ("d") int idDirector, @RequestParam ("m") int idMovie) 
//	{
//		var optMovie = movieRepository.findById(idMovie);
//		var optDirector = personRepository.findById(idDirector);
//
//		if (optMovie.isPresent() && optDirector.isPresent() ) 
//		{
//			optMovie.get().setDirector(optDirector.get());
//			movieRepository.flush();
//		}
//	
//		return optMovie;
//	}
//	
//	
//	
//	
//	@DeleteMapping("/{id}")
//	@ResponseBody
//	public Optional<Movie> deleteMovie(@PathVariable("id") int idMovie) 
//	{
//		var movieToDelete = movieRepository.findById(idMovie);
//		movieToDelete.ifPresent(m -> 
//		{
//			movieRepository.delete(m);
//			movieRepository.flush();
//			
//		});
//		return movieToDelete;
//			
//	}
//		
	
	
}
