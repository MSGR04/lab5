package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class Clear extends Command {
    public Clear() {
        super("clear", "очистить коллекцию");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        ResourceController.getInstance().resource().clear();
        return new ExecutionResult("коллекция успешно очищена!");
    }
}
