package br.com.domain.senior.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TcpIpMessageHeaderInterpreter {
    
    private Integer messageLength;
    
    private byte messageNumber;
    
    private byte messageType;
    
    private byte messageId;
    
    private short headerLength;
    
    // Construtor que interpreta o cabeçalho da mensagem
    public TcpIpMessageHeaderInterpreter(byte[] header) {
        interpretHeader(header);
    }
    
    // Método para interpretar o cabeçalho
    private void interpretHeader(byte[] header) {
        if (header.length < 9) {
            throw new IllegalArgumentException("Header must be at least 9 bytes long");
        }
        
        ByteBuffer buffer = ByteBuffer.wrap(header);
        buffer.order(ByteOrder.BIG_ENDIAN); // Define a ordem dos bytes como Big Endian
        
        // Extração dos valores do cabeçalho
        this.messageLength = buffer.getInt(); // 4 bytes
        this.messageNumber = buffer.get(); // 1 byte
        this.messageType = buffer.get(); // 1 byte
        this.messageId = buffer.get(); // 1 byte
        this.headerLength = buffer.getShort(); // 2 bytes
    }
    
    // Métodos para acessar os valores extraídos
    public int getMessageLength() {
        return messageLength;
    }
    
    public byte getMessageNumber() {
        return messageNumber;
    }
    
    public byte getMessageType() {
        return messageType;
    }
    
    public byte getMessageId() {
        return messageId;
    }
    
    public short getHeaderLength() {
        return headerLength;
    }
    
    @Override
    public String toString() {
        return "TcpIpMessageHeaderInterpreter{" + "messageLength=" + messageLength + ", messageNumber=" + messageNumber + ", messageType="
               + messageType + ", messageId=" + messageId + ", headerLength=" + headerLength + '}';
    }
    
}
