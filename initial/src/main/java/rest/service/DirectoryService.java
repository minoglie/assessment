package rest.service;

import rest.bean.DirectoryStatsBean;

import java.io.File;
import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DirectoryService {

    public static Collection<DirectoryStatsBean> calculateDirectoryStats(String dirPath) {

        Map<String, DirectoryStatsBean> directories = new LinkedHashMap<>();

        File dir = new File(dirPath);
        File[] allDirectoryFiles = dir.listFiles();

        if(allDirectoryFiles.length < 500000){
            for (File aFile : allDirectoryFiles) {

                directories = getFileFolderSize(aFile, directories);
            }
        }

        return directories.values();
    }

    static Map<String, DirectoryStatsBean> getFileFolderSize(File dir, Map<String, DirectoryStatsBean> directories) {

        String fileName = dir.getName();
        String path = dir.getPath();
        String absolutePath = dir.getAbsolutePath();
        Boolean isDirectory = dir.isDirectory();
        Boolean isHidden = dir.isHidden();
        Boolean isFile = dir.isFile();
        Long size = dir.length();
        Instant lastModified = Instant.ofEpochMilli(dir.lastModified()) ;

        if (dir.isDirectory()) {

            DirectoryStatsBean directoryStats = directories.get(dir.getName());

            if (directoryStats == null) {
                directoryStats = new DirectoryStatsBean(fileName, path, absolutePath, isDirectory, isHidden, isFile, size, lastModified);
            }

            File[] files = dir.listFiles();

            if (files != null) {
                for (File file : files) {

                    directoryStats.addFileSize(file.length());
                }
            }

            directories.put(dir.getName(), directoryStats);

        } else if (dir.isFile()) {

            DirectoryStatsBean directoryStats = directories.get(dir.getName());

            if (directoryStats == null) {
                directoryStats = new DirectoryStatsBean(fileName, path, absolutePath, isDirectory, isHidden, isFile, size, lastModified);
            }
            directoryStats.addFileSize(dir.length());
            directories.put(dir.getName(), directoryStats);
        }

        return directories;
    }
}
