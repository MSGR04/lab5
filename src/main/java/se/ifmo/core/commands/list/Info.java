package se.ifmo.core.commands.list;

import se.ifmo.core.collection.Resource;
import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class Info extends Command {
    public Info() {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        Resource resource = ResourceController.getInstance().resource();

        return new ExecutionResult(String.format("тип коллекции: %s\nколичество элементов: %d\nдата инициализации: %s",
                resource.getClass().getSimpleName(), resource.size(), resource.getInitDate()));
    }
}
