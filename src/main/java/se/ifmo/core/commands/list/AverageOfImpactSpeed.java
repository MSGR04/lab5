package se.ifmo.core.commands.list;

import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.collection.objects.HumanBeing;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class AverageOfImpactSpeed extends Command {
    public AverageOfImpactSpeed() {
        super("average_of_impact_speed", "вывести среднее значение поля impactSpeed для всех элементов коллекции");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        return new ExecutionResult("среднее значение поля impactSpeed: " +
                ResourceController.getInstance().resource().values().stream()
                        .mapToLong(HumanBeing::getImpactSpeed)
                        .average().orElse(0));
    }
}
