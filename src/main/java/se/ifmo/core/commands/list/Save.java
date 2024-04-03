package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class Save extends Command {
    public Save() {
        super("save", "сохранить коллекцию в файл");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        ResourceController.getInstance().save();
        return new ExecutionResult("коллекция успешно сохранена");
    }
}
