package se.ifmo;

import  se.ifmo.chat.handler.Handler;
import se.ifmo.chat.handler.HandlerConfiguration;
import se.ifmo.chat.runner.ConsoleRunner;
import se.ifmo.core.collection.ResourceController;
import se.ifmo.core.commands.list.*;
import se.ifmo.core.io.IOWorker;
import se.ifmo.core.io.console.BufferedConsoleWorker;

import java.nio.file.Path;
import java.util.List;

public class Application {
    private static Path filePath = null;

    public static Path getFilePath() {
        return filePath;
    }

    public static final Handler HANDLER = new Handler(new HandlerConfiguration(List.of(
            new AverageOfImpactSpeed(),
            new Clear(),
            new ExecuteScript(),
            new Exit(),
            new History(),
            new Info(),
            new Insert(),
            new MaxByRealHero(),
            new PrintFieldAscendingMinutesOfWaiting(),
            new RemoveKey(),
            new RemoveLower(),
            new ReplaceIfGreater(),
            new Save(),
            new Show(),
            new Update()
    )));

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Укажите путь к файлу в качестве аргумента командной строки");
            return;
        }

        filePath = Path.of(args[0]);
        ResourceController.getInstance().load();

        try (IOWorker consoleWorker = new BufferedConsoleWorker();
             ConsoleRunner consoleRunner = new ConsoleRunner(consoleWorker)) {

            consoleRunner.start();
        } catch (Exception e) {
            System.err.println("Ошибка во время исполнения: " + e.getMessage());
        }
    }
}


