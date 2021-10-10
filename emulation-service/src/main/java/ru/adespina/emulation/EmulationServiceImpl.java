package ru.adespina.emulation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.adespina.exceptions.FileStorageException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class EmulationServiceImpl implements EmulationService {
    private final Path fileStorageLocation;
    private static final String GUID_FILE = ".guid";

    public EmulationServiceImpl(EmulationConfig fileStorageLocation) {
        this.fileStorageLocation = Paths.get(fileStorageLocation.getFolder());
    }

    @Override
    public String createFolder(String id) {
        String data;
        try {
            Path path = fileStorageLocation.resolve(id);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                data = writeAndReadFile(id);
            } else {
                throw new FileStorageException("Subfolder: " + id + " already exist");
            }
        } catch (Exception ex) {
            throw new FileStorageException("Could not create subfolder: " + id + ", subfolder already exist ", ex);
        }
        return data;
    }

    private String writeAndReadFile(String id) {
        String data;
        Path path = getPath(id);
        try {
            Files.write(path, UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
            data = Files.readAllLines(path).get(0);
        } catch (Exception ex) {
            throw new FileStorageException("Could write in file: " + path, ex);
        }
        return data;
    }

    private Path getPath(String id) {
        return fileStorageLocation.resolve(id).resolve(GUID_FILE);
    }
}
