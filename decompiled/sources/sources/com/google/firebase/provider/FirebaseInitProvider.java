package com.google.firebase.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.StartupTime;
import java.util.concurrent.atomic.AtomicBoolean;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class FirebaseInitProvider extends ContentProvider {
    private static AtomicBoolean currentlyInitializing;
    private static StartupTime startupTime;

    static {
        startupTime = StartupTime.now();
        currentlyInitializing = new AtomicBoolean(false);
    }

    public FirebaseInitProvider() {
    }

    private static void checkContentProviderAuthority(ProviderInfo r1) {
        Preconditions.checkNotNull(r1, "FirebaseInitProvider ProviderInfo cannot be null.");
        if ("com.google.firebase.firebaseinitprovider".equals(r1.authority) == true) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Incorrect provider authority in manifest. Most likely due to a missing applicationId variable in application's build.gradle.");
    }

    public static StartupTime getStartupTime() {
        return startupTime;
    }

    public static boolean isCurrentlyInitializing() {
        return currentlyInitializing.get();
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context r1, ProviderInfo r2) {
        checkContentProviderAuthority(r2);
        super.attachInfo(r1, r2);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri r1, String r2, String[] r3) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri r1) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri r1, ContentValues r2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        currentlyInitializing.set(true);     // Catch: Throwable -> L7
        if (FirebaseApp.initializeApp(getContext()) != null) goto L9;
        Log.i("FirebaseInitProvider", "FirebaseApp initialization unsuccessful");     // Catch: Throwable -> L7
    L10:
        currentlyInitializing.set(false);
        return false;
    L9:
        Log.i("FirebaseInitProvider", "FirebaseApp initialization successful");     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        currentlyInitializing.set(false);
        throw th;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri r1, String[] r2, String r3, String[] r4, String r5) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri r1, ContentValues r2, String r3, String[] r4) {
        return 0;
    }
}
