package com.exteragram.messenger.camera;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.util.Size;
import android.util.SizeF;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.Utilities;

/* loaded from: classes4.dex */
public abstract class CameraLensStops {
    private static final float[] NO_RATIOS = null;
    private static final Map<String, float[]> RATIO_CACHE = null;
    private static final float[] ROUND_RATIOS = null;
    private static final float[] RULER_LADDER = null;
    private static final float[] SNAP_RATIOS = null;

    static {
        NO_RATIOS = new float[0];
        SNAP_RATIOS = new float[]{1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 10.0f, 12.0f, 15.0f, 20.0f};
        ROUND_RATIOS = new float[]{1.5f, 2.0f, 3.0f, 5.0f, 10.0f, 15.0f, 20.0f, 30.0f};
        RULER_LADDER = new float[]{1.0f, 2.0f, 5.0f, 10.0f, 30.0f};
        RATIO_CACHE = new HashMap();
    }

    private static void addDistinctStop(ArrayList<Float> r4, float r5) {
        if (r5 > 0.0f) goto L5;
        return;
    L5:
        if (Float.isFinite(r5) == false) goto L24;
        int r0 = 0;
    L9:
        if (r0 >= r4.size()) goto L18;
        float r1 = r4.get(r0).floatValue();
        if (Math.abs(r1 - r5) <= 1.0E-4f) goto L25;
        if (r1 > r5) goto L15;
        r0 = r0 + 1;
        goto L9
    L15:
        r4.add(r0, Float.valueOf(r5));
        return;
    L25:
        return;
    L18:
        r4.add(Float.valueOf(r5));
        return;
    }

    private static void addReachStop(ArrayList<Float> r2, float r3, float r4) {
        if (r2.size() < 4) goto L5;
        return;
    L5:
        if (r2.isEmpty() == true) goto L16;
        float r32 = snapToNiceRatio(r3 * 2.0f);
        if (r32 <= (r4 + 1.0E-4f)) goto L10;
        return;
    L10:
        if (r32 <= (r2.get(r2.size() - 1).floatValue() + 1.0E-4f)) goto L17;
        addDistinctStop(r2, r32);
        return;
    L17:
        return;
    }

    private static float[] boundStops(float[] r6, float r7, float r8, boolean r9) {
        ArrayList r0 = new ArrayList(r6.length + 2);
        if (r9 == false) goto L5;
        addDistinctStop(r0, r7);
    L5:
        int r1 = r6.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L20;
        float r3 = r6[r2];
        if (r3 <= 0.0f) goto L19;
        if (Float.isFinite(r3) == false) goto L19;
        if (r3 >= (r7 - 1.0E-4f)) goto L17;
        if (r9 == true) goto L19;
        addDistinctStop(r0, r7);
        goto L19
    L17:
        if (r3 > (1.0E-4f + r8)) goto L19;
        addDistinctStop(r0, Utilities.clamp(r3, r8, r7));
    L19:
        r2 = r2 + 1;
        goto L6
    L20:
        if (r9 == false) goto L23;
        addDistinctStop(r0, r8);
    L23:
        return toArray(r0);
    }

    public static float[] buildRulerStops(float r8, float r9, float[] r10) {
        ArrayList r0 = new ArrayList((r10.length + RULER_LADDER.length) + 2);
        addDistinctStop(r0, r8);
        int r1 = r10.length;
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        addDistinctStop(r0, Utilities.clamp(r10[r3], r9, r8));
        r3 = r3 + 1;
        goto L3
    L5:
        addDistinctStop(r0, r9);
        float[] r102 = RULER_LADDER;
        int r12 = r102.length;
    L6:
        if (r2 >= r12) goto L18;
        float r32 = r102[r2];
        if (r32 < (r8 - 1.0E-4f)) goto L16;
        if (r32 > (1.0E-4f + r9)) goto L16;
        if (nearestOctaveDistance(r0, r32) < 0.55d) goto L16;
        addDistinctStop(r0, r32);
    L16:
        r2 = r2 + 1;
        goto L6
    L18:
        return toArray(r0);
    }

    public static float[] buildToggleStops(boolean r5, float r6, float r7, float[] r8) {
        float[] r82 = telephotoRatios(r8, r7);
        int r3 = 0;
        if (r82.length == 0) goto L5;
        ArrayList r52 = new ArrayList(6);
        if (r6 >= 0.9999f) goto L13;
        addDistinctStop(r52, r6);
    L13:
        addDistinctStop(r52, Utilities.clamp(1.0f, r7, r6));
        int r62 = r82.length;
    L14:
        if (r3 >= r62) goto L16;
        addDistinctStop(r52, r82[r3]);
        r3 = r3 + 1;
        goto L14
    L16:
        fillWideGaps(r52);
        addReachStop(r52, r82[r82.length - 1], r7);
        dropCrowdedStops(r52);
        return toArray(r52);
    L5:
        if (r5 == false) goto L7;
        float[] r53 = {r6, 1.0f, 2.0f};
    L9:
        return boundStops(r53, r6, r7, false);
    L7:
        r53 = new float[]{r6, 1.0f, 2.0f, 5.0f};
        goto L9
    }

