package se.ifmo.core.commands.list;

import se.ifmo.core.collection.Resource;
import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class MaxByRealHero extends Command {
    public MaxByRealHero() {
        super("max_by_real_hero", "вывести любой объект из коллекции, значение поля realHero которого является максимальным");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        return ResourceController.getInstance().resource().values().stream().max((a, b) -> Boolean.compare(a.isRealHero(), b.isRealHero()))
                .map(humanBeing -> new ExecutionResult(new Resource(humanBeing)))
                .orElse(new ExecutionResult("коллекция пуста"));
    }
}
