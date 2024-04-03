package se.ifmo.core.commands.list;

import se.ifmo.chat.runner.Runner;
import se.ifmo.core.commands.Command;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;
import se.ifmo.core.io.IOWorker;
import se.ifmo.core.io.file.BufferedFileWorker;
import se.ifmo.chat.runner.QueueRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExecuteScript extends Command {
    public ExecuteScript() {
        super("execute_script", "{file} - считать и исполнить скрипт из указанного файла");
    }

    @Override
    public ExecutionResult execute(ExecutionArguments args) {
        if (args.getText() == null)  //проверка что есть аргументы
            return new ExecutionResult("введите имя файла");
        if (args.getText().isEmpty() || args.getText().isBlank())
            return new ExecutionResult("имя файла не может быть пустым");

        Path selectedFile = Path.of(args.getText());

        if (Files.notExists(selectedFile))
            return new ExecutionResult("файл не найден");
        if (!Files.isReadable(selectedFile))
            return new ExecutionResult("нет прав на чтение файла");

        try(IOWorker fileWorker = new BufferedFileWorker(selectedFile)) {
            String script = fileWorker.read();

            if (script == null) return new ExecutionResult("ошибка при чтении файла");
            if (script.isBlank() || script.isEmpty()) return new ExecutionResult("файл пуст");

            Deque<String> requests = new ArrayDeque<>(Arrays.stream(script.split("\n")).toList());

            new QueueRunner(requests).start();
        } catch (Exception e) {
            return new ExecutionResult("ошибка при чтении файла: " + e.getMessage());
        }

        return new ExecutionResult("скрипт выполнен");
    }
}
