package com.google.firebase.crashlytics.internal.settings;

import android.content.res.AssetFileDescriptor;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class CachedSettingsIo {
    private final File cachedSettingsFile;

    public CachedSettingsIo(FileStore r2) {
        this.cachedSettingsFile = r2.getCommonFile("com.crashlytics.settings.json");
    }

    private File getSettingsFile() {
        return this.cachedSettingsFile;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JSONObject readCachedSettings() {
        Logger.getLogger().d("Checking for cached settings...");
        AssetFileDescriptor.AutoCloseInputStream r1 = 0;
        FileInputStream r12 = null;
        File r5 = getSettingsFile();     // Catch: Throwable -> L12 Exception -> L14
        if (r5.exists() == false) goto L16;
        FileInputStream r2 = new FileInputStream(r5);     // Catch: Throwable -> L12 Exception -> L14
        JSONObject r3 = new JSONObject(CommonUtils.streamToString(r2));     // Catch: Throwable -> L8 Exception -> L10
        r12 = r2;
    L18:
        CommonUtils.closeOrLog(r12, "Error while closing settings cache file.");
        return r3;
    L10:
        e = e;
    L20:
        Logger.getLogger().e("Failed to fetch cached settings", e);     // Catch: Throwable -> L8
        CommonUtils.closeOrLog(r2, "Error while closing settings cache file.");
        return null;
    L16:
        Logger.getLogger().v("Settings file does not exist.");     // Catch: Throwable -> L12 Exception -> L14
        r3 = null;
    L8:
        th = th;
        r1 = "Checking for cached settings...";
    L23:
        CommonUtils.closeOrLog(r1, "Error while closing settings cache file.");
        throw th;
    L14:
        e = e;
        r2 = null;
    L12:
        th = th;
        goto L23
    }

    public void writeCachedSettings(long r4, JSONObject r6) {
        Logger.getLogger().v("Writing settings to cache file...");
        if (r6 == null) goto L21;
        FileWriter r1 = null;
        r6.put("expires_at", r4);     // Catch: Throwable -> L13 Exception -> L15
        FileWriter r42 = new FileWriter(getSettingsFile());     // Catch: Throwable -> L13 Exception -> L15
        r42.write(r6.toString());     // Catch: Throwable -> L9 Exception -> L11
        r42.flush();     // Catch: Throwable -> L9 Exception -> L11
        CommonUtils.closeOrLog(r42, "Failed to close settings writer.");
        return;
    L11:
        e = e;
        r1 = r42;
    L16:
        Logger.getLogger().e("Failed to cache settings", e);     // Catch: Throwable -> L13
        CommonUtils.closeOrLog(r1, "Failed to close settings writer.");
        return;
    L9:
        th = th;
        r1 = r42;
    L19:
        CommonUtils.closeOrLog(r1, "Failed to close settings writer.");
        throw th;
    L13:
        th = th;
    L15:
        e = e;
        goto L16
    }
}
