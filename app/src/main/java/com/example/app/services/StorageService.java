package com.example.app.services;

import com.example.app.models.user.User;
import com.example.app.models.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    private final Path avatarPath = Paths.get("avatars");

    public void uploadImage(
            MultipartFile   image,
            User            user
    ) {
        try(InputStream is = image.getInputStream()) {

            if (!Files.exists(avatarPath)) {
                Files.createDirectories(avatarPath);
            }

            Path destination = avatarPath.resolve(Objects.requireNonNull(
                    image.getOriginalFilename()
            ));

            Files.copy(image.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

            user.setAvatarUrl(image.toString());

            userRepository.save(user);

        } catch(IOException ex) {
            throw new InternalError("ads");
        }
    }
}
