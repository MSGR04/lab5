package se.ifmo.core.commands;

public abstract class Command {
    private final String name;
    private final String help;
    private final int requiredResources; //кол-во элементов для исполнения

    public Command(String name, String help) {
        this.name = name;
        this.help = help;
        this.requiredResources = 0;
    }

    public Command(String name, String help, int requiredResources) {
        this.name = name;
        this.help = help;
        this.requiredResources = requiredResources;
    }

    public String getName() {
        return name;
    } //геттеры сеттеры

    public String getHelp() {
        return help;
    }

    public int getRequiredResources() {
        return requiredResources;
    }

    public abstract ExecutionResult execute(ExecutionArguments args);
}
