package com.benkitou.hotel.utils;

import java.io.File;

public class FilesOperations {
    public static String removeLastSegmentFromPath(String path) {
        int lastSlashIndex = path.lastIndexOf("/");
        if (lastSlashIndex >= 0) {
            return path.substring(0, lastSlashIndex);
        } else {
            // Handle the case where there is no slash in the path
            return path;
        }
    }

    public static  void deleteFolderAndContents(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists()) {
            if (folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            // Recursively delete subdirectories
                            deleteFolderAndContents(file.getAbsolutePath());
                        } else {
                            // Delete files within the folder
                            if (!file.delete()) {
                                System.err.println("Failed to delete file: " + file.getAbsolutePath());
                            }
                        }
                    }
                }
                // Delete the empty folder
                if (!folder.delete()) {
                    System.err.println("Failed to delete folder: " + folder.getAbsolutePath());
                }
            } else {
                System.err.println("Path is not a directory: " + folder.getAbsolutePath());
            }
        } else {
            System.err.println("Folder does not exist: " + folder.getAbsolutePath());
        }
    }
}
