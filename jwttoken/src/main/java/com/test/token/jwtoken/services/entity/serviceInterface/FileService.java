package com.test.token.jwtoken.services.entity.serviceInterface;

import com.test.token.jwtoken.exception.FileTypeNotValidException;
import com.test.token.jwtoken.payloads.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    ImageResponse updatePostImage(String path, MultipartFile multipartFile) throws IOException, FileTypeNotValidException;

    InputStream servePostImage(String path, String imageName) throws FileNotFoundException;

    boolean delete(String filename) throws IOException;

}
