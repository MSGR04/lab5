package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.collection.objects.HumanBeing;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class RemoveLower extends Command {
    public RemoveLower() {
        super("remove_lower", "{element} - удалить из коллекции все элементы, меньшие, чем заданный");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        if (args.getResource().isEmpty())
            return new ExecutionResult("коллекция пуста");

        HumanBeing selected = args.getResource().values().iterator().next();

        ResourceController.getInstance().resource().entrySet()
                .stream().filter(temp -> temp.getValue().compareTo(selected) < 0)
                .forEach(temp -> ResourceController.getInstance().resource().remove(temp.getKey()));

        return new ExecutionResult("все элементы, меньшие, чем заданный, успешно удалены");
    }
}
