package se.ifmo.core.commands;

import se.ifmo.core.collection.Resource;

public class ExecutionArguments { //класс для хранения элементов необходимых для исполнения команды
    private String text = ""; //текстовые аргументы
    private Resource resource = new Resource();//набор элементов, который мы передаём в команду

    public ExecutionArguments() {}

    public ExecutionArguments(String text, Resource resource) {//инициализация
        this.text = text;
        this.resource = resource;
    }

    public ExecutionArguments(String text) {
        this.text = text;
    }
    //геттеры и сеттеры
    public String getText() {
        return text;
    }

    public Resource getResource() {
        return resource;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
