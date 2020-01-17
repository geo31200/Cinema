package cinema.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class Movie {	//model

	//attributes
	private String title;
	private int year;
	private int duration;
	private Person director;
	private List<Person> actors;
	
	
	public Movie(String title, int year) {		
		this(title,year, 0);
		
	}
	
	public Movie(String title, int year, int duration) {		// param�tre de l'objet Movie
		
		this(title, year, duration,null);
	}

	
	
	public Movie(String title, int year, Person director) {
		
		this(title, year,0 , director );
		}

	public Movie(String title, int year, int duration, Person director) {
		super();
		this.title = Objects.requireNonNull(title);
		this.year = year;
		this.duration = duration;
		this.director = director;
		this.actors = new ArrayList<>();
	}

	
	// getter et setter


	public Person getDirector() {
		return director;
	}

	public void setDirector(Person director) {
		this.director = director;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = Objects.requireNonNull(title);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}



	@Override
	public String toString() {
		return title + " ("+ year +")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return this.title.contentEquals(other.title)
				&& this.year == other.year;
	}

	public void addActor(Person actor) {
		// TODO Auto-generated method stub
		this.actors.add(actor);
	}
	
	public void addActors(Collection<? extends Person> actors) {
		this.actors.addAll(actors);
		
	}
	
	public void addAllActors(Person... actors) 
	{
		this.addActors(Arrays.asList(actors));
	}

	
	public Iterator<Person> iteratorActors()
	{
		return this.actors.iterator();
	}
	
	public Stream<Person> streamActors()
	{
	return this.actors.stream();	
	} 
	
}
