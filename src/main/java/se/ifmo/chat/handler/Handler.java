package se.ifmo.chat.handler;

import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class Handler {
    public static final Deque<String> HISTORY = new ArrayDeque<>();

    private final HandlerConfiguration configuration;

    public Handler(HandlerConfiguration configuration) {
        this.configuration = configuration;
    }

    public ExecutionResult execute(String command, ExecutionArguments arguments) {
        Handler.HISTORY.add(command);

        if (command == null || command.isBlank() || command.isEmpty()) return new ExecutionResult("команда не может быть пустой");
        if ("help".equalsIgnoreCase(command)) return new ExecutionResult(getHelp());//отвечает за то, что при вводе help, выведутся все команды

        return configuration.commands().stream()//список команд из конфигурации преобразуют в поток
                .filter(temp -> temp.getName().equalsIgnoreCase(command))//Фильтрует поток команд, оставляя только те, имя которых (без учета регистра) совпадает с введенной командой.
                .findFirst()//возвращает первую команду из потока
                .map(temp -> temp.execute(arguments))
                .orElse(new ExecutionResult(String.format("команда '%s' не найдена, введите 'help' для справки", command)));
    }

    public int getRequiredElementsForCommand(String command) {
        return configuration.commands().stream()
                .filter(temp -> temp.getName().equalsIgnoreCase(command))
                .findFirst()
                .map(Command::getRequiredResources)
                .orElse(0);
    }

    public String getHelp() {
        return "Все доступные команды:" + configuration.commands().stream()
                .map(command -> String.format("%n%s - %s", command.getName(), command.getHelp()))
                .collect(Collectors.joining());
    }
}
