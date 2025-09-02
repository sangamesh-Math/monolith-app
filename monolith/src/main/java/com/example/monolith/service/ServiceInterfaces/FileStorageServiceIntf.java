package com.example.monolith.service.ServiceInterfaces;

import com.example.monolith.model.FileMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageServiceIntf {
    public FileMetadata storeFile(MultipartFile file) throws IOException;
    public byte[] getFile(Long id) throws IOException;

    FileMetadata getFileMetadata(Long id);
}
