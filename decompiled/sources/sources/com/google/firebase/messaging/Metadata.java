package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.FirebaseApp;
import java.util.List;

/* loaded from: classes.dex */
class Metadata {
    private String appVersionCode;
    private String appVersionName;
    private final Context context;
    private int gmsVersionCode;
    private int iidImplementation;

    public Metadata(Context r2) {
        this.iidImplementation = 0;
        this.context = r2;
    }

    public static String getDefaultSenderId(FirebaseApp r3) {
        String r0 = r3.getOptions().getGcmSenderId();
        if (r0 == null) goto L5;
        return r0;
    L5:
        String r32 = r3.getOptions().getApplicationId();
        if (r32.startsWith("1:") == true) goto L8;
        return r32;
    L8:
        String[] r33 = r32.split(":");
        if (r33.length >= 2) goto L11;
        return null;
    L11:
        String r34 = r33[1];
        if (r34.isEmpty() == false) goto L14;
        return null;
    L14:
        return r34;
    }

    private PackageInfo getPackageInfo(String r2) {
        return this.context.getPackageManager().getPackageInfo(r2, 0);
    L4:
        e = move-exception;
        Log.w("FirebaseMessaging", "Failed to find package " + e);
        return null;
    }

    private synchronized void populateAppVersionInfo() {
        monitor-enter(this);
        PackageInfo r0 = getPackageInfo(this.context.getPackageName());     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        this.appVersionCode = Integer.toString(r0.versionCode);     // Catch: Throwable -> L7
        this.appVersionName = r0.versionName;     // Catch: Throwable -> L7
    L9:
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public synchronized String getAppVersionCode() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.appVersionCode != null) goto L8;
        populateAppVersionInfo();     // Catch: Throwable -> L6
    L8:
        String r0 = this.appVersionCode;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    }

    public synchronized String getAppVersionName() {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (this.appVersionName != null) goto L8;
        populateAppVersionInfo();     // Catch: Throwable -> L6
    L8:
        String r0 = this.appVersionName;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    }

    public synchronized int getGmsVersionCode() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.gmsVersionCode != 0) goto L10;
        PackageInfo r0 = getPackageInfo("com.google.android.gms");     // Catch: Throwable -> L8
        if (r0 == null) goto L10;
        this.gmsVersionCode = r0.versionCode;     // Catch: Throwable -> L8
    L10:
        int r02 = this.gmsVersionCode;     // Catch: Throwable -> L8
        monitor-exit(this);
        return r02;
    }

    public synchronized int getIidImplementation() {
        monitor-enter(this);
        int r0 = this.iidImplementation;     // Catch: Throwable -> L12
        if (r0 == 0) goto L7;
        monitor-exit(this);
        return r0;
    L7:
        PackageManager r02 = this.context.getPackageManager();     // Catch: Throwable -> L12
        if (r02.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") != (-1)) goto L15;
        Log.e("FirebaseMessaging", "Google Play services missing or without correct permission.");     // Catch: Throwable -> L12
        monitor-exit(this);
        return 0;
    L15:
        if (PlatformVersion.isAtLeastO() == true) goto L23;
        Intent r1 = new Intent("com.google.android.c2dm.intent.REGISTER");     // Catch: Throwable -> L12
        r1.setPackage("com.google.android.gms");     // Catch: Throwable -> L12
        List<ResolveInfo> r12 = r02.queryIntentServices(r1, 0);     // Catch: Throwable -> L12
        if (r12 == null) goto L23;
        if (r12.size() <= 0) goto L23;
        this.iidImplementation = 1;     // Catch: Throwable -> L12
        monitor-exit(this);
        return 1;
    L23:
        Intent r13 = new Intent("com.google.iid.TOKEN_REQUEST");     // Catch: Throwable -> L12
        r13.setPackage("com.google.android.gms");     // Catch: Throwable -> L12
        List<ResolveInfo> r03 = r02.queryBroadcastReceivers(r13, 0);     // Catch: Throwable -> L12
        if (r03 != null) goto L26;
    L30:
        Log.w("FirebaseMessaging", "Failed to resolve IID implementation package, falling back");     // Catch: Throwable -> L12
        if (PlatformVersion.isAtLeastO() == false) goto L33;
        this.iidImplementation = 2;     // Catch: Throwable -> L12
    L34:
        int r04 = this.iidImplementation;     // Catch: Throwable -> L12
        monitor-exit(this);
        return r04;
    L33:
        this.iidImplementation = 1;     // Catch: Throwable -> L12
        goto L34
    L26:
        if (r03.size() <= 0) goto L30;
        this.iidImplementation = 2;     // Catch: Throwable -> L12
        monitor-exit(this);
        return 2;
    L12:
        th = move-exception;
        throw th;
    }

    public boolean isGmscorePresent() {
        if (getIidImplementation() == 0) goto L6;
        return true;
    L6:
        return false;
    }
}
