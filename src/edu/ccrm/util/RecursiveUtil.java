package edu.ccrm.util;

import java.nio.file.Files;
import java.nio.file.Path;

public class RecursiveUtil {
    public static long calculateDirectorySize(Path dir) {
        if (Files.notExists(dir)) return 0;
        long size = 0;
        try (var walker = Files.walk(dir)) {
            size = walker.filter(Files::isRegularFile)
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (Exception e) {
                            return 0;
                        }
                    }).sum();
        } catch (Exception e) {
            // Handle
        }
        return size;
    }
}