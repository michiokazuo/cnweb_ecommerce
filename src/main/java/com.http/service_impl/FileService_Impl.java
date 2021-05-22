package com.http.service_impl;

import com.http.config.AppConfig;
import com.http.service.FileService;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileService_Impl implements FileService {

    private final static String SAVE_DIRECTORY = AppConfig.SAVE_DIRECTORY;

    @Override
    public List<String> uploadFiles(Collection<Part> partCollection) throws Exception {
        if (partCollection == null) return null;
        List<String> listRs = new ArrayList<>();
        Path path = getFolderUpload();
        String root = AppConfig.ROOT_BE;

        try {
            String saveFile = path + "";

            for (Part part : partCollection) {
                String fileName = getFileName(part);

                if (fileName != null && !fileName.equals("")) {
                    String filePath = saveFile + File.separator + fileName;
                    System.out.println("Write File: " + filePath);
                    part.write(filePath);
                    listRs.add(root + "/" + SAVE_DIRECTORY + "/"
                            + saveFile.substring(saveFile.lastIndexOf(File.separator) + 1) + "/" + fileName);
                }
            }


            if (isEmpty(path)) {
                Files.delete(path);
                System.out.println("Folder is empty");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listRs.isEmpty() ? null : listRs;
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        System.out.println(contentDisposition);
        String[] items = contentDisposition.split(";");

        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                fileName = fileName.replace("\\", "/");
                return fileName.substring(fileName.lastIndexOf("/") + 1);
            }
        }

        return null;
    }

    private Path getFolderUpload() throws IOException {
        String appPath = AppConfig.PATH_SAVE_FILES + "/" + SAVE_DIRECTORY + "/" + new Date().getTime();

        Path folderUpload = Paths.get(appPath);
        if (!Files.exists(folderUpload)) {
            Files.createDirectories(folderUpload);
        }

        return folderUpload;
    }

    private boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directory = Files.newDirectoryStream(path)) {
                return !directory.iterator().hasNext();
            }
        }

        return false;
    }
}
