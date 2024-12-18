package br.com.thidi.middleware.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CLogger {
	private static final Logger SENIOR_LOGGER = LogManager.getLogger("");
	private static final Logger HIKVISION_LOGGER = LogManager.getLogger("");
	private static final Logger WEBSOCKET_LOGGER = LogManager.getLogger("");
	private static final Logger SPRING_LOGGER = LogManager.getLogger("");
	private static final Logger LOGGER = LogManager.getLogger("");
	private static final Logger PROPERTIES = LogManager.getLogger("");
	private static final Logger FILE = LogManager.getLogger("");
	private static final Logger GENERAL = LogManager.getLogger("");

	public static void logWebSocketInfo(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		WEBSOCKET_LOGGER.info(logMessage);
		System.out.println("WEBOSCKET INFO: " + logMessage);
	}

	public static void logSpringInfo(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		SPRING_LOGGER.info(logMessage);
		System.out.println("SPRING INFO: " + logMessage);
	}

	public static void logSeniorInfo(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		SENIOR_LOGGER.info(logMessage);
		System.out.println("SENIOR INFO: " + logMessage);
	}

	public static void logIntelbrasInfo(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		HIKVISION_LOGGER.info(logMessage);
		System.out.println("HIKVISION INFO: " + logMessage);
	}

	public static void logSeniorError(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		SENIOR_LOGGER.error(logMessage);
		System.out.println("SENIOR ERROR: " + logMessage);
	}

	public static void logIntelbrasError(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		HIKVISION_LOGGER.error(logMessage);
		System.out.println("HIKVISION ERROR: " + logMessage);
	}

	public static void logWebSocketError(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		WEBSOCKET_LOGGER.error(logMessage);
		System.out.println("WEBSOCKET DEBUG: " + logMessage);
	}

	public static void logWebSocketDebug(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.getMessage();
//		WEBSOCKET_LOGGER.debug(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("HIKVISION DEBUG: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logIntelbrasDebug(String func, String message) {
		System.out.println("HIKVISION DEBUG: " + getFormattedLogMessage(func, message));
//		HIKVISION_LOGGER.debug(getFormattedLogMessage(func, message));
	}

	public static void logSeniorDebug(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.getMessage();

		System.out.println("SENIOR DEBUG: " + String.valueOf(logMessage) + " - " + errorMessage);
//		SENIOR_LOGGER.debug(String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logSeniorDebug(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
		System.out.println("SENIOR DEBUG: " + message);
//     SENIOR_LOGGER.debug(logMessage);
	}

	public static void logIntelbrasError(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.toString();
//		HIKVISION_LOGGER.error(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("HIKVISION ERROR: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logSeniorError(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.toString();
//		SENIOR_LOGGER.error(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("SENIOR ERROR: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void loggerInfo(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		LOGGER.info(logMessage);
		System.out.println("LOGGER INFO: " + logMessage);
	}

	public static void loggerError(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.toString();
//		LOGGER.error(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("LOGGER ERROR: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logWebSocketError(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.getMessage();
//		WEBSOCKET_LOGGER.error(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("WEBSOCKET ERROR: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logPropertiesInfo(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		PROPERTIES.info(logMessage);
		System.out.println("PROPERTIES INFO: " + logMessage);
	}

	public static void logPropertiesError(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.toString();
//		PROPERTIES.error(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("PROPERTIES ERROR: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logPropertiesDebug(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.getMessage();
//		PROPERTIES.debug(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("PROPERTIES DEBUG: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logFileInfo(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		FILE.info(logMessage);
		System.out.println("FILE INFO: " + logMessage);
	}

	public static void logFileError(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.toString();
//		FILE.error(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("FILE ERROR: " + logMessage);
	}

	public static void logGeneralDebug(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.getMessage();
//		FILE.debug(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("GENERAL DEBUG: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logGeneralInfo(String func, String message) {
		String logMessage = getFormattedLogMessage(func, message);
//		GENERAL.info(logMessage);
		System.out.println("GENERAL DEBUG: " + logMessage);
	}

	public static void logGeneralError(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.toString();
//		GENERAL.error(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("GENERAL ERROR: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	public static void logFileDebug(String func, String message, Throwable throwable) {
		String logMessage = getFormattedLogMessage(func, message);
		String errorMessage = throwable.getMessage();
//		GENERAL.debug(String.valueOf(logMessage) + " - " + errorMessage);
		System.out.println("FILE DEBUG: " + String.valueOf(logMessage) + " - " + errorMessage);
	}

	private static String getFormattedLogMessage(String func, String message) {
		return String.format("* %s * %s", new Object[] { func, message });
	}
}

