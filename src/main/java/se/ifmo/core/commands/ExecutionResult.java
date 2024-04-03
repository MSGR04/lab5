package se.ifmo.core.commands;

import se.ifmo.core.collection.Resource;

public class ExecutionResult { //класс результата выполнения
    private String text = "";
    private Resource resource = new Resource();

    public ExecutionResult(String text) {
        this.text = text;
    }

    public ExecutionResult(Resource resource) {
        this.resource = resource;
    }

    public ExecutionResult(String text, Resource resource) {
        this.text = text;
        this.resource = resource;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
