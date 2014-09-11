package com.php25.PDownload;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with penghuiping
 * User: penghuiping
 * Date: 14-9-11
 * Time: 下午1:54
 * To change this template use File | Settings | File Templates.
 */
public class DownloadFileDao {
    private SQLiteDatabase database;

    public DownloadFileDao(Context context) {
        database = context.openOrCreateDatabase("com_php25_PDownload_metaFile.db", Context.MODE_PRIVATE, null);
        database.execSQL("DROP TABLE IF EXISTS DownloadFile");
        database.execSQL("CREATE TABLE IF NOT EXISTS DownloadFile (id INTEGER PRIMARY KEY AUTOINCREMENT,tag VARCHAR,url VARCHAR,basePath VARCHAR,name VARCHAR,absolutePath VARCHAR,totalSize INTEGER,downloading SMALLINT)");
    }

    public void close() {
        database.close();
    }

    public long insert(DownloadFile downloadFile) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag",downloadFile.getTag());
        contentValues.put("url",downloadFile.getUrl());
        contentValues.put("basePath",downloadFile.getBasePath());
        contentValues.put("name",downloadFile.getName());
        contentValues.put("absolutePath",downloadFile.getAbsolutePath());
        contentValues.put("totalSize",downloadFile.getTotalSize());
        contentValues.put("downloading", downloadFile.getDownloading());
        return database.insert("DownloadFile",null,contentValues);
    }

    public void update(DownloadFile downloadFile) {
        database.execSQL("update DownloadFile set downloading=? where id=?", new Object[]{downloadFile.getDownloading(), downloadFile.getId()});
    }

    public void delete(DownloadFile downloadFile) {
        database.execSQL("delete from downloadFile where id=?", new Object[]{downloadFile.getId()});
    }

    public DownloadFile queryByTag(String tag) {
        DownloadFile downloadFile = null;
        Cursor cursor = database.rawQuery("select * from DownloadFile where tag=?", new String[]{tag});
        if (cursor.moveToNext()) {
            downloadFile = new DownloadFile();
            downloadFile.setId(cursor.getLong(cursor.getColumnIndex("id")));
            downloadFile.setTag(cursor.getString(cursor.getColumnIndex("tag")));
            downloadFile.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            downloadFile.setBasePath(cursor.getString(cursor.getColumnIndex("basePath")));
            downloadFile.setName(cursor.getString(cursor.getColumnIndex("name")));
            downloadFile.setAbsolutePath(cursor.getString(cursor.getColumnIndex("absolutePath")));
            downloadFile.setTotalSize(cursor.getInt(cursor.getColumnIndex("totalSize")));
            short value = cursor.getShort(cursor.getColumnIndex("downloading"));
            switch (value) {
                case 0:
                    downloadFile.setDownloading(false);
                    break;
                case 1:
                    downloadFile.setDownloading(true);
                    break;
            }
        }
        cursor.close();
        return downloadFile;
    }

    public List<DownloadFile> queryAll() {
        List<DownloadFile> downloadFiles = new ArrayList<DownloadFile>();
        Cursor cursor = database.rawQuery("select * from DownloadFile", null);
        while (cursor.moveToNext()) {
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.setId(cursor.getLong(cursor.getColumnIndex("id")));
            downloadFile.setTag(cursor.getString(cursor.getColumnIndex("tag")));
            downloadFile.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            downloadFile.setBasePath(cursor.getString(cursor.getColumnIndex("basePath")));
            downloadFile.setName(cursor.getString(cursor.getColumnIndex("name")));
            downloadFile.setAbsolutePath(cursor.getString(cursor.getColumnIndex("absolutePath")));
            downloadFile.setTotalSize(cursor.getInt(cursor.getColumnIndex("totalSize")));
            short value = cursor.getShort(cursor.getColumnIndex("downloading"));
            switch (value) {
                case 0:
                    downloadFile.setDownloading(false);
                    break;
                case 1:
                    downloadFile.setDownloading(true);
                    break;
            }
            downloadFiles.add(downloadFile);
        }
        cursor.close();
        return downloadFiles;
    }
}
