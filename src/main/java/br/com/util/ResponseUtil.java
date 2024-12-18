package br.com.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

	public static ResponseEntity<Object> resposeSuccess(Object object) {
		if (object == null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(object, HttpStatus.OK);
	}

	public static ResponseEntity<Object> resposeNotContent() {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	public static ResponseEntity<Object> resposeBadRequest(Object object) {
		return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<Object> resposeNotFound() {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public static ResponseEntity<Object> resposeInternalError(Object object) {
		if (object == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(object, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public static ResponseEntity<Object> resposeGeneric(Object object, HttpStatus status) {
		return new ResponseEntity<>(object, status);
	}
}
