package br.com.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UtilsProperties {

	@Value("${senior.socket.host}")
	private String host;

	@Value("${senior.socket.port}")
	private int port;

	@Value("${scheduled.keep.alive.device.second}")
	private int keepAlive;

	@Value("${time.out.await.socket.second}")
	private long timeOutAwaitMessage;

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public long getTimeOutAwaitMessage() {
		return timeOutAwaitMessage;
	}

	public int getKeepAlive() {
		return keepAlive;
	}

}
