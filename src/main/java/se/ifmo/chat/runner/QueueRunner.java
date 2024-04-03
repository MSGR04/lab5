package se.ifmo.chat.runner;

import se.ifmo.Application;
import se.ifmo.core.collection.ObjectUtil;
import se.ifmo.core.collection.objects.HumanBeing;
import se.ifmo.core.commands.ExecutionArguments;
import se.ifmo.core.commands.ExecutionResult;
import se.ifmo.core.io.IOWorker;
import se.ifmo.core.io.console.BufferedConsoleWorker;

import java.util.Deque;
import java.util.StringTokenizer;

public class QueueRunner implements Runner {
    public static int recursionDepth = 0;
    public static final int MAX_RECURSION_DEPTH = 10;

    private Deque<String> requests;
    private IOWorker ioWorker;

    public QueueRunner(Deque<String> requests) {
        this.requests = requests;
        this.ioWorker = new BufferedConsoleWorker();
    }

    @Override
    public void start() {
        if (recursionDepth > MAX_RECURSION_DEPTH) {
            ioWorker.write("Глубина рекурсии не может быть > " + MAX_RECURSION_DEPTH);
            return;
        }

        recursionDepth++;

        main:
        while (!requests.isEmpty()) {
            String input = requests.pollLast();

            if (input.isEmpty() || input.isBlank()) continue;

            StringTokenizer tokenizer = new StringTokenizer(input, " ");
            ExecutionArguments executionArguments = new ExecutionArguments();

            String command = tokenizer.nextToken();
            int requiredResources = Application.HANDLER.getRequiredElementsForCommand(command);

            if (tokenizer.hasMoreTokens()) executionArguments.setText(tokenizer.nextToken());
            while (requiredResources-- > 0) {
                HumanBeing objectIn = ObjectUtil.collect(requests);
                if (objectIn == null) continue main;
                executionArguments.getResource().put(objectIn);
            }

            ExecutionResult result = Application.HANDLER.execute(command, executionArguments);

            if (result == null)
                continue;

            ioWorker.write(result.getText());
            result.getResource().forEach((id, element) -> ioWorker.write("#%d %s", id, element.toString()));
        }

//        stop();
    }

    @Override
    public void stop() {
        try {
            ioWorker.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        requests.clear();
    }

    @Override
    public void close() throws Exception {
        stop();
    }
}