    private static float chooseRoundRatio(float r12, float r13) {
        double r0 = Math.sqrt(r12 * r13);
        float[] r2 = ROUND_RATIOS;
        int r3 = r2.length;
        float r4 = 0.0f;
        double r5 = Double.MAX_VALUE;
        int r7 = 0;
    L3:
        if (r7 >= r3) goto L13;
        float r8 = r2[r7];
        if (r8 <= (r12 + 1.0E-4f)) goto L12;
        if (r8 >= (r13 - 1.0E-4f)) goto L12;
        double r9 = Math.abs(octaves(r8, (float) r0));
        if (r9 >= r5) goto L12;
        r4 = r8;
        r5 = r9;
    L12:
        r7 = r7 + 1;
        goto L3
    L13:
        return r4;
    }

    private static void dropCrowdedStops(ArrayList<Float> r8) {
    L3:
        if (r8.size() <= 5) goto L14;
        int r1 = -1;
        double r2 = Double.MAX_VALUE;
        int r4 = 1;
    L6:
        if (r4 >= (r8.size() - 1)) goto L11;
        double r5 = octaves(r8.get(r4 - 1).floatValue(), r8.get(r4).floatValue());
        if (r5 >= r2) goto L10;
        r1 = r4;
        r2 = r5;
    L10:
        r4 = r4 + 1;
        goto L6
    L11:
        if (r1 < 0) goto L20;
        r8.remove(r1);
        goto L3
    L20:
        return;
    }

    private static void fillWideGaps(ArrayList<Float> r7) {
    L3:
        if (r7.size() >= 4) goto L18;
        int r0 = 1;
        int r1 = -1;
        double r2 = 2.0d;
    L6:
        if (r0 >= r7.size()) goto L11;
        double r4 = octaves(r7.get(r0 - 1).floatValue(), r7.get(r0).floatValue());
        if (r4 <= r2) goto L10;
        r1 = r0;
        r2 = r4;
    L10:
        r0 = r0 + 1;
        goto L6
    L11:
        if (r1 < 0) goto L27;
        float r02 = chooseRoundRatio(r7.get(r1 - 1).floatValue(), r7.get(r1).floatValue());
        if (r02 <= 0.0f) goto L28;
        int r12 = r7.size();
        addDistinctStop(r7, r02);
        if (r7.size() != r12) goto L3;
        return;
    L28:
        return;
    L27:
        return;
    }

