package se.ifmo.chat.runner;

import se.ifmo.Application;
import se.ifmo.core.collection.ObjectUtil;
import se.ifmo.core.collection.objects.HumanBeing;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;
import se.ifmo.core.io.IOWorker;

import java.util.StringTokenizer;

public class ConsoleRunner implements Runner {
    private final IOWorker ioWorker;

    private boolean handle = true;

    public ConsoleRunner(IOWorker ioWorker) {
        this.ioWorker = ioWorker;
    }

    @Override
    public void start() {
        ioWorker.write("### LAB 5 ###");
        ioWorker.write("Для выхода введите 'exit'");
        ioWorker.write("Для помощи по командам введите 'help'");
        ioWorker.write("Для отчистки консоли введите 'clear'");

        String input;
        main: while (handle && (input = ioWorker.writeAndRead("~ ")) != null && !input.equals("exit")) { //проверка, пока флаг или строка null(Ctr+D) или не exit
            QueueRunner.recursionDepth = 0;//обнуленние счётчика рекурсии

            if ("clear".equals(input)) { //чистка консоли
                ioWorker.clear();
                continue;
            }

            if (input.isEmpty() || input.isBlank()) continue; //проверка на пустую строку

            StringTokenizer tokenizer = new StringTokenizer(input, " ");//разбиение команды на токены
            ExecutionArguments executionArguments = new ExecutionArguments();

            String command = tokenizer.nextToken();//сама команда по первом токену
            int requiredResources = Application.HANDLER.getRequiredElementsForCommand(command);

            while (tokenizer.hasMoreTokens()) executionArguments.setText(executionArguments.getText() + tokenizer.nextToken() + " ");//все оставшиеся токены записываю это текстовые аргументы
            executionArguments.setText(executionArguments.getText().trim());

            while (requiredResources-- > 0) { //прохожусь по всем запросам
                HumanBeing objectIn = ObjectUtil.collect(ioWorker);
                if (objectIn == null) continue main;
                executionArguments.getResource().put(objectIn);
            }

            ExecutionResult result = Application.HANDLER.execute(command, executionArguments);//результат выпонения команды

            if (result == null) {
                ioWorker.write("Команда вернула пустой ответ");
                continue;
            }

            ioWorker.write(result.getText());
            result.getResource().forEach((id, element) -> ioWorker.write("#%d %s",id,element.toString()));
        }

        ioWorker.write("Выход из программы...");
    }

    @Override
    public void stop() {
        handle = false;
    }

    @Override
    public void close() throws Exception {
        stop();
    }
}
