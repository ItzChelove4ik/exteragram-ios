package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.concurrency.CrashlyticsTasks;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class DataCollectionArbiter {
    private Boolean crashlyticsDataCollectionEnabled;
    TaskCompletionSource<Void> dataCollectionEnabledTask;
    private final TaskCompletionSource<Void> dataCollectionExplicitlyApproved;
    private final FirebaseApp firebaseApp;
    private boolean setInManifest;
    private final SharedPreferences sharedPreferences;
    private final Object taskLock;
    boolean taskResolved;

    public DataCollectionArbiter(FirebaseApp r3) {
        Object r0 = new Object();
        this.taskLock = r0;
        this.dataCollectionEnabledTask = new TaskCompletionSource();
        this.taskResolved = false;
        this.setInManifest = false;
        this.dataCollectionExplicitlyApproved = new TaskCompletionSource();
        Context r1 = r3.getApplicationContext();
        this.firebaseApp = r3;
        this.sharedPreferences = CommonUtils.getSharedPrefs(r1);
        Boolean r32 = getDataCollectionValueFromSharedPreferences();
        if (r32 != null) goto L5;
        r32 = getDataCollectionValueFromManifest(r1);
    L5:
        this.crashlyticsDataCollectionEnabled = r32;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L8:
        if (isAutomaticDataCollectionEnabled() == false) goto L12;
        this.dataCollectionEnabledTask.trySetResult(null);     // Catch: Throwable -> L10
        this.taskResolved = true;     // Catch: Throwable -> L10
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
    }

    private Boolean getDataCollectionValueFromManifest(Context r2) {
        Boolean r22 = readCrashlyticsDataCollectionEnabledFromManifest(r2);
        if (r22 != null) goto L6;
        this.setInManifest = false;
        return null;
    L6:
        this.setInManifest = true;
        return Boolean.valueOf(Boolean.TRUE.equals(r22));
    }

    private Boolean getDataCollectionValueFromSharedPreferences() {
        if (this.sharedPreferences.contains("firebase_crashlytics_collection_enabled") == false) goto L6;
        this.setInManifest = false;
        return Boolean.valueOf(this.sharedPreferences.getBoolean("firebase_crashlytics_collection_enabled", true));
    L6:
        return null;
    }

    private boolean isFirebaseDataCollectionDefaultEnabled() {
        return this.firebaseApp.isDataCollectionDefaultEnabled();
    L4:
        return false;
    }

    private void logDataCollectionState(boolean r3) {
        if (r3 == false) goto L4;
        String r32 = "ENABLED";
    L6:
        if (this.crashlyticsDataCollectionEnabled != null) goto L9;
        String r2 = "global Firebase setting";
    L12:
        Logger.getLogger().d(String.format("Crashlytics automatic data collection %s by %s.", new Object[]{r32, r2}));
        return;
    L9:
        if (this.setInManifest == false) goto L11;
        r2 = "firebase_crashlytics_collection_enabled manifest flag";
        goto L12
    L11:
        r2 = "API";
        goto L12
    L4:
        r32 = "DISABLED";
        goto L6
    }

    private static Boolean readCrashlyticsDataCollectionEnabledFromManifest(Context r3) {
        PackageManager r1 = r3.getPackageManager();     // Catch: PackageManager.NameNotFoundException -> L13
        if (r1 == null) goto L19;
        ApplicationInfo r32 = r1.getApplicationInfo(r3.getPackageName(), 128);     // Catch: PackageManager.NameNotFoundException -> L13
        if (r32 == null) goto L20;
        Bundle r12 = r32.metaData;     // Catch: PackageManager.NameNotFoundException -> L13
        if (r12 != null) goto L10;
        return null;
    L10:
        if (r12.containsKey("firebase_crashlytics_collection_enabled") == false) goto L22;
        return Boolean.valueOf(r32.metaData.getBoolean("firebase_crashlytics_collection_enabled"));
    L22:
        return null;
    L20:
        return null;
    L19:
        return null;
    L13:
        e = move-exception;
        Logger.getLogger().e("Could not read data collection permission from manifest", e);
        return null;
    }

    @SuppressLint({"ApplySharedPref"})
    private static void storeDataCollectionValueInSharedPreferences(SharedPreferences r1, Boolean r2) {
        SharedPreferences.Editor r12 = r1.edit();
        if (r2 == null) goto L5;
        r12.putBoolean("firebase_crashlytics_collection_enabled", r2.booleanValue());
    L6:
        r12.apply();
        return;
    L5:
        r12.remove("firebase_crashlytics_collection_enabled");
        goto L6
    }

    public void grantDataCollectionPermission(boolean r1) {
        if (r1 == false) goto L5;
        this.dataCollectionExplicitlyApproved.trySetResult(null);
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("An invalid data collection token was used.");
    }

    public synchronized boolean isAutomaticDataCollectionEnabled() {
        monitor-enter(this);
        Boolean r0 = this.crashlyticsDataCollectionEnabled;     // Catch: Throwable -> L6
        if (r0 == null) goto L8;
        boolean r02 = r0.booleanValue();     // Catch: Throwable -> L6
    L9:
        logDataCollectionState(r02);     // Catch: Throwable -> L6
        monitor-exit(this);
        return r02;
    L8:
        r02 = isFirebaseDataCollectionDefaultEnabled();     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    }

    public synchronized void setCrashlyticsDataCollectionEnabled(Boolean r4) {
        monitor-enter(this);
        if (r4 != null) goto L31;
    L8:
        if (r4 == null) goto L10;
        Boolean r1 = r4;
    L11:
        this.crashlyticsDataCollectionEnabled = r1;     // Catch: Throwable -> L6
        storeDataCollectionValueInSharedPreferences(this.sharedPreferences, r4);     // Catch: Throwable -> L6
        Object r42 = this.taskLock;     // Catch: Throwable -> L6
        monitor-enter(r42);     // Catch: Throwable -> L6
        boolean r12 = isAutomaticDataCollectionEnabled();     // Catch: Throwable -> L18
        boolean r2 = this.taskResolved;
        if (r12 == false) goto L20;
        if (r2 == true) goto L22;
        this.dataCollectionEnabledTask.trySetResult(null);     // Catch: Throwable -> L18
        this.taskResolved = true;     // Catch: Throwable -> L18
    L22:
        monitor-exit(r42);     // Catch: Throwable -> L18
        monitor-exit(this);
        return;
    L20:
        if (r2 == false) goto L22;
        this.dataCollectionEnabledTask = new TaskCompletionSource();     // Catch: Throwable -> L18
        this.taskResolved = false;     // Catch: Throwable -> L18
    L18:
        th = move-exception;
        throw th;     // Catch: Throwable -> L6
    L6:
        th = move-exception;
        throw th;
    L10:
        r1 = getDataCollectionValueFromManifest(this.firebaseApp.getApplicationContext());     // Catch: Throwable -> L6
        goto L11
    L31:
        this.setInManifest = false;     // Catch: Throwable -> L6
        goto L8
    }

    public Task<Void> waitForAutomaticDataCollectionEnabled() {
        Object r0 = this.taskLock;
        monitor-enter(r0);
        Task<Void> r1 = this.dataCollectionEnabledTask.getTask();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public Task<Void> waitForDataCollectionPermission() {
        return CrashlyticsTasks.race(this.dataCollectionExplicitlyApproved.getTask(), waitForAutomaticDataCollectionEnabled());
    }
}
