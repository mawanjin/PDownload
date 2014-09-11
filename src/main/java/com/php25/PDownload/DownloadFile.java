package com.php25.PDownload;

import java.io.Serializable;

/**
 * Created with penghuiping
 * User: penghuiping
 * Date: 14-9-5
 * Time: 上午10:52
 * To change this template use File | Settings | File Templates.
 */
public class DownloadFile implements Serializable{
    private Long id;
    private String tag;
    private String url;
    private String basePath;
    private String name;
    private String absolutePath;
    private Integer totalSize;
    private boolean downloading;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Boolean getDownloading() {
        return downloading;
    }

    public void setDownloading(Boolean downloading) {
        this.downloading = downloading;
    }

    @Override
    public String toString() {
        return "DownloadFile{" +
                "url='" + url + '\'' +
                ", basePath='" + basePath + '\'' +
                ", name='" + name + '\'' +
                ", absolutePath='" + absolutePath + '\'' +
                ", totalSize=" + totalSize +
                '}';
    }
}
