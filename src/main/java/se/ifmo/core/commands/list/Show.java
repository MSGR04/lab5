package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class Show extends Command {
    public Show() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        return new ExecutionResult("все элементы коллекции", ResourceController.getInstance().resource());
    }
}
