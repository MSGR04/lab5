package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.collection.objects.HumanBeing;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

import java.util.stream.Collectors;

public class PrintFieldAscendingMinutesOfWaiting extends Command {
    public PrintFieldAscendingMinutesOfWaiting() {
        super("print_field_ascending_minutes_of_waiting", "вывести значения поля minutesOfWaiting всех элементов в порядке возрастания");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        return new ExecutionResult(ResourceController.getInstance().resource().values().stream()
                .map(HumanBeing::getMinutesOfWaiting)
                .sorted()
                .map(minutesOfWaiting -> "\n" + minutesOfWaiting)
                .collect(Collectors.joining()));
    }
}
