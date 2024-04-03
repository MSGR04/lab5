package se.ifmo.core.io.file;

import se.ifmo.Application;
import se.ifmo.core.collection.Resource;
import se.ifmo.core.io.format.json.JSONFormatWorker;

public class FileController {
    public static Resource loadResource() { // проверка на наличие пути до файла для чтения
        if (Application.getFilePath() == null) return new Resource();
        return new JSONFormatWorker().read(Application.getFilePath());
    }

    public static void save(Resource resource) { //для записи
        if (Application.getFilePath() == null) return;
        new JSONFormatWorker().write(resource, Application.getFilePath());
    }
}
