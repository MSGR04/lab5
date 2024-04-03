package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.collection.objects.HumanBeing;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class ReplaceIfGreater extends Command {
    public ReplaceIfGreater() {
        super("replace_if_greater", "{id} {element} - заменить значение по ключу, если новое значение больше старого");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        if (args.getResource().isEmpty())
            return new ExecutionResult("коллекция пуста");
        if (args.getText() == null || args.getText().isBlank() || args.getText().isEmpty())
            return new ExecutionResult("введите id элемента для замены");
        if (!args.getText().matches("\\d+"))
            return new ExecutionResult("id должен быть числом");

        long id = Long.parseLong(args.getText());
        HumanBeing selected = args.getResource().values().iterator().next();

        if (!ResourceController.getInstance().resource().containsKey(id))
            return new ExecutionResult("элемент с таким id отсутствует");

        if (ResourceController.getInstance().resource().get(id).compareTo(selected) < 0) {
            ResourceController.getInstance().resource().put(id, selected);
            return new ExecutionResult("элемент успешно заменен");
        } else return new ExecutionResult("новое значение не больше старого");
    }
}
