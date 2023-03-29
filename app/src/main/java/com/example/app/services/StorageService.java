package com.example.app.services;

import com.example.app.models.user.User;
import com.example.app.models.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final UserRepository userRepository;

    @Value("${file.upload-dir}")
    private final String fileDirectory;

    public void uploadImage(MultipartFile image, User user) throws IOException {
        if (image == null || user == null) {
            throw new IllegalArgumentException("Invalid input parameters");
        }

        Path filePath = Paths.get(fileDirectory, image.getOriginalFilename());
        Files.write(filePath, image.getBytes());

        String avatarPath = filePath.toString();
        user.setAvatarPath(avatarPath);
        userRepository.save(user);
    }
}
