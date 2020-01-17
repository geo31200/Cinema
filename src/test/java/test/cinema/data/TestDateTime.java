package test.cinema.data;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDateTime {
	
	
	
	@Test
	void testParseFrenchDate() {
		
		String dateStr = "12/08/1991";
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println(date);
		
		assertAll(
		() ->assertEquals(12, date.getDayOfMonth() , "day"),
		() ->assertEquals(8, date.getMonthValue(), "month"),
		() ->assertEquals(1991, date.getYear(), "year")
				);
	}
	@Test
	void testFomatDate() {
		LocalDate date = LocalDate.now();
		var format = List.of( 
		DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", Locale.FRANCE),
		DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("es", "es")),
		DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("lv", "lv")),
		DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("ru", "ru")),
		DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("ja", "ja")),
		DateTimeFormatter.ofPattern("eeee dd MMMM yyyy", new Locale("ind", "ind"))
		);
		
		format.forEach(f-> System.out.println(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
		
		
	     
		
	}
	
	@Test
	void testLocalInde() 
	{
	 var date  = LocalDate.now();
     System.out.println("Les locales d'Inde sont :");
     
     Locale[] locale = Locale.getAvailableLocales();   
     Arrays.stream(locale)
     
   .filter(f-> f.getCountry().equals("IN"))
   .map(f-> date.format(DateTimeFormatter.ofPattern("eeee dd MMMM yyyy",f)))
   .forEach(System.out::println);
     
	}
	
	@Test
	void tourDuMondeEn80jours( ) 
	{

		 var forms = DateTimeFormatter.ofPattern("eeee dd MMMM yyyy, kk:mm");
		var formats = List.of(
				LocalDateTime.now(),
				LocalDateTime.now(ZoneId.of("America/New_York")),
				LocalDateTime.now(ZoneId.of("Pacific/Auckland")),
				LocalDateTime.now(ZoneId.of("Pacific/Midway"))
				);
		
		formats.forEach(f-> System.out.println(forms.format(f)));
		
	}
	
}
