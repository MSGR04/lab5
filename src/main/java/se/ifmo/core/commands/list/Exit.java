package se.ifmo.core.commands.list;

import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

public class Exit extends Command {
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        System.exit(1);
        return null;
    }
}
