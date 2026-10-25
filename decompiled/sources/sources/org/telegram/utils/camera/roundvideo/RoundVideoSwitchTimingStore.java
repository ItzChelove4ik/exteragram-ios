package org.telegram.utils.camera.roundvideo;

import android.content.SharedPreferences;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.CastStatusCodes;
import java.lang.reflect.Array;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;

/* loaded from: classes6.dex */
abstract class RoundVideoSwitchTimingStore {
    private static final String[] KEYS = null;
    private static final int[] counts = null;
    private static boolean loaded;
    private static final int[] nextIndices = null;
    private static final int[][] samples = null;

    static {
        KEYS = new String[]{"round_video_switch_back_to_front_ms", "round_video_switch_front_to_back_ms"};
        samples = (int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 8});
        counts = new int[2];
        nextIndices = new int[2];
    }

    private static int average(int r4) {
        int r0 = counts[r4];
        if (r0 != 0) goto L9;
        if (r4 != 0) goto L7;
        return 680;
    L7:
        return 630;
    L9:
        int r1 = 0;
        int r2 = 0;
    L10:
        if (r1 >= r0) goto L13;
        r2 = r2 + samples[r4][r1];
        r1 = r1 + 1;
        goto L10
    L13:
        return Math.round(r2 / r0);
    }

    private static int clamp(int r0, int r1, int r2) {
        return Math.max(r1, Math.min(r2, r0));
    }

    private static int direction(RoundVideoSession.CameraFacing r0, RoundVideoSession.CameraFacing r1) {
        if (r0 != r1) goto L4;
        f$$ExternalSyntheticBUOutline1.m("Camera switch direction must change");
        return 0;
    L4:
        if (r0 != RoundVideoSession.CameraFacing.BACK) goto L7;
        return 0;
    L7:
        return 1;
    }

    private static void ensureLoaded() {
        if (loaded == true) goto L20;
        loaded = true;
        SharedPreferences r0 = preferences();
        int r2 = 0;
    L6:
        String[] r3 = KEYS;
        if (r2 >= r3.length) goto L30;
        String r32 = r0.getString(r3[r2], null);
        if (r32 == null) goto L19;
        if (r32.isEmpty() == true) goto L19;
        String[] r33 = r32.split(",");
        int r4 = Math.max(0, r33.length - 8);
    L15:
        if (r4 >= r33.length) goto L18;
        int r5 = clamp(Integer.parseInt(r33[r4]), 200, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: NumberFormatException -> L21
        int[] r6 = samples[r2];     // Catch: NumberFormatException -> L21
        int[] r7 = counts;     // Catch: NumberFormatException -> L21
        int r8 = r7[r2];     // Catch: NumberFormatException -> L21
        r7[r2] = r8 + 1;     // Catch: NumberFormatException -> L21
        r6[r8] = r5;     // Catch: NumberFormatException -> L21
    L17:
        r4 = r4 + 1;
        goto L15
    L18:
        nextIndices[r2] = counts[r2] % 8;
    L19:
        r2 = r2 + 1;
        goto L6
    L30:
        return;
    }

    public static synchronized int getAverageMs(RoundVideoSession.CameraFacing r1, RoundVideoSession.CameraFacing r2) {
        monitor-enter(RoundVideoSwitchTimingStore.class);
        ensureLoaded();     // Catch: Throwable -> L7
        int r12 = average(direction(r1, r2));     // Catch: Throwable -> L7
        monitor-exit(RoundVideoSwitchTimingStore.class);
        return r12;
    L7:
        th = move-exception;
        throw th;
    }

    private static void persist(int r7) {
        int r0 = counts[r7];
        int r1 = 0;
        if (r0 != 8) goto L5;
        int r3 = nextIndices[r7];
    L6:
        StringBuilder r4 = new StringBuilder(r0 * 5);
    L7:
        if (r1 >= r0) goto L11;
        if (r1 <= 0) goto L10;
        r4.append(',');
    L10:
        r4.append(samples[r7][(r3 + r1) % 8]);
        r1 = r1 + 1;
        goto L7
    L11:
        preferences().edit().putString(KEYS[r7], r4.toString()).apply();
        return;
    L5:
        r3 = 0;
        goto L6
    }

    private static SharedPreferences preferences() {
        return ApplicationLoader.applicationContext.getSharedPreferences("mainconfig", 0);
    }

    public static synchronized int recordAndGetAverageMs(RoundVideoSession.CameraFacing r3, RoundVideoSession.CameraFacing r4, int r5) {
        monitor-enter(RoundVideoSwitchTimingStore.class);
        ensureLoaded();     // Catch: Throwable -> L7
        int r32 = direction(r3, r4);     // Catch: Throwable -> L7
        int r42 = clamp(r5, 200, CastStatusCodes.AUTHENTICATION_FAILED);     // Catch: Throwable -> L7
        int[] r52 = samples[r32];     // Catch: Throwable -> L7
        int[] r1 = nextIndices;     // Catch: Throwable -> L7
        r52[r1[r32]] = r42;     // Catch: Throwable -> L7
        r1[r32] = (r1[r32] + 1) % 8;     // Catch: Throwable -> L7
        int[] r43 = counts;     // Catch: Throwable -> L7
        int r12 = r43[r32];     // Catch: Throwable -> L7
        if (r12 >= 8) goto L9;
        r43[r32] = r12 + 1;     // Catch: Throwable -> L7
    L9:
        persist(r32);     // Catch: Throwable -> L7
        int r33 = average(r32);     // Catch: Throwable -> L7
        monitor-exit(RoundVideoSwitchTimingStore.class);
        return r33;
    L7:
        th = move-exception;
        throw th;
    }
}
