package com.onebeld.voyager.services;

import com.onebeld.voyager.services.interfaces.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    private final Path fileStorageLocation;
    private final String pathToImages = System.getProperty("files.dir");

    public FileStorageServiceImpl() {
        this.fileStorageLocation = Paths.get(pathToImages).toAbsolutePath().normalize();
    }

    @Override
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty())
                throw new RuntimeException("Failed to store empty file " + file.getOriginalFilename());

            String fileName = generateFileName(file);
            Path targetLocation = fileStorageLocation.resolve(fileName);

            Files.copy(file.getInputStream(), targetLocation);

            return pathToImages + fileName;
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    @Override
    public Path load(String filename) {
        return fileStorageLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = load(fileName);

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable())
                return resource;
            else
                throw new RuntimeException("Could not read file: " + fileName);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public void delete(String url) throws IOException {
        URI uri = URI.create(url);

        String fileName = Paths.get(uri.getPath()).getFileName().toString();
        Path path = fileStorageLocation.resolve(fileName);

        Files.delete(path);
    }

    private String generateFileName(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String extension = "";

        int i = Objects.requireNonNull(originalFileName).lastIndexOf('.');
        if (i > 0)
            extension = originalFileName.substring(i + 1);

        return UUID.randomUUID().toString() + "-" + originalFileName + "." + extension;
    }
}
