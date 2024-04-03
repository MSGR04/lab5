package se.ifmo.chat.runner;

public interface Runner extends AutoCloseable {
    void start();

    void stop();
}
