package se.ifmo.core.io.format;

import java.nio.file.Path;

public interface FormatWorker<K> {
    K read(Path filePath);
    void write(K values, Path filePath);
}
