package br.com.senior.service.queue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.domain.senior.abstration.HeaderMessage;

@Service
public class MessageQueue {
    
    private final Map<Byte, HeaderMessage> messageQueue;
    
    private static final Logger logger = LoggerFactory.getLogger("MessageQueue");
    
    public MessageQueue() {
        this.messageQueue = new HashMap<>();
    }
    
    public void addItem(HeaderMessage item) {
        logger.debug("Message to send for queue: {}", item);
        messageQueue.put(item.getMessageNumber(), item);
    }
    
    public Optional<HeaderMessage> pollItem(Byte key) {
        return Optional.ofNullable(messageQueue.remove(key));
    }
    
    public Optional<HeaderMessage> peekItem(Byte key) {
        return Optional.ofNullable(messageQueue.get(key));
    }
    
    public List<HeaderMessage> getAllItems() {
        return messageQueue.values().stream().toList();
    }
    
    public Optional<HeaderMessage> getItem(HeaderMessage item) {
        return messageQueue.values().stream().filter(i -> i.equals(item)).findFirst();
    }
    
    public boolean removeItem(HeaderMessage item) {
        Optional<Byte> keyToRemove = messageQueue.entrySet().stream().filter(entry -> entry.getValue().equals(item)).map(Map.Entry::getKey)
                                                 .findFirst();
        keyToRemove.ifPresent(messageQueue::remove);
        return keyToRemove.isPresent();
    }
    
    public boolean isEmpty() {
        return messageQueue.isEmpty();
    }
    
    public int size() {
        return messageQueue.size();
    }
    
}
