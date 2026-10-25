package com.exteragram.messenger.adblock.interop;

import com.exteragram.messenger.adblock.data.BlockResult;
import com.exteragram.messenger.adblock.data.FilterListMetadata;
import com.exteragram.messenger.adblock.data.UrlCosmeticResources;
import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
public class NativeAdBlock {
    private static volatile Boolean loaded;

    public NativeAdBlock() {
    }

    public static native FilterListMetadata addFilters(long r0, String r2);

    public static native long createEngine(long r0);

    public static native long createFilterSet(String[] r0);

    public static native void destroyEngine(long r0);

    public static native void destroyFilterSet(long r0);

    public static native UrlCosmeticResources getCosmeticResources(long r0, String r2);

    public static native String[] getHiddenSelectors(long r0, String[] r2, String[] r3, String[] r4);

    public static boolean isLoadFailed() {
        return Boolean.FALSE.equals(loaded);
    }

    public static synchronized boolean loadLibraries() {
        monitor-enter(NativeAdBlock.class);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (loaded == null) goto L17;
    L12:
        boolean r1 = loaded.booleanValue();     // Catch: Throwable -> L10
        monitor-exit(NativeAdBlock.class);
        return r1;
    L17:
        System.loadLibrary("etgadblock");     // Catch: Throwable -> L8
        loaded = Boolean.TRUE;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        FileLog.e(th);     // Catch: Throwable -> L10
        loaded = Boolean.FALSE;     // Catch: Throwable -> L10
        goto L12
    }

    public static native BlockResult shouldBlock(long r0, String r2, String r3, String r4);

    public static native void useResources(long r0, String[] r2, String[][] r3, String[] r4, String[] r5);
}
