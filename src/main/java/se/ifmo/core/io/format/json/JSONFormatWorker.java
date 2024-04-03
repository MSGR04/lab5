package se.ifmo.core.io.format.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import se.ifmo.core.collection.Resource;
import se.ifmo.core.collection.objects.Root;
import se.ifmo.core.io.format.FormatWorker;
import se.ifmo.core.io.format.json.adapters.IgnoreFailureTypeAdapterFactory;
import se.ifmo.core.io.format.json.adapters.ResourceDeserializer;
import se.ifmo.core.io.IOWorker;
import se.ifmo.core.io.file.BufferedFileWorker;

import java.nio.file.Files;
import java.nio.file.Path;

public class JSONFormatWorker implements FormatWorker<Resource> {
    private static final Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapterFactory(new IgnoreFailureTypeAdapterFactory())
                .registerTypeAdapter(Resource.class, new ResourceDeserializer())
                .create();

    @Override
    public Resource read(Path filePath) { // Читает файл по указанному пути и преобразовывает его содержимое в Resourse
        // check if file exists
        if (Files.notExists(filePath)) {
            System.err.printf("Файл %s не найден%n", filePath.getFileName());
            return new Resource();
        }

        // check if file is readable
        if (!Files.isReadable(filePath)) {
            System.err.printf("Файл %s не доступен для чтения%n", filePath.getFileName());
            return new Resource();
        }

        // parse file
        try (IOWorker fileWorker = new BufferedFileWorker(filePath)) {
            Root root = gson.fromJson(fileWorker.read(), Root.class);

            if (root == null || root.getResource() == null) {
                System.out.println("кажется, введенный файл пустой. будет инициализирована пустая коллекция.");
                return new Resource();
            }

            System.out.println("коллекция успешно проинициализирована! размер: " + root.getResource().size());
            return root.getResource();
        } catch (Exception e) {
            System.err.printf("Ошибка обработки файла %s%n%s%n", filePath.getFileName(), e.getMessage());
            return new Resource();
        }
    }

    @Override
    public void write(Resource values, Path filePath) {
        // check if file exists
        if (Files.notExists(filePath)) {
            System.err.printf("Файл %s не найден%n", filePath.getFileName());
            return;
        }

        // check if file is writable
        if (!Files.isWritable(filePath)) {
            System.err.printf("Файл %s не доступен для чтения%n", filePath.getFileName());
            return;
        }

        // write to file
        try (IOWorker fileWorker = new BufferedFileWorker(filePath, false)) {
            fileWorker.write(gson.toJson(new Root(values)));
        } catch (Exception e) {
            System.err.printf("Ошибка записи в файл %s: %s%n", filePath.getFileName(), e.getMessage());
            return;
        }
    }
}

