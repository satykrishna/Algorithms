package java8.datetime.api;

import static java.time.temporal.TemporalAdjusters.next;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class TimeDateAPIEx {

	public static void localDateDefn() {

		LocalDate date = LocalDate.of(2010, 05, 15);

		int year = date.getYear();

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

		LocalDate d = LocalDate.from(date);

	}

	public static void localTimeFunction() {

		LocalTime localTime = LocalTime.of(13, 13, 13);

		int hour = localTime.getHour();

		int minute = localTime.getMinute();

		int second = localTime.getSecond();

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

		LocalDate d1 = LocalDate.now();

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

		Duration.ofDays(5);

		Duration.parse("12");

	}

	public static void dateManiplulation() {

		LocalDate localDate = LocalDate.now();

		LocalDate d1 = localDate.withYear(2004);

		LocalDate d2 = localDate.withDayOfMonth(21);

		LocalDate d3 = localDate.with(ChronoField.MICRO_OF_DAY, 5);

	}

	public static void nextWorkingDay() {

		LocalDate localDate = LocalDate.now();

		localDate = localDate.with(new NextWorkingDay());

		localDate = localDate.with(temporal -> {
			DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

			int daysToAdd = 1; // default;

			switch (dayOfWeek) {
			case FRIDAY:
				daysToAdd = 3;
				break;

			case SATURDAY:
				daysToAdd = 2;
				break;

			default:
				daysToAdd = 1;
				break;
			}

			return temporal.plus(daysToAdd, ChronoUnit.DAYS);
		});

		TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(temporal -> {
			DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

			int days2Add = 1;

			switch (dayOfWeek) {
			case FRIDAY:
				days2Add = 3;
				break;

			case SUNDAY:
				days2Add = 1;
				break;

			default:
				break;
			}

			return temporal.plus(days2Add, ChronoUnit.DAYS);
		});
	}

	public static void playWithTemporal() {

		LocalDate localDate = LocalDate.now();

		int month = localDate.get(ChronoField.MONTH_OF_YEAR);

		DayOfWeek dayOfWeek = localDate.getDayOfWeek();

		LocalTime localTime = LocalTime.now();

		localDate.with(ChronoField.YEAR, 2011);

		LocalDate transformedDate = localDate.minus(5, ChronoUnit.DAYS).plusDays(4);
		LocalDate d4 = localDate.plusWeeks(1);

		LocalDate d5 = localDate.minusYears(2);

		LocalDate d6 = localDate.plus(6, ChronoUnit.MONTHS);
	}

	public static void usingTemporalAdjusters() {

		LocalDate localDate = LocalDate.now();

		LocalDate d1 = localDate.with(next(DayOfWeek.SUNDAY));
	}

	public static LocalDate playWithTemporal(LocalDate inputDate) {

		return inputDate.with(temporal -> {

			DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

			int days2Add = 1;

			switch (dayOfWeek) {
			case FRIDAY:
				days2Add = 3;
				break;

			case SATURDAY:
				days2Add = 2;
				break;

			default:
				days2Add = 1;
				break;
			}

			return temporal.plus(days2Add, ChronoUnit.DAYS);
		});
	}

	public static LocalDate playWithTemporalAdjusters(LocalDate localDate) {
		return localDate.with(tempAdjuster);
	}

	private static TemporalAdjuster tempAdjuster =

			TemporalAdjusters.ofDateAdjuster(localDate -> {

				DayOfWeek dayOfWeek = localDate.getDayOfWeek();

				int days2Add = 1;

				if (dayOfWeek == DayOfWeek.SATURDAY) {
					days2Add = 2;
				}

				else if (dayOfWeek == DayOfWeek.FRIDAY) {
					days2Add = 3;
				}

				return localDate.plusDays(days2Add);

			});

	public static void dateFormatterAndParsing() {

		LocalDate date = LocalDate.now();

		String dateFormatter1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);

		System.out.println(dateFormatter1);

		String dateFormatter2 = date.format(DateTimeFormatter.ISO_ORDINAL_DATE);

		System.out.println(dateFormatter2);

		LocalDate parsedDateFromString = LocalDate.parse(dateFormatter2, DateTimeFormatter.ISO_ORDINAL_DATE);

		System.out.println(parsedDateFromString);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d - MMMM yy", Locale.CHINA);

		String formattedDate = date.format(formatter);

		System.out.println(formattedDate);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yy-MM-dd", Locale.CHINESE);

		LocalDate chineseDate = LocalDate.parse("18-04-12", formatter2);

		System.out.println(chineseDate);

		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");

		String formatter3Date = chineseDate.format(formatter3);

		System.out.println(formatter3Date);

		DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("dd/MMMM/yyyy", Locale.CANADA);

		LocalDate formatter4Date = LocalDate.parse(formatter3Date, formatter4);

		System.out.println("4:" + formatter4Date);

		DateTimeFormatter canadianDateFormatter = new DateTimeFormatterBuilder().appendText(ChronoField.DAY_OF_WEEK).appendLiteral(".")
				.appendText(ChronoField.MONTH_OF_YEAR).appendLiteral("").appendText(ChronoField.YEAR).parseCaseInsensitive().toFormatter(Locale.CANADA);
		String canadianDate = LocalDate.now().format(canadianDateFormatter);
		System.out.println(canadianDate);
	}

	public static void playWithZones() {

		ZoneId romeZone = ZoneId.of("Europe/Rome");
		LocalDate localDate = LocalDate.now();
		ZonedDateTime zdt1 = localDate.atStartOfDay(romeZone);
		LocalDateTime dateTime = LocalDateTime.of(2018, Month.APRIL, 30, 17, 00, 00, 00);
		ZonedDateTime zdt2 = dateTime.atZone(romeZone);
		System.out.println(zdt1);
		System.out.println(zdt2);
		Instant instant = Instant.now();
		ZonedDateTime zdt3 = instant.atZone(romeZone);
		Instant instantFromLocalDateTime = dateTime.toInstant((ZoneOffset) romeZone);
		LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);
	}
	
	public static void playWithZoneOffSet() {
		
		ZoneId romeZone = ZoneId.of("Europe/London");
		LocalDateTime localDateTime = LocalDateTime.now(romeZone);
		System.out.println(localDateTime);
		ZonedDateTime zdt1 = localDateTime.atZone(romeZone);
		
		ZoneOffset newyorkZoneOffSet = ZoneOffset.of("-05:00");
		OffsetDateTime dateTimeInNewYork = OffsetDateTime.of(localDateTime, newyorkZoneOffSet);
		System.out.println(dateTimeInNewYork);
		
		
	}

	public static void main(String[] args) {
		// localDateDefn();
		// playWithTemporal();
		// dateFormatterAndParsing();
//		playWithZones();
		playWithZoneOffSet();
	}

}

class NextWorkingDay implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {

		DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

		int daysToAdd = 1;

		if (dayOfWeek == DayOfWeek.FRIDAY)
			daysToAdd = 3;
		else if (dayOfWeek == DayOfWeek.SATURDAY)
			daysToAdd = 2;

		return temporal.with(ChronoField.DAY_OF_WEEK, daysToAdd);
	}

}