package com.test.token.jwtoken.services.entity.implementation;

import com.test.token.jwtoken.exception.FileTypeNotValidException;
import com.test.token.jwtoken.payloads.ImageResponse;
import com.test.token.jwtoken.services.entity.serviceInterface.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${project.image}")
    private String path;

    @Override
    public ImageResponse updatePostImage(String path, MultipartFile multipartFile)
            throws IOException, FileTypeNotValidException {

        String contentType = multipartFile.getContentType();

        assert contentType != null;
        if (contentType.contains("jpeg") || contentType.contains("jpg") || contentType.contains("png")) {

            // FileName
            String name = multipartFile.getOriginalFilename();

            // Generating Random File Name for The Image
            String randomId = UUID.randomUUID().toString();
            assert name != null;
            String filename = randomId.concat(name.substring(name.lastIndexOf(".")));

            // FullPath
            String filePath = path + File.separator + filename;

            // Create Folder if Not Created
            File folder = new File(path);

            if (!folder.exists()) {
                folder.mkdir();
            }

            // File Copy
            Files.copy(multipartFile.getInputStream(), Paths.get(filePath));

            return new ImageResponse(LocalDateTime.now(), filename, "Post Image Updated Successfully !", true);

        } else {
            throw new FileTypeNotValidException("Image", contentType);
        }

    }

    @Override
    public InputStream servePostImage(String path, String imageName) throws FileNotFoundException {

        String fullPath = path + File.separator + imageName;

        InputStream is = new FileInputStream(fullPath);

        return is;
    }

    @Override
    public boolean delete(String filename) throws IOException {

        Path file = Paths.get(path + File.separator + filename);

        return Files.deleteIfExists(file);

    }

}
