package se.ifmo.core.commands.list;

import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;
import se.ifmo.chat.handler.Handler;

public class History extends Command {
    public History() {
        super("history", "вывести последние 6 команд (без их аргументов)");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        return new ExecutionResult(Handler.HISTORY.stream()
                .limit(6)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("история пуста"));
    }
}
