package se.ifmo.core.io.console;

import se.ifmo.core.io.IOWorker;

import java.io.*;

public class BufferedConsoleWorker implements IOWorker {
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public BufferedConsoleWorker() {
        this.reader = new BufferedReader(new InputStreamReader(System.in)); //инициализируем поток на чтение
        this.writer = new BufferedWriter(new OutputStreamWriter(System.out));// и на запись
    }

    @Override
    public String read() { //чтение из консоли
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    @Override //запись из консоли
    public void write(String message) {
        try {
            writer.append(message).append("\n").flush();
        } catch (IOException ignored) {}
    }

    @Override //запись без переносов
    public void writeWithoutNewLine(String message) {
        try {
            writer.append(message).flush();
        } catch (IOException ignored) {}
    }

    @Override //закрываю потоки
    public void shutdownGracefully() throws IOException {
        this.reader.close();
        this.writer.close();
    }
}
