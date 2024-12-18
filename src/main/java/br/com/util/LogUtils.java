package br.com.util;

import org.slf4j.Logger;

import br.com.domain.senior.abstration.HeaderMessage;

public class LogUtils {

	public static void logInfoMessageSendBytesToHexFormatt(byte[] message, Logger logger) {
		String hexMessage = Utils.bytesToHexFormatt(message);
		logger.info("|<-- Mensagem Enviada (Hexadecimal): " + hexMessage);
	}

	public static void logInfoMessageReceiveBytesToHexFormatt(byte[] message, Logger logger) {
		String hexMessage = Utils.bytesToHexFormatt(message);
		String ascllMessage = Utils.convertByteArrayToASCIIString(message);
		logger.info("|--> Mensagem Recebida (Hexadecimal): {}", hexMessage);
		logger.info("|--> Mensagem Recebida (ASCII): {}", ascllMessage);
	}

	public static void logInfoInterpreterMessage(HeaderMessage message, Logger logger) {
		logger.info("|--> Mensagem Recebida: {}", message);
	}

}
