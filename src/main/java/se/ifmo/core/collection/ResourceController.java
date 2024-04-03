package se.ifmo.core.collection;

import se.ifmo.core.io.file.FileController;

public class ResourceController {
    private Resource resource = null;

    private ResourceController() {}

    private static ResourceController instance = null;

    public static ResourceController getInstance() {
        if (instance == null) instance = new ResourceController();
        return instance;
    }

    public Resource load() {
        resource = FileController.loadResource();
        System.out.printf("Загружено из файла %d записей%n", resource.size());
        return resource;
    }

    public void save() {
        FileController.save(resource);
    }

    public Resource resource() {
        if (resource == null) load();
        return resource;
    }
}
