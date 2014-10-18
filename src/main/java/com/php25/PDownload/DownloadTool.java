package com.php25.PDownload;

import com.php25.tools.DigestTool;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created with penghuiping
 * User: penghuiping
 * Date: 14-9-10
 * Time: 下午4:00
 * To change this template use File | Settings | File Templates.
 */
public class DownloadTool {
    public static final String LOG_TAG = "com.php25.PDownload";

    /**
     * 开启一个下载线程
     *
     * @param context
     * @param url
     */
    public static void startDownload(DownloadApplication context, String url) {
        if (context.containsDownloadManager(DigestTool.md5(url))) {
            DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(url));
            downloadManager.download(url);
        } else {
            DownloadManager downloadManager = new DownloadManager(context);
            context.addDownloadManager(DigestTool.md5(url), downloadManager);
            downloadManager.download(url);
        }
    }

    /**
     * 开启个下载进度更新线程
     *
     * @param context
     * @param handler
     * @param downloadFile
     * @return
     */
    public static Future updateDownloadProgress(DownloadApplication context, DownloadHandler handler, DownloadFile downloadFile) {
        final DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(downloadFile.getUrl()));
        downloadManager.setDownloadHandler(handler);
        return downloadManager.updateDownloadProgress(downloadFile);
    }

    /**
     * 停止下载线程
     *
     * @param context
     * @param url
     */
    public static void stopDownload(DownloadApplication context, String url) {
        DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(url));
        downloadManager.setStopped(true);
    }

    /**
     * 判断下载线程是否停止
     *
     * @param context
     * @param url
     * @return
     */
    public static boolean isStopped(DownloadApplication context, String url) {
        DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(url));
        return downloadManager.isStopped();
    }

    /**
     * 获取所有需要下载的任务列表
     *
     * @param context
     * @return
     */
    public static List<DownloadFile> getAllDownloadingTask(DownloadApplication context) {
        return context.getDownloadFileDao().queryAll();
    }

    /**
     * 判断是否可以开始跟新进度条
     * @param context
     * @param url
     * @return
     */
    public static boolean canBeginUpdateProgress(DownloadApplication context,String url) {
        DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(url));
        return downloadManager.getCanUpdateProcess();
    }

    /**
     * 获得下载任务的meta文件
     * @param context
     * @param url
     * @return
     */
    public static DownloadFile getDownloadFile(DownloadApplication context,String url) {
        DownloadManager downloadManager = context.getDownloadManager(DigestTool.md5(url));
        return downloadManager.getFile();
    }

}
