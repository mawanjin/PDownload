PDownload
=========

android 下载组件4

使用方法
=========
 
1.首先android application需要继承DownloadApplication
2.所有的的api接口都在DownloadTool这个工具类中，这个类中的api:

DownloadTool 
=========

### (1)开启一个下载线程下载文件
        public static void startDownload(DownloadApplication context, String url);
        
        //例子
        String url = "http://mirror.bit.edu.cn/apache/tomcat/tomcat-7/v7.0.55/bin/apache-tomcat-7.0.55.zip";
        DownloadTool.startDownload(mApplication,url);
    
### (2)开启一个定时刷新线程更新下载进度,这里需要传一个DownloadHanlder,这是一个接口。
        public static Future updateDownloadProgress(DownloadApplication context,DownloadHandler handler,DownloadFiledownloadFile);

        //例子
        Future future = DownloadTool.updateDownloadProgress(app,new DownloadHandler() {
        @Override
        public void updateProcess(final float process) {
            //这里更新view需要用uithread
            ((DemoDownloadActivity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    viewTag.progressBar.setProgress((int) (process * 100));
                }
            });
        }
        
        @Override
        public void finished() {
            //这里更新view需要用uithread
            ((DemoDownloadActivity) mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    viewTag.progressBar.setProgress(100);
                    files.remove(file);
                    DemoDownloadListAdapter.this.notifyDataSetChanged();
                    ToastUtils.show(mContext, "下载完成");
                }
            });
        }
        },file);
        这里第三个参数是DowloadFile,是一个正在下载文件任务的描述类,通过这个类可以知道下载文件的大小，存储绝对路径等,如果下载完成，
        类将失效。
            
### (3)  停止一个正在下载的线程
  public static void stopDownload(DownloadApplication context,String url);
### (4)  判断下载线程是否停止
  public static boolean isStopped(DownloadApplication context,String url);
### (5)  获取所有未完成的下载任务列表
  public static List<DownloadFile> getAllDownloadingTask(DownloadApplication context);
### (6)  判断是否可以开始更新进度条
  public static boolean canBeginUpdateProgress(DownloadApplication context,String url)
### (7)  获得下载任务的meta文件
  public static DownloadFile getDownloadFile(DownloadApplication context,String url)
