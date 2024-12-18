package br.com.senior.service.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.util.UtilsProperties;

@Component
public class SocketMessageCommunicator implements SocketCommunicator {
    
    private static final Logger logger = LoggerFactory.getLogger("SocketMessageCommunicator");
    
    private String host;
    
    private int port;
    
    private final UtilsProperties utilsProperties = new UtilsProperties();
    
    private SocketChannel socketChannel;
    
    private final AtomicBoolean isConnected = new AtomicBoolean(false);
    
    public void connectSocket() throws IOException, InterruptedException {
        host = utilsProperties.getHost();
        port = utilsProperties.getPort();
        if (host == null || host.isEmpty()) {
            host = "172.16.14.7";
        }
        if (port == 0) {
            port = 61000;
        }
        logger.info("Host: {} - Port {}", host, port);
        connect(host, port);
    }
    
    @Override
    public void connect(String host, int port) throws IOException, InterruptedException {
        if (host == null) {
            throw new NullPointerException("Host cannot be null.");
        } else if (port < 0) {
            throw new IllegalArgumentException("Port cannot be negative.");
        }
        
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(host, port));
            
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            
            while (!socketChannel.finishConnect()) {
                Thread.sleep(10L);
            }
            
            isConnected.set(true);
            logger.info("Connected to {}:{}", host, port);
        } catch (IOException e) {
            isConnected.set(false);
            logger.warn("Error connecting to host {}:{}. Will retry.", host, port);
            throw e;
        }
    }
    
    @Override
    public void disconnect() throws IOException {
        try {
            if (socketChannel != null && socketChannel.isConnected()) {
                socketChannel.close();
            }
            isConnected.set(false);
            logger.info("Disconnected from socket.");
        } catch (IOException e) {
            logger.error("Error while disconnecting.", e);
            throw e;
        }
    }
    
    @Override
    public void write(ByteBuffer buffer, long timeoutMillis) throws IOException, InterruptedException {
        if (!isConnected()) {
            reconnectIfNeeded();
            logger.warn("Socket disconnected. Message will be queued.");
            throw new IllegalStateException("Socket is not connected.");
        }
        
        long start = System.currentTimeMillis();
        long now;
        
        do {
            int bytes = socketChannel.write(buffer);
            if (bytes < 0) {
                throw new IOException("Output channel was closed unexpectedly.");
            }
            if (!buffer.hasRemaining()) {
                return;
            }
            now = System.currentTimeMillis();
        } while (now - start <= timeoutMillis);
        
        throw new SocketTimeoutException("Write timeout exceeded.");
    }
    
    private void reconnectIfNeeded() {
        if (!isConnected()) {
            try {
                connectSocket();
            } catch (IOException | InterruptedException e) {
                logger.error("Reconnection attempt failed. Retrying...");
            }
        }
    }
    
    @Override
    public boolean isConnected() {
        return isConnected(socketChannel) && isConnected.get();
    }
    
    @Override
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
    
    private static boolean isConnected(SocketChannel channel) {
        return channel != null && channel.isOpen() && channel.isConnected();
    }
    
}
