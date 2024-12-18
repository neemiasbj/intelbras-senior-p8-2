package br.com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class DateFormatUtil {
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String TIME_FORMAT = "HH:mm";
	public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";
	public static final String DATE_MASK = "##/##/####";
	public static final String TIME_MASK = "##:##";
	public static final String DATE_TIME_MASK = "##/##/#### ##:##";
	private static final SimpleDateFormat DATE_FORMATTER;
	private static final SimpleDateFormat DATE_TIME_FORMATTER;
	private static final SimpleDateFormat TIME_FORMATTER;
	private static final Calendar CALENDAR_INSTANCE;

	private DateFormatUtil() {
	}

	public static long parseStringToDateTime(String formatted) throws ParseException {
		if (formatted == null) {
			throw new NullPointerException("A data informada não pode ser nula.");
		} else {
			synchronized (DATE_TIME_FORMATTER) {
				Date date = DATE_TIME_FORMATTER.parse(formatted);
				String[] splitted = formatted.split(" ");
				String dateFormatted = splitted[0];
				String timeFormatted = splitted[1];
				validateDate(date, dateFormatted);
				validateTime(date, timeFormatted);
				return date.getTime();
			}
		}
	}

	public static String formatDateTimeToString(long date) {
		synchronized (DATE_TIME_FORMATTER) {
			return DATE_TIME_FORMATTER.format(date);
		}
	}

	public static long parseStringToDate(String dateFormatted) throws ParseException {
		if (dateFormatted == null) {
			throw new NullPointerException("A data informada não pode ser nula.");
		} else {
			synchronized (DATE_FORMATTER) {
				Date date = DATE_FORMATTER.parse(dateFormatted);
				validateDate(date, dateFormatted);
				return date.getTime();
			}
		}
	}

	public static String formatDateToString(long date) {
		synchronized (DATE_FORMATTER) {
			return DATE_FORMATTER.format(date);
		}
	}

	public static short parseStringToTime(String dateFormatted) throws ParseException {
		if (dateFormatted == null) {
			throw new NullPointerException("A data informada não pode ser nula.");
		} else {
			Date date;
			synchronized (DATE_FORMATTER) {
				date = TIME_FORMATTER.parse(dateFormatted);
			}

			validateTime(date, dateFormatted);
			short minutes = 0;
			synchronized (CALENDAR_INSTANCE) {
				CALENDAR_INSTANCE.setTime(date);
				minutes = (short) (minutes + CALENDAR_INSTANCE.get(11) * 60);
				minutes = (short) (minutes + CALENDAR_INSTANCE.get(12));
				return minutes;
			}
		}
	}

	public static String formatTimeToString(short timeInMinutes) {
		int hour = timeInMinutes / 60;
		int minute = timeInMinutes % 60;
		synchronized (CALENDAR_INSTANCE) {
			CALENDAR_INSTANCE.set(11, hour);
			CALENDAR_INSTANCE.set(12, minute);
			return TIME_FORMATTER.format(CALENDAR_INSTANCE.getTime());
		}
	}

	private static void validateDate(Date date, String formatted) throws ParseException {
		String[] splitted = formatted.split("/");
		int day = Integer.parseInt(splitted[0]);
		int month = Integer.parseInt(splitted[1]);
		int year = Integer.parseInt(splitted[2]);
		synchronized (CALENDAR_INSTANCE) {
			CALENDAR_INSTANCE.setTime(date);
			if (CALENDAR_INSTANCE.get(5) != day) {
				throw new ParseException("Dia inválido: " + day, 0);
			} else if (CALENDAR_INSTANCE.get(2) != month - 1) {
				throw new ParseException("Mês inválido: " + month, 0);
			} else if (CALENDAR_INSTANCE.get(1) != year) {
				throw new ParseException("Ano inválido: " + year, 0);
			} else if (year < 1970) {
				throw new ParseException("Só são válidas datas a partir do ano de 1970", 0);
			}
		}
	}

	private static void validateTime(Date date, String formatted) throws ParseException {
		String[] splitted = formatted.split(":");
		int hour = Integer.parseInt(splitted[0]);
		int min = Integer.parseInt(splitted[1]);
		synchronized (CALENDAR_INSTANCE) {
			CALENDAR_INSTANCE.setTime(date);
			if (CALENDAR_INSTANCE.get(12) != min) {
				throw new ParseException("Minuto inválido: " + min, 0);
			} else if (CALENDAR_INSTANCE.get(11) != hour) {
				throw new ParseException("Hora inválida: " + hour, 0);
			}
		}
	}

	static {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
		DATE_FORMATTER.setTimeZone(tz);
		DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		DATE_TIME_FORMATTER.setTimeZone(tz);
		TIME_FORMATTER = new SimpleDateFormat("HH:mm");
		TIME_FORMATTER.setTimeZone(tz);
		CALENDAR_INSTANCE = Calendar.getInstance(tz);
	}
}