    private static float halfFieldOfViewTangent(CameraCharacteristics r6) {
        float[] r0 = (float[]) r6.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS);
        SizeF r1 = (SizeF) r6.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE);
        Rect r2 = (Rect) r6.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
        Size r3 = (Size) r6.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);
        Integer r62 = (Integer) r6.get(CameraCharacteristics.SENSOR_ORIENTATION);
        if (r0 != null) goto L5;
    L27:
        return 0.0f;
    L5:
        if (r0.length == 0) goto L27;
        if (r1 == null) goto L27;
        if (r2 == null) goto L27;
        if (r3 == null) goto L27;
        if (r62 == null) goto L27;
        if ((r62.intValue() % 180) != 90) goto L15;
        float r63 = r1.getHeight();
        float r12 = r2.height();
        int r22 = r3.getHeight();
    L14:
        float r23 = r22;
        float r02 = r0[0];
        if (r02 <= 0.0f) goto L27;
        if (r63 <= 0.0f) goto L27;
        if (r12 <= 0.0f) goto L27;
        if (r23 <= 0.0f) goto L27;
        return ((r63 * r12) / r23) / (r02 * 2.0f);
    L15:
        r63 = r1.getWidth();
        r12 = r2.width();
        r22 = r3.getWidth();
        goto L14
    }

    private static double nearestOctaveDistance(ArrayList<Float> r5, float r6) {
        double r0 = Double.MAX_VALUE;
        int r2 = 0;
    L4:
        if (r2 >= r5.size()) goto L6;
        r0 = Math.min(r0, Math.abs(octaves(r5.get(r2).floatValue(), r6)));
        r2 = r2 + 1;
        goto L4
    L6:
        return r0;
    }

    private static float[] normalizeRatios(float[] r11, float r12) {
        if (r11.length < 2) goto L5;
        float r0 = 0.0f;
        int r2 = 0;
        if (r12 <= 0.0f) goto L11;
        float r1 = r11[0];
        if (r1 <= (1.25f * r12)) goto L11;
        float r122 = r12 / r1;
    L12:
        int r13 = r11.length;
        double r4 = Double.MAX_VALUE;
        int r6 = 0;
    L13:
        if (r6 >= r13) goto L19;
        float r7 = r11[r6] * r122;
        double r8 = Math.abs(octaves(1.0f, r7));
        if (r8 >= r4) goto L17;
        r0 = r7;
        r4 = r8;
    L17:
        r6 = r6 + 1;
        goto L13
    L19:
        if (Math.abs(r0 - 1.0f) > 0.12f) goto L21;
        ArrayList r14 = new ArrayList(r11.length);
        int r3 = r11.length;
    L23:
        if (r2 >= r3) goto L26;
        addDistinctStop(r14, snapToNiceRatio((r11[r2] * r122) / r0));
        r2 = r2 + 1;
        goto L23
    L26:
        return toArray(r14);
    L21:
        return NO_RATIOS;
    L11:
        r122 = 1.0f;
        goto L12
    L5:
        return NO_RATIOS;
    }

    private static double octaves(float r2, float r3) {
        if (r2 > 0.0f) goto L5;
        return 0.0d;
    L5:
        if (r3 > 0.0f) goto L8;
        return 0.0d;
    L8:
        return Math.log(r3 / r2) / Math.log(2.0d);
    }

    public static float[] opticalZoomRatios(Context r2, String r3, float r4) {
        if (r2 == null) goto L26;
        if (r3 == null) goto L26;
        if (Build.VERSION.SDK_INT < 28) goto L26;
        Map<String, float[]> r0 = RATIO_CACHE;
        monitor-enter(r0);
        float[] r1 = r0.get(r3);     // Catch: Throwable -> L22
        monitor-exit(r0);     // Catch: Throwable -> L22
        if (r1 != null) goto L21;
        r1 = readOpticalZoomRatios(r2, r3);
        monitor-enter(r0);
        r0.put(r3, r1);     // Catch: Throwable -> L17
        monitor-exit(r0);     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        throw th;
    L21:
        return normalizeRatios(r1, r4);
    L22:
        th = move-exception;
        throw th;
    L26:
        return NO_RATIOS;
    }

    private static float[] readOpticalZoomRatios(Context r5, String r6) {
        CameraManager r52 = (CameraManager) r5.getSystemService("camera");     // Catch: Exception -> L29
        if (r52 == null) goto L5;
        CameraCharacteristics r62 = r52.getCameraCharacteristics(r6);     // Catch: Exception -> L29
        Set<String> r0 = r62.getPhysicalCameraIds();     // Catch: Exception -> L29
        if (r0 == null) goto L28;
        if (r0.size() < 2) goto L28;
        float r63 = halfFieldOfViewTangent(r62);     // Catch: Exception -> L29
        if (r63 <= 0.0f) goto L14;
        ArrayList r2 = new ArrayList(r0.size());
        Iterator<String> r02 = r0.iterator();
    L17:
        if (r02.hasNext() == false) goto L26;
        float r3 = halfFieldOfViewTangent(r52.getCameraCharacteristics(r02.next()));     // Catch: Exception -> L23
        if (r3 <= 0.0f) goto L17;
        addDistinctStop(r2, r63 / r3);     // Catch: Exception -> L23
    L23:
        e = move-exception;
        FileLog.e(e);
        goto L17
    L26:
        return toArray(r2);
    L14:
        return NO_RATIOS;
    L28:
        return NO_RATIOS;
    L5:
        return NO_RATIOS;
    L29:
        e = move-exception;
        FileLog.e(e);
        return NO_RATIOS;
    }

    private static float snapToNiceRatio(float r9) {
        if (Float.isFinite(r9) == true) goto L5;
    L23:
        return 0.0f;
    L5:
        if (r9 <= 0.0f) goto L23;
        float[] r0 = SNAP_RATIOS;
        int r2 = r0.length;
        float r3 = Float.MAX_VALUE;
        int r4 = 0;
        float r5 = 0.0f;
    L8:
        if (r4 >= r2) goto L19;
        float r6 = r0[r4];
        float r7 = (r6 - r9) / r9;
        if (r7 < (-0.03f)) goto L17;
        if (r7 > 0.1f) goto L17;
        float r72 = Math.abs(r7);
        if (r72 >= r3) goto L17;
        r5 = r6;
        r3 = r72;
    L17:
        r4 = r4 + 1;
        goto L8
    L19:
        if (r5 <= 0.0f) goto L22;
        return r5;
    L22:
        return Math.round(r9 * 10.0f) / 10.0f;
    }

    private static float[] telephotoRatios(float[] r5, float r6) {
        if (r5 == null) goto L17;
        if (r5.length == 0) goto L17;
        ArrayList r0 = new ArrayList(r5.length);
        int r1 = r5.length;
        int r2 = 0;
    L7:
        if (r2 >= r1) goto L15;
        float r3 = r5[r2];
        if (r3 < 1.15f) goto L13;
        if (r3 > (1.0E-4f + r6)) goto L13;
        addDistinctStop(r0, r3);
    L13:
        r2 = r2 + 1;
        goto L7
    L15:
        return toArray(r0);
    L17:
        return NO_RATIOS;
    }

    private static float[] toArray(ArrayList<Float> r4) {
        int r0 = r4.size();
        float[] r1 = new float[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1[r2] = r4.get(r2).floatValue();
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }
}
