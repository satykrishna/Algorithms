package java8.datetime.api;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class TimeDateAPIEx {

	
	public static void localDateDefn() {
		
		LocalDate date = LocalDate.of(2010, 05, 15);
		
		int year  = date.getYear();
		
		Month month = date.getMonth();
		
		int day = date.getDayOfMonth();
		
		DayOfWeek dow = date.getDayOfWeek();
		
		int noofDaysinMonth = date.lengthOfMonth();
		
		boolean isLeapYear = date.isLeapYear();
		
		LocalDate now = LocalDate.now();

		year = date.get(ChronoField.YEAR);
		
		int month_ofYear = date.get(ChronoField.MONTH_OF_YEAR);
		
		day = date.get(ChronoField.DAY_OF_MONTH);
		
		LocalDate localDate = LocalDate.parse("2014-1-13");
		
	}
	
	public static void localTimeFunction() {
		
		LocalTime localTime = LocalTime.of(13, 13, 13);
		
		int hour = localTime.getHour();
		
		int minute = localTime.getMinute();
		
		int second  = localTime.getSecond();
		
		localTime = LocalTime.parse("13:45:20");
	}

	
	public static void localDateTimeFn() {
		
		LocalDate date = LocalDate.now();
		
		LocalTime time = LocalTime.now();
		
		LocalDateTime ldt1 = LocalDateTime.of(date, time);
		
		ldt1 = date.atTime(time);
		
		ldt1 = time.atDate(date);
		
		date = ldt1.toLocalDate();
		
		time = ldt1.toLocalTime();
	}

	
	public static void instanceFunction() {
		Instant.ofEpochMilli(3);
		Instant.ofEpochSecond(3);
		Instant.ofEpochSecond(3, 0);
		Instant.ofEpochSecond(3, 1_000_000_000);
		Instant.ofEpochSecond(4, -1_000_000_000);
	}

	public static void durationOrPeriodFn() {
		
		LocalDate d1  = LocalDate.now();
		
		LocalDate d2 = LocalDate.now();
		
		Duration duration = Duration.between(d1, d2);
		
		LocalDateTime ldt1 = LocalDateTime.now();
		
		LocalDateTime ldt2 = LocalDateTime.now();
		
		Duration d12 = Duration.between(ldt1, ldt2);
		
		Period tenDays = Period.between(d1, d2);
		
		Duration threeMins = Duration.ofMinutes(3);
		
		Duration threeMonths = Duration.of(3, ChronoUnit.MONTHS);
		
		Period tenDays1 = Period.ofDays(10);
		
		Period threeWeeks = Period.ofWeeks(3);
		
		Period twoYearSixMonthsOneDay = Period.of(2, 6, 1);
		
		
		
	}
	
	public static void main(String[] args) {
		localDateDefn();
	}
	
}
