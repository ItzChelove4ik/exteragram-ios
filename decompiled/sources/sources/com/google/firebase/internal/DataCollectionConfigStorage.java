package com.google.firebase.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import com.google.firebase.events.Publisher;

/* loaded from: classes.dex */
public class DataCollectionConfigStorage {
    private boolean dataCollectionDefaultEnabled;
    private final Context deviceProtectedContext;
    private final Publisher publisher;
    private final SharedPreferences sharedPreferences;

    public DataCollectionConfigStorage(Context r3, String r4, Publisher r5) {
        Context r32 = directBootSafe(r3);
        this.deviceProtectedContext = r32;
        this.sharedPreferences = r32.getSharedPreferences("com.google.firebase.common.prefs:" + r4, 0);
        this.publisher = r5;
        this.dataCollectionDefaultEnabled = readAutoDataCollectionEnabled();
    }

    private static Context directBootSafe(Context r0) {
        return ContextCompat.createDeviceProtectedStorageContext(r0);
    }

    private boolean readAutoDataCollectionEnabled() {
        if (this.sharedPreferences.contains("firebase_data_collection_default_enabled") == false) goto L7;
        return this.sharedPreferences.getBoolean("firebase_data_collection_default_enabled", true);
    L7:
        return readManifestDataCollectionEnabled();
    }

    private boolean readManifestDataCollectionEnabled() {
        PackageManager r1 = this.deviceProtectedContext.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L15
        if (r1 == null) goto L13;
        ApplicationInfo r3 = r1.getApplicationInfo(this.deviceProtectedContext.getPackageName(), 128);     // Catch: PackageManager.NameNotFoundException -> L15
        if (r3 == null) goto L18;
        Bundle r12 = r3.metaData;     // Catch: PackageManager.NameNotFoundException -> L15
        if (r12 != null) goto L10;
        return true;
    L10:
        if (r12.containsKey("firebase_data_collection_default_enabled") == false) goto L20;
        return r3.metaData.getBoolean("firebase_data_collection_default_enabled");
    L20:
        return true;
    L18:
        return true;
    L13:
        return true;
    L21:
        return true;
    }

    public synchronized boolean isEnabled() {
        monitor-enter(this);
        boolean r0 = this.dataCollectionDefaultEnabled;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }
}
