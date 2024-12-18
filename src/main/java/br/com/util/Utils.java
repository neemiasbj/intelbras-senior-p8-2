package br.com.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class Utils {

	public static DateFormat dfWebhookFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static DateFormat formatHHmmDDMM = new SimpleDateFormat("HHmmddMM");
	public static Gson gson = new Gson();

	public static final Boolean TRUE = true;
	public static final Boolean FALSE = false;
	public static final byte PROTOCOL_ERROR = 1;
	public static final String ERROR_MSG_NOT_IMPLEMENTED = "Mensagem não implementada!";
	public static final String ERROR_MSG_NULL_BUFFER = "Buffer não pode ser nulo. MessageDecodeFactory";
	public static final DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	private static final Pattern IP_PATTERN = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");

	public static String addGmtToDateTime(String dataString, Integer gmt) {
		ZoneOffset offsetGMT = ZoneOffset.ofHours(gmt / 60);

		OffsetDateTime offsetDateTime = OffsetDateTime.parse(dataString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		OffsetDateTime offsetDateTimeGMT = offsetDateTime.withOffsetSameInstant(offsetGMT);

		return offsetDateTimeGMT.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
	}

	public static String removeGmtToDateTime(String horarioComGMT, Integer gmtEmMinutos) {
		LocalDateTime dataAtual = LocalDateTime.parse(horarioComGMT, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		LocalDateTime dataSemOffset = dataAtual.minusMinutes(gmtEmMinutos);

		return dataSemOffset.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + ".000Z";
	}

	public static Date removeGmtToDateTimefromDate(String horarioComGMT, Integer gmtEmMinutos) {
		LocalDateTime dataAtual = LocalDateTime.parse(horarioComGMT, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		LocalDateTime dataSemOffset = dataAtual.minusMinutes(gmtEmMinutos);
		ZonedDateTime zonedDateTime = dataSemOffset.atZone(ZoneOffset.UTC);

		return Date.from(zonedDateTime.toInstant());
	}

	public static String getStringIpFromInteger(int intIp) {
		if (intIp == 0) {
			return "0.0.0.0";
		} else {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < 4; ++i) {
				int isolate = intIp << i * 8 >> 24 & 255;
				sb.append(isolate);
				if (i < 3) {
					sb.append(".");
				}
			}

			return sb.toString();
		}
	}

	public static String getString(ByteBuffer buf, int size) {
		byte[] valueInArray = new byte[size];
		buf.get(valueInArray);
		return new String(valueInArray, StandardCharsets.ISO_8859_1);
	}

	public static String getDescription(ByteBuffer buffer) {
		byte descSize = buffer.get();
		return getString(buffer, descSize);
	}

	public static String formatDateToWebhookPattern(LocalDateTime date) {
		return CUSTOM_FORMATTER.format(date);
	}

	public static Long convertDateToSeconds(Date date) {
		return date.getTime() / 1000;
	}

	public static Long convertLocalDateTimeToSeconds(LocalDateTime dateTime) {
		return dateTime.toEpochSecond(ZoneOffset.UTC);
	}

	public static String convertBytenText(byte[] message) {
		return new String(message);
	}

	public static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(2 * bytes.length);

		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString.toString();
	}

	public static String bytesToHexFormatt(byte[] bytes) {
		StringBuilder hexString = new StringBuilder(3 * bytes.length);

		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xff & bytes[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);

			if (i < bytes.length - 1) {
				hexString.append(' ');
			}
		}

		return hexString.toString();
	}

	public static String hexToString(String hex) {
		StringBuilder output = new StringBuilder();

		for (int i = 0; i < hex.length(); i += 2) {
			String pair = hex.substring(i, i + 2);
			int decimal = Integer.parseInt(pair, 16);
			output.append((char) decimal);
		}

		return output.toString();
	}

	public static List<Byte> convertPairStringSizedToBCD(String bcdString) {
		ArrayList<Byte> binBcd = new ArrayList<>(bcdString.length() / 2);
		for (int i = 0; i < binBcd.size(); i++) {
			String sByte = bcdString.substring(i * 2, i * 2 + 2);
			binBcd.add(Byte.parseByte(sByte, 16));
		}
		return binBcd;
	}

	public static byte convertDecimalStringToBCD(String decimalString) {
		return (byte) Integer.parseInt(decimalString.substring(0, 2), 16);
	}

	public static Byte convertByteToByteBCD(byte b) {
		return ((byte) (((b / 10) * 16) + (b % 10)));
	}

	public static String convertByteArrayToASCIIString(byte[] message) {
		return new String(message, StandardCharsets.US_ASCII);
	}

	public static byte[] convertByteArrayToBCD(byte[] byteArray, int offSetDestino, int offSetOrigem, int qtdeDestino) {
		int i;
		byte k, x, z;
		byte[] destino = new byte[byteArray.length / 2];

		for (i = 0; i < qtdeDestino; i++) {
			k = (byte) (convertASCIIToHex(byteArray[i * 2]) * 16);
			x = (convertASCIIToHex(byteArray[(i * 2) + 1]));
			z = (byte) (k + x);
			destino[i] = z;
		}

		return destino;
	}

	private static byte convertASCIIToHex(byte arg) {
		if ((arg >= 0x30) && (arg <= 0x39))
			return (byte) (arg - 0x30);
		if ((arg >= 0x41) && (arg <= 0x46))
			return (byte) (arg - 0x37);
		return (0);
	}

	public static byte byteToBCD(byte arg1, byte arg2) {
		return ((byte) ((((arg1) - 0x30) * 16) + (((arg2) - 0x30))));
	}

	/**
	 * Decodes an unsigned packed BCD byte to its integer number wrapped in a
	 * {@code String}.
	 *
	 * @param bcd the BCD byte to decode.
	 * @return the decoded integer number wrapped inside a {@code String}.
	 */
	public static String convertBCDToString(byte bcd) {
		StringBuilder sb = new StringBuilder();

		byte high = (byte) (bcd & 0xf0);
		high >>>= (byte) 4;
		high = (byte) (high & 0x0f);
		byte low = (byte) (bcd & 0x0f);

		sb.append(high);
		sb.append(low);

		return sb.toString();
	}

	/**
	 * Decodes an unsigned packed BCD byte to its integer number
	 *
	 * @param bcd the BCD byte to decode.
	 * @return the decoded integer number wrapped inside a {@code Integer}.
	 */
	public static Integer convertBCDToInteger(byte bcd) {
		return Integer.valueOf(convertBCDToString(bcd));
	}

	/**
	 * Encodes a positive integer number into an unsigned packed BCD.
	 *
	 * @param num a positive integer number to encode (maximum value of
	 *            2<sup>63</sup>-1).
	 * @return BCD representation of the passed number argument.
	 * @throws IllegalArgumentException if the passed num argument is negative.
	 */
	public static byte[] convertDecimalToBCD(long num) {
		if (num < 0)
			throw new IllegalArgumentException("The method decimalToBcd doesn't support negative numbers." + " Invalid argument: " + num);

		int digits = 0;

		long temp = num;
		while (temp != 0) {
			digits++;
			temp /= 10;
		}

		int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;

		byte[] bcd = new byte[byteLen];

		for (int i = 0; i < digits; i++) {
			byte tmp = (byte) (num % 10);

			if (i % 2 == 0) {
				bcd[i / 2] = tmp;
			} else {
				bcd[i / 2] |= (byte) (tmp << 4);
			}

			num /= 10;
		}

		for (int i = 0; i < byteLen / 2; i++) {
			byte tmp = bcd[i];
			bcd[i] = bcd[byteLen - i - 1];
			bcd[byteLen - i - 1] = tmp;
		}

		return bcd;
	}

	public static String convertByteArrayToString(byte[] byteArray, char separator) {
		String hexString = "";

		// Iterating through each byte in the array
		for (byte i : byteArray)
			hexString += String.format("%02X%1", i) + separator;

		return hexString;
	}

	public static String convertByteArrayToString(byte[] byteArray) {
		String hexString = "";

		// Iterating through each byte in the array
		for (byte i : byteArray)
			hexString += String.format("%02X", i);

		return hexString;
	}

	public static String convertByteToBitString(byte b) {
		return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
	}

	public static String convertByteArrayToBitString(byte[] bytes) {
		String strBits = "";

		for (byte bt : bytes)
			strBits += String.format("%8s", convertByteToBitString(bt));

		return strBits;
	}

	public static Integer convertByteToIntBCD(byte b) {
		assert 0 <= b && b <= 99; // two digits only.
		return (b / 10 << 4) | b % 10;
	}

	public static byte[] convertByteListToByteArray(List<Byte> bytesList) {
		byte[] bytes = new byte[bytesList.size()];
		for (int i = 0; i < bytesList.size(); i++)
			bytes[i] = bytesList.get(i);

		return bytes;
	}

	public static String padStart(String stringToFill, int sizeToFill, char charToFill) {
		if (stringToFill.length() >= sizeToFill)
			return stringToFill;

		String temp = stringToFill;
		for (int i = stringToFill.length(); i < sizeToFill; i++)
			temp = charToFill + temp;

		return temp;
	}

	public static String padEnd(String stringToFill, int sizeToFill, char charToFill) {
		if (stringToFill.length() >= sizeToFill)
			return stringToFill;

		String temp = stringToFill;
		for (int i = stringToFill.length(); i < sizeToFill; i++)
			temp += charToFill;

		return temp;
	}

	public static byte[] convertHexStringToByteArray(String s) {
		int it = Integer.parseInt(s, 16);
		System.out.println("Hexadecimal String " + s);
		BigInteger bigInt = BigInteger.valueOf(it);
		return (bigInt.toByteArray());
	}

	public static byte convertHexToByte(String hex) {
		int decimal = Integer.parseInt(hex, 16);
		return (byte) decimal;
	}

	public static String convertByteArrayToHexString(byte[] byteArray) {
		return byteArray == null ? null : String.join(",", splitStringIntoXStringSize(new BigInteger(1, byteArray).toString(16).toUpperCase(), 2));
	}

	public static String convertByteArrayToHexString(byte[] byteArray, String strToJoin) {
		if (byteArray == null)
			return null;
		return String.join(strToJoin == null ? "" : strToJoin, splitStringIntoXStringSize(new BigInteger(1, byteArray).toString(16).toUpperCase(), 2));
	}

	public static String formatIpAdressRemoveZeros(String ip) {
		return ip.replaceAll("0+(\\d)", "$1").replaceAll("\\.0+(\\d)", ".$1");
	}

	public static short computeAdditionChecksum(byte[] data) {
		short sum = 0;
		{
			for (byte b : data) {
				sum += b;
			}
		}
		return sum;
	}

	public static byte[] getComputedChecksumTwoBytesFromIntSum(int sum) {
		byte[] checksum = new byte[2];
		checksum[0] = ((byte) ((sum) & 0xFF));
		checksum[0] = ((byte) ((sum >> 8) & 0xFF));
		return checksum;
	}

	public static String formatIpAdressAddZeros(String ip) {
		String[] nums = ip.split(".");
		for (int i = 0; i < nums.length; i++)
			nums[i] = padStart(nums[i], 3, '0');

		return String.join(".", nums);
	}

	public static int calculateBufferChecksum(List<Byte> buffer) {
		int checkSum = 0;
		for (byte b : buffer)
			checkSum += (b & 0xFF);
		return checkSum;
	}

	public static int calculateBufferChecksum(byte[] buffer) {
		int checkSum = 0;
		for (byte b : buffer)
			checkSum += (b & 0xFF);
		return checkSum;
	}

	public static String formatDateToWebhookPattern(Date date) {
		return dfWebhookFormat.format(date);
	}

	public static String getFormatedDateHHmmDDMM(Date date) {
		return formatHHmmDDMM.format(date);
	}

	public static String getFormatedDateHHmmDDMM() {
		return formatHHmmDDMM.format(new Date());
	}

	public static String[] splitStringIntoXStringSize(String str, Integer size) {
		return str.split(String.format("(?<=\\G.{%s})", size));
	}

	public static byte convertBitStringToByte(String bit) {
		return Byte.parseByte(bit, 2);
	}

	public static int convertBitStringToInt(String binaryString) {
		return Integer.parseInt(binaryString, 2);
	}

	public static String classToJson(Object obj) {
		return gson.toJson(obj);
	}

	public static String manageCodinMessageIpAddress(String ip) {
		List<String> ranges = Arrays.asList(ip.split("\\."));

		for (int i = 0; i < ranges.size(); i++)
			ranges.set(i, ranges.get(i).replaceFirst("^0+(?!$)", ""));

		return String.join(".", ranges);
	}

	public static String leftPad(String texto, Integer tamanho, Character caracter) {
		if (texto.length() < tamanho) {
			StringBuilder sb = new StringBuilder(texto);
			for (int cont = 0; cont < (tamanho - texto.length()); cont++) {
				sb.insert(0, caracter);
			}
			return sb.toString();
		}
		return texto;
	}

	public static String formatarCPF(String cpf, Integer count, Character caracter) {
		String cpfCompleto = leftPad(cpf, 11, '0');
		return cpfCompleto.substring(0, 3) + "." + cpfCompleto.substring(3, 6) + "." + cpfCompleto.substring(6, 9) + "-" + cpfCompleto.substring(9, 11);
	}

	public static String completePersonPis(String pis) {
		return pis == null ? "000000000000" : pis.replace(" ", "0");
	}

	/**
	 * @param ipAddress String do endereço IP.
	 * @return IP formatado ou uma mensagem de erro.
	 */
	public static String formatIPAddress(String ipAddress) {
		if (isValidIP(ipAddress)) {
			// Formata o IP para adicionar zeros onde necessário
			String[] parts = ipAddress.split("\\.");
			return String.format("%03d.%03d.%03d.%03d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
		} else {
			throw new IllegalArgumentException("Endereço IP inválido: " + ipAddress);
		}
	}

	/**
	 * @param ipAddress String do endereço IP.
	 * @return true se o IP for válido, false caso contrário.
	 */
	private static boolean isValidIP(String ipAddress) {
		Matcher matcher = IP_PATTERN.matcher(ipAddress);
		return matcher.matches();
	}
}
