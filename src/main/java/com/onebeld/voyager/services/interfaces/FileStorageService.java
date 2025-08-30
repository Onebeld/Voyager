package com.onebeld.voyager.services.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface FileStorageService {
    String storeFile(MultipartFile file);

    Path load(String filename);

    Resource loadAsResource(String fileName);

    void delete(String url) throws IOException;
}
