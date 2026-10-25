package org.chromium.net.telemetry;

import android.util.StatsEvent;
import android.util.StatsLog;

/* loaded from: classes5.dex */
public abstract class CronetStatsLog {
    public static void write(int r1, long r2, int r4, int r5, int r6, int r7, int r8, int r9, int r10, int r11, int r12, int r13, int r14, int r15, int r16) {
        StatsEvent.Builder r0 = StatsEvent.newBuilder();
        r0.setAtomId(r1);
        r0.writeLong(r2);
        r0.writeInt(r4);
        r0.writeInt(r5);
        r0.writeInt(r6);
        r0.writeInt(r7);
        r0.writeInt(r8);
        r0.writeInt(r9);
        r0.writeInt(r10);
        r0.writeInt(r11);
        r0.writeInt(r12);
        r0.writeInt(r13);
        r0.writeInt(r14);
        r0.writeInt(r15);
        r0.writeInt(r16);
        if (762 != r1) goto L5;
        r0.addBooleanAnnotation((byte) 1, true);
    L5:
        r0.usePooledBuffer();
        StatsLog.write(r0.build());
    }

    public static void write(int r1, long r2, int r4, int r5, int r6, int r7, int r8, long r9, int r11, int r12, boolean r13, boolean r14, int r15, int r16, int r17, long r18, long r20, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29, int r30, int r31, String r32, int r33, long r34, long r36, long r38, long r40) {
        StatsEvent.Builder r0 = StatsEvent.newBuilder();
        r0.setAtomId(r1);
        r0.writeLong(r2);
        r0.writeInt(r4);
        r0.writeInt(r5);
        r0.writeInt(r6);
        r0.writeInt(r7);
        r0.writeInt(r8);
        r0.writeLong(r9);
        r0.writeInt(r11);
        r0.writeInt(r12);
        r0.writeBoolean(r13);
        r0.writeBoolean(r14);
        r0.writeInt(r15);
        r0.writeInt(r16);
        r0.writeInt(r17);
        r0.writeLong(r18);
        r0.writeLong(r20);
        r0.writeInt(r22);
        r0.writeInt(r23);
        r0.writeInt(r24);
        r0.writeInt(r25);
        r0.writeInt(r26);
        if (704 != r1) goto L5;
        r0.addBooleanAnnotation((byte) 1, true);
    L5:
        r0.writeInt(r27);
        r0.writeInt(r28);
        r0.writeInt(r29);
        r0.writeInt(r30);
        r0.writeInt(r31);
        r0.writeString(r32);
        r0.writeInt(r33);
        r0.writeLong(r34);
        r0.writeLong(r36);
        r0.writeLong(r38);
        r0.writeLong(r40);
        r0.usePooledBuffer();
        StatsLog.write(r0.build());
    }

    public static void write(int r1, long r2, int r4, int r5, int r6, int r7, int r8, boolean r9, boolean r10, int r11, boolean r12, boolean r13, boolean r14, int r15, String r16, int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29, int r30, int r31, int r32, int r33, int r34, int r35, int r36, int r37, long r38, int r40) {
        StatsEvent.Builder r0 = StatsEvent.newBuilder();
        r0.setAtomId(r1);
        r0.writeLong(r2);
        r0.writeInt(r4);
        r0.writeInt(r5);
        r0.writeInt(r6);
        r0.writeInt(r7);
        r0.writeInt(r8);
        r0.writeBoolean(r9);
        r0.writeBoolean(r10);
        r0.writeInt(r11);
        r0.writeBoolean(r12);
        r0.writeBoolean(r13);
        r0.writeBoolean(r14);
        r0.writeInt(r15);
        r0.writeString(r16);
        r0.writeInt(r17);
        r0.writeInt(r18);
        r0.writeInt(r19);
        r0.writeInt(r20);
        r0.writeInt(r21);
        r0.writeInt(r22);
        r0.writeInt(r23);
        r0.writeInt(r24);
        r0.writeInt(r25);
        r0.writeInt(r26);
        r0.writeInt(r27);
        r0.writeInt(r28);
        r0.writeInt(r29);
        r0.writeInt(r30);
        r0.writeInt(r31);
        r0.writeInt(r32);
        r0.writeInt(r33);
        r0.writeInt(r34);
        r0.writeInt(r35);
        r0.writeInt(r36);
        r0.writeInt(r37);
        r0.writeLong(r38);
        r0.writeInt(r40);
        if (703 != r1) goto L5;
        r0.addBooleanAnnotation((byte) 1, true);
    L5:
        r0.usePooledBuffer();
        StatsLog.write(r0.build());
    }
}
