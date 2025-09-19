package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.ccrm.config.AppConfig;

public class BackupService {
    public void backupData() throws IOException {
        Path dataDir = Paths.get(AppConfig.getInstance().getDataFolder());
        Path backupDir = dataDir.resolve("backups/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")));
        Files.createDirectories(backupDir);
        try (var walker = Files.walk(dataDir)) {
            walker.filter(p -> !p.startsWith(backupDir) && Files.isRegularFile(p))
                    .forEach(p -> {
                        try {
                            Files.copy(p, backupDir.resolve(dataDir.relativize(p)), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            // Handle
                        }
                    });
        }
    }
}