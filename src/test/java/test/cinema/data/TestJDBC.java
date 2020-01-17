package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import cinema.data.Movie;
import cinema.data.Person;

class TestJDBC {

	static DataSource ds;
	
	@BeforeAll
	static void initDataSource() 
	{
		PGSimpleDataSource pgds = new PGSimpleDataSource();
		String host = "localhost";
		String dbname = "postgres";
		int port = 5432;
		String user = "cinema";
		String password = "password";
		pgds.setURL("jdbc:postgresql://"+host+":"+port+ "/"+dbname);
		pgds.setUser(user);
		pgds.setPassword(password);
		ds = pgds;
	
	}
	
	
	@Test
	void test() throws SQLException {
		
		var listMovie = new TreeSet<>(Comparator.comparing(Movie::getTitle).thenComparing(Movie::getYear));
		// thenComparing ajoute un autre critere de comparaison en cas d'égalité
		String sql = "select* from film";
		try (Connection connexion = ds.getConnection())
		{
			Statement request = connexion.createStatement();
			ResultSet res = request.executeQuery(sql);
			while (res.next()) 
			{
				String title = res.getString("titre");
				int year = res.getInt("annee");
				int duration = res.getInt("duree");
				
				Movie movie = new Movie(title, year, duration);
				
				listMovie.add(movie);
				
				
			}
		
		}
	}
	
	@Test
	void testLireFilmsFiltre() throws SQLException { 
	{
		var listMovie = new TreeSet<>(Comparator.comparing(Movie::getTitle).thenComparing(Movie::getYear));
		String sql = "select * from film where duree >= ?";
		int durationThreshold = 120;
	
		System.out.println(sql);
		try (
				Connection connexion = ds.getConnection();
				PreparedStatement request = connexion.prepareStatement(sql); )
		{
			request.setInt(1, durationThreshold);
		
			try (ResultSet res = request.executeQuery()) {
			
			while (res.next()) 
			{
				String title = res.getString("titre");
				int year = res.getInt("annee");
				int duration = res.getInt("duree");
							
				listMovie.add(new Movie(title, year, duration));
				
			}	
			}
			System.out.println(listMovie);
			assertAll(listMovie.stream() 
					.map(m-> () -> assertTrue(m.getDuration() >= durationThreshold, m.getTitle())));
			
		}
	}
	
	}
	
	
	@Test
	void testaddPerson() throws SQLException 
	{
		var persons = List.of (
				new Person("Bradley Cooper", LocalDate.of(1982, 02, 26)),
				new Person ("Gene Hackman", LocalDate.of(1930, 01, 30)),
				new Person ("Morgan Freeman", LocalDate.of(1937, 07, 01))
				);
		String sql = "insert into individu (prenom, nom, date_naissance) values (?,?,?)";
		
		try(
				Connection connexion = ds.getConnection();
				PreparedStatement request = connexion.prepareStatement(sql))
		{
			for (var person: persons) 
			{
				String fullName = person.getName();
				var parts = fullName.split(" ");
				System.out.println(Arrays.toString(parts));
				request.setString(1, parts[0]);
				request.setString(2, parts[1]);
				request.setDate(3, Date.valueOf(person.getBirthdate()));
				request.executeUpdate();
			}
		}
		
	}
	
}
