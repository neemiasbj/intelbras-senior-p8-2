package br.com.senior.service.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface SocketCommunicator {
    
    void connectSocket() throws IOException, InterruptedException;
    
    void connect(String host, int port) throws IOException, InterruptedException;
    
    void disconnect() throws IOException;
    
    void write(ByteBuffer buffer, long timeoutMillis) throws IOException, InterruptedException;
    
    boolean isConnected();
    
    SocketChannel getSocketChannel();
    
}
