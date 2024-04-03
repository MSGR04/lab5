package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.collection.objects.HumanBeing;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

import java.util.Optional;

public class Insert extends Command {
    public Insert() {
        super("insert", "{id} {element} - добавить новый элемент с заданным ключом", 1);
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) { //
        if (args.getText() == null || args.getText().isBlank() || args.getText().isEmpty()) //Этот код проверяет, был ли предоставлен текстовый аргумент.
            return new ExecutionResult("введите id элемента для добавления");
        if (!args.getText().matches("\\d+"))//Проверка на то, является ли текстовый аргумент числом
            return new ExecutionResult("id должен быть числом");

        Optional<HumanBeing> toInsertOptional = args.getResource().values().stream().findAny();//Этот код выбирает любой элемент из коллекции для вставки.

        if (toInsertOptional.isEmpty())
            return new ExecutionResult("отсутствует элемент для добавления");

        HumanBeing toInsert = toInsertOptional.get();
        long id = Long.parseLong(args.getText());//преобразовываем текстовый документ в число типа long

        if (ResourceController.getInstance().resource().containsKey(id)) //Этот код проверяет, существует ли уже элемент с таким ключом
            return new ExecutionResult("элемент с таким id уже существует");

        return new ExecutionResult(ResourceController.getInstance().resource().put(id, toInsert) == null ?//Этот код возвращает результат выполнения с сообщением о том, был ли элемент успешно добавлен или произошла ошибка.
                "элемент успешно добавлен" :
                "произошла ошибка при добавлении элемента");
    }
}
