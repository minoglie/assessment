package rest.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Map;

public class DirectoryStatsBean {
    String name;
    String path;
    String absolutePath;
    Boolean directory;
    Boolean hidden;
    Boolean file;
    Long size;
    Instant lastModified;

    Integer totalFiles = 0;
    Long totalSize = Long.valueOf(0);

    public DirectoryStatsBean(String name, String path, String absolutePath, Boolean directory, Boolean hidden, Boolean file, Long size, Instant lastModified) {
        this.name = name;
        this.path = path;
        this.absolutePath = absolutePath;
        this.directory = directory;
        this.hidden = hidden;
        this.file = file;
        this.size = size;
        this.lastModified = lastModified;
    }

    public void addFileSize(Long size){
        totalFiles++;
        totalSize += size;
    }

    public BigDecimal displaySizeInMB(){
        return new BigDecimal(totalSize).divide(BigDecimal.valueOf(1024)).divide(BigDecimal.valueOf(1024)).setScale(2, RoundingMode.CEILING);
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public Boolean getDirectory() {
        return directory;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public Boolean getFile() {
        return file;
    }

    public Long getSize() {
        return size;
    }

    public Instant getLastModified() {
        return lastModified;
    }

    public Integer getTotalFiles() {
        return totalFiles;
    }

    public Long getTotalSize() {
        return totalSize;
    }



    @Override
    public String toString() {
        return "DirectoryStatsBean{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                ", isDirectory=" + directory +
                ", isHidden=" + hidden +
                ", isFile=" + file +
                ", size=" + size +
                ", lastModified=" + lastModified +
                ", totalFiles=" + totalFiles +
                ", totalSize=" + totalSize +
                '}';
    }
}
