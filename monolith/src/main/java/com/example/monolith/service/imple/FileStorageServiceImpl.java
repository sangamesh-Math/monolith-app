package com.example.monolith.service.imple;

import com.example.monolith.model.FileMetadata;
import com.example.monolith.repository.FileMetadataRepository;
import com.example.monolith.service.ServiceInterfaces.FileStorageServiceIntf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileStorageServiceImpl implements FileStorageServiceIntf {

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    private final FileMetadataRepository fileMetadataRepository;

    public FileStorageServiceImpl(FileMetadataRepository fileMetadataRepository) {
        this.fileMetadataRepository = fileMetadataRepository;
    }

    public FileMetadata storeFile(MultipartFile file) throws IOException {
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new RuntimeException("File size exceeds 5MB limit");
        }

        List<String> allowedTypes = List.of(
                "text/plain",
                "application/pdf",
                "application/msword",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        );

        String fileType = file.getContentType();
        if (fileType == null || !allowedTypes.contains(fileType)) {
            throw new RuntimeException("Invalid file type: " + fileType);
        }

        // Ensure directory exists
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        // Save file physically
        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Save metadata in DB
        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(file.getOriginalFilename());
        metadata.setFileType(fileType);
        metadata.setFileSize(file.getSize());
        metadata.setFilePath(filePath.toString());
        metadata.setUploadedAt(LocalDateTime.now());

        return fileMetadataRepository.save(metadata);
    }


    public FileMetadata getFileMetadata(Long id) {
        return fileMetadataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }

    public byte[] getFile(Long id) throws IOException {
        FileMetadata metadata = getFileMetadata(id);
        Path filePath = Paths.get(metadata.getFilePath());
        return Files.readAllBytes(filePath);
    }

}
