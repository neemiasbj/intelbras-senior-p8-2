package br.com.domain.senior.model;

import java.util.function.Consumer;

public class MessageTask implements Runnable {
    
    private final Runnable task;
    
    private final Consumer<Exception> failureHandler;
    
    public MessageTask(Runnable task, Consumer<Exception> failureHandler) {
        this.task = task;
        this.failureHandler = failureHandler;
    }
    
    @Override
    public void run() {
        task.run();
    }
    
    public void onFailure(Exception e) {
        failureHandler.accept(e);
    }
    
}
