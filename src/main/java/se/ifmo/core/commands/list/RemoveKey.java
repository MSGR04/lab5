package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class RemoveKey extends Command {
    public RemoveKey() {
        super("remove_key", "{id} - удалить элемент из коллекции по его ключу");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        if (args.getText() == null || args.getText().isEmpty() || args.getText().isBlank())
            return new ExecutionResult("введите id элемента для удаления");
        if (!args.getText().matches("\\d+"))
            return new ExecutionResult("id должен быть числом");

        long id = Long.parseLong(args.getText());

        return new ExecutionResult(ResourceController.getInstance().resource().remove(id) != null ?
                "элемент успешно удален" :
                "элемент с таким id отсутствует");
    }
}
