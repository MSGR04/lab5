package se.ifmo.core.io.file;

import se.ifmo.core.io.IOWorker;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedFileWorker implements IOWorker {
    private final BufferedReader reader; //поток на чтение
    private final BufferedWriter writer;//на запись

    public BufferedFileWorker(Path path, boolean append) throws IOException {
        if (Files.notExists(path)) throw new FileNotFoundException();

        if (!Files.isReadable(path)) System.err.println("[WARN] can't read from file" + path);
        if (!Files.isWritable(path)) System.err.println("[WARN] can't write to file" + path);

        this.reader = new BufferedReader(new FileReader(path.toFile())); //инициализируем потоки ввода и вывода
        this.writer = new BufferedWriter(new FileWriter(path.toFile(), append));
    }

    public BufferedFileWorker(Path path) throws IOException {
        this(path, true);
    }

    @Override
    public String read() { //чтение из файла
        try {
            StringBuilder data = new StringBuilder();
            while (reader.ready())
                data.append(reader.readLine()).append("\n");
            return data.toString();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override //запись в файл
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

    @Override //закрываем потоки
    public void shutdownGracefully() throws Exception {
        reader.close();
        writer.close();
    }
}
