package org.telegram.messenger;

import android.annotation.SuppressLint;
import java.io.BufferedInputStream;
import java.io.IOException;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import org.telegram.tgnet.SerializedData;

/* loaded from: classes5.dex */
public final class ResLottieMeta {
    private static final String ASSET_NAME = "lottie_meta.bin";
    private static final int ENTRY_SIZE = 8;
    public static final long NOT_FOUND = -1;

    public static final class Holder {
        private static final long[] DATA = null;

        /* renamed from: -$$Nest$sfgetDATA, reason: not valid java name */
        public static /* bridge */ /* synthetic */ long[] m4819$$Nest$sfgetDATA() {
            return DATA;
        }

        static {
            DATA = ResLottieMeta.m4818$$Nest$smload();
        }

        private Holder() {
        }
    }

    /* renamed from: -$$Nest$smload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long[] m4818$$Nest$smload() {
        return load();
    }

    private ResLottieMeta() {
    }

    @SuppressLint({"ResourceType"})
    public static long find(int r7) {
        long[] r0 = Holder.m4819$$Nest$sfgetDATA();
        int r1 = r0.length - 1;
        int r2 = 0;
    L3:
        if (r2 > r1) goto L10;
        int r3 = (r2 + r1) >>> 1;
        long r4 = r0[r3];
        int r6 = resIdOf(r4);
        if (r6 < r7) goto L6;
        if (r6 <= r7) goto L9;
        r1 = r3 - 1;
        goto L3
    L9:
        return r4;
    L6:
        r2 = r3 + 1;
        goto L3
    L10:
        return -1;
    }

    public static int fpsOf(long r2) {
        return (int) ((r2 >>> 24) & 255);
    }

    public static int frameCountOf(long r2) {
        return (int) (r2 & 8388607);
    }

    public static boolean isMonoColorOf(long r2) {
        if ((r2 & 8388608) == 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static long[] load() {
        BufferedInputStream r2 = new BufferedInputStream(ApplicationLoader.applicationContext.getResources().getAssets().open(ASSET_NAME));     // Catch: RuntimeException -> L14 IOException -> L16
        int r3 = r2.available();     // Catch: Throwable -> L10
        if ((r3 % 8) != 0) goto L19;
        int r32 = r3 / 8;     // Catch: Throwable -> L10
        long[] r1 = new long[r32];     // Catch: Throwable -> L10
        SerializedData r4 = new SerializedData(r2);     // Catch: Throwable -> L10
        int r5 = 0;
    L7:
        if (r5 >= r32) goto L12;
        r1[r5] = r4.readInt64(true);     // Catch: Throwable -> L10
        r5 = r5 + 1;
        goto L7
    L12:
        r2.close();     // Catch: RuntimeException -> L14 IOException -> L16
        return r1;
    L19:
        throw new IllegalStateException("lottie_meta.bin has invalid size: " + r3);     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        r2.close();     // Catch: Throwable -> L22
    L24:
        throw th;     // Catch: RuntimeException -> L14 IOException -> L16
    L22:
        th = move-exception;
        th.addSuppressed(th);     // Catch: RuntimeException -> L14 IOException -> L16
    L16:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Unable to load lottie_meta.bin", e);
        return null;
    L14:
        e = move-exception;
        if (BuildVars.LOGS_ENABLED == false) goto L28;
        FileLog.e("Unable to load lottie_meta.bin", e);
    L28:
        throw e;
    }

    public static int resIdOf(long r1) {
        return (int) (r1 >>> 32);
    }
}
