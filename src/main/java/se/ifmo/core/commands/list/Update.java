package se.ifmo.core.commands.list;

import se.ifmo.core.collection.objects.HumanBeing;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class Update extends Command {
    public Update() {
        super("update", "{id} {element} - обновить значение элемента коллекции, id которого равен заданному");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        if (args.getText() == null || args.getText().isEmpty() || args.getText().isBlank())
            return new ExecutionResult("введите id элемента для обновления");
        if (!args.getText().matches("\\d+"))
            return new ExecutionResult("id должен быть числом");
        if (args.getResource().isEmpty())
            return new ExecutionResult("коллекция пуста");

        long id = Long.parseLong(args.getText());
        HumanBeing input = args.getResource().values().iterator().next();

        if (!args.getResource().containsKey(id))
            return new ExecutionResult("элемент с таким id отсутствует");

        args.getResource().put(id, input);

        return new ExecutionResult("элемент успешно обновлен");
    }
}
