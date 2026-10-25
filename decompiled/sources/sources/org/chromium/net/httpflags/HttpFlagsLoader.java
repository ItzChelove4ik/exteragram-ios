package org.chromium.net.httpflags;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.httpflags.ResolvedFlags;
import org.chromium.net.impl.CronetManifest;

/* loaded from: classes5.dex */
public final class HttpFlagsLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String FLAGS_FILE_DIR_NAME = "app_httpflags";
    static final String FLAGS_FILE_NAME = "flags.binarypb";
    static final String FLAGS_FILE_PROVIDER_INTENT_ACTION = "android.net.http.FLAGS_FILE_PROVIDER";
    public static final String LOG_FLAG_NAME = "Cronet_log_me";
    public static final String TAG = "HttpFlagsLoader";
    private static ResolvedFlags sHttpFlags;
    private static final Object sLock = null;
    private static String sVersion;

    static {
        sLock = new Object();
    }

    private HttpFlagsLoader() {
    }

    public static void flushHttpFlags() {
        sHttpFlags = null;
        sVersion = null;
    }

    private static File getFlagsFileFromProvider(ApplicationInfo r3) {
        return new File(new File(new File(r3.deviceProtectedDataDir), FLAGS_FILE_DIR_NAME), FLAGS_FILE_NAME);
    }

    public static ResolvedFlags getHttpFlags(Context r4, String r5, boolean r6, boolean r7) {
        Object r0 = sLock;
        monitor-enter(r0);
        String r1 = sVersion;     // Catch: Throwable -> L11
        if (r1 != null) goto L7;
    L13:
        ResolvedFlags r12 = sHttpFlags;     // Catch: Throwable -> L11
        if (r12 == null) goto L17;
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r12;
    L17:
        sVersion = r5;     // Catch: Throwable -> L11
        ScopedSysTraceEvent r13 = ScopedSysTraceEvent.scoped("HttpFlagsLoader#getHttpFlags loading flags");     // Catch: Throwable -> L11
    L21:
        th = move-exception;
        if (r13 != null) goto L50;
    L44:
        throw th;     // Catch: Throwable -> L11
    L50:
        r13.close();     // Catch: Throwable -> L42
    L42:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L11
        goto L44
    L19:
        if (CronetManifest.shouldReadHttpFlags(r4) == true) goto L23;
        Log.d(TAG, "Not loading HTTP flags because they are disabled in the manifest");     // Catch: Throwable -> L21
        Flags r2 = null;
    L24:
        if (r2 != null) goto L27;
        r2 = Flags.newBuilder().build();     // Catch: Throwable -> L21
    L27:
        ResolvedFlags r42 = ResolvedFlags.resolve(r2, r4.getPackageName(), r5, r7);     // Catch: Throwable -> L21
        sHttpFlags = r42;     // Catch: Throwable -> L21
        ResolvedFlags.Value r43 = r42.flags().get(LOG_FLAG_NAME);     // Catch: Throwable -> L21
        if (r43 != null) goto L30;
    L34:
        ResolvedFlags r44 = sHttpFlags;     // Catch: Throwable -> L21
        if (r13 == null) goto L37;
        r13.close();     // Catch: Throwable -> L11
    L37:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return r44;
    L30:
        if (r6 == false) goto L32;
        String r62 = "API";
    L33:
        Log.i(TAG, String.format("HTTP flags log line (%s): %s", new Object[]{r62, r43.getStringValue()}));     // Catch: Throwable -> L21
        goto L34
    L32:
        r62 = "Impl";
        goto L33
    L23:
        r2 = load(r4);     // Catch: Throwable -> L21
        goto L24
    L7:
        if (r5.equals(r1) == true) goto L13;
        throw new IllegalStateException("getHttpFlags() called multiple times with different versions");     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    private static ApplicationInfo getProviderApplicationInfo(Context r3) {
        ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("HttpFlagsLoader#getProviderApplicationInfo");
        ResolveInfo r32 = r3.getPackageManager().resolveService(new Intent(FLAGS_FILE_PROVIDER_INTENT_ACTION), 1048576);     // Catch: Throwable -> L10
        if (r32 != null) goto L12;
        Log.i(TAG, "Unable to resolve the HTTP flags file provider package. This is expected if the host system is not set up to provide HTTP flags.");     // Catch: Throwable -> L10
        if (r0 == null) goto L9;
        r0.close();
    L9:
        return null;
    L12:
        ApplicationInfo r33 = r32.serviceInfo.applicationInfo;     // Catch: Throwable -> L10
        if (r0 == null) goto L15;
        r0.close();
    L15:
        return r33;
    L10:
        th = move-exception;
        if (r0 != null) goto L23;
    L21:
        throw th;
    L23:
        r0.close();     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        th.addSuppressed(th);
        goto L21
    }

    public static Flags load(Context r4) {
        ApplicationInfo r42 = getProviderApplicationInfo(r4);     // Catch: RuntimeException -> L11
        if (r42 != null) goto L6;
        return null;
    L6:
        Log.d(TAG, String.format("Found application exporting HTTP flags: %s", new Object[]{r42.packageName}));     // Catch: RuntimeException -> L11
        File r43 = getFlagsFileFromProvider(r42);     // Catch: RuntimeException -> L11
        Log.d(TAG, String.format("HTTP flags file path: %s", new Object[]{r43.getAbsolutePath()}));     // Catch: RuntimeException -> L11
        Flags r44 = loadFlagsFile(r43);     // Catch: RuntimeException -> L11
        if (r44 != null) goto L9;
        return null;
    L9:
        Log.d(TAG, String.format("Successfully loaded HTTP flags: %s", new Object[]{r44}));     // Catch: RuntimeException -> L11
        return r44;
    L11:
        e = move-exception;
        Log.i(TAG, "Unable to load HTTP flags file", e);
        return null;
    }

    private static Flags loadFlagsFile(File r3) {
        ScopedSysTraceEvent r0 = ScopedSysTraceEvent.scoped("HttpFlagsLoader#loadFlagsFile");
        FileInputStream r1 = new FileInputStream(r3);     // Catch: Throwable -> L9 IOException -> L11 FileNotFoundException -> L21
        Flags r2 = Flags.parseDelimitedFrom(r1);     // Catch: Throwable -> L13
        r1.close();     // Catch: Throwable -> L9 IOException -> L11 FileNotFoundException -> L21
        if (r0 == null) goto L8;
        r0.close();
    L8:
        return r2;
    L13:
        th = move-exception;
        r1.close();     // Catch: Throwable -> L16
    L18:
        throw th;     // Catch: Throwable -> L9 IOException -> L11 FileNotFoundException -> L21
    L16:
        th = move-exception;
        th.addSuppressed(th);     // Catch: Throwable -> L9 IOException -> L11 FileNotFoundException -> L21
    L9:
        th = move-exception;
        if (r0 != null) goto L37;
    L31:
        throw th;
    L37:
        r0.close();     // Catch: Throwable -> L29
    L29:
        th = move-exception;
        th.addSuppressed(th);
    L21:
        Log.i(TAG, String.format("HTTP flags file `%s` is missing. This is expected if HTTP flags functionality is currently disabled in the host system.", new Object[]{r3.getPath()}));     // Catch: Throwable -> L9
        if (r0 == null) goto L39;
        r0.close();
        return null;
    L39:
        return null;
    L11:
        e = move-exception;
        throw new RuntimeException("Unable to read HTTP flags file", e);     // Catch: Throwable -> L9
    }
}
