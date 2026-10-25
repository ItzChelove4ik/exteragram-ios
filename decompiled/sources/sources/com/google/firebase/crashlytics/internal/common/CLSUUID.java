package com.google.firebase.crashlytics.internal.common;

import android.os.Process;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
class CLSUUID {
    private static final String ID_SHA = null;
    private static final AtomicLong sequenceNumber = null;
    private final String sessionId;

    static {
        ID_SHA = CommonUtils.sha1(UUID.randomUUID().toString() + System.currentTimeMillis());
        sequenceNumber = new AtomicLong(0);
    }

    public CLSUUID() {
        byte[] r0 = new byte[10];
        populateTime(r0);
        populateSequenceNumber(r0);
        populatePID(r0);
        String r02 = CommonUtils.hexify(r0);
        Locale r1 = Locale.US;
        this.sessionId = String.format(r1, "%s%s%s%s", new Object[]{r02.substring(0, 12), r02.substring(12, 16), r02.subSequence(16, 20), ID_SHA.substring(0, 12)}).toUpperCase(r1);
    }

    private static byte[] convertLongToFourByteBuffer(long r1) {
        ByteBuffer r0 = ByteBuffer.allocate(4);
        r0.putInt((int) r1);
        r0.order(ByteOrder.BIG_ENDIAN);
        r0.position(0);
        return r0.array();
    }

    private static byte[] convertLongToTwoByteBuffer(long r1) {
        ByteBuffer r0 = ByteBuffer.allocate(2);
        r0.putShort((short) r1);
        r0.order(ByteOrder.BIG_ENDIAN);
        r0.position(0);
        return r0.array();
    }

    private void populatePID(byte[] r3) {
        byte[] r2 = convertLongToTwoByteBuffer(Integer.valueOf(Process.myPid()).shortValue());
        r3[8] = r2[0];
        r3[9] = r2[1];
    }

    private void populateSequenceNumber(byte[] r3) {
        byte[] r2 = convertLongToTwoByteBuffer(sequenceNumber.incrementAndGet());
        r3[6] = r2[0];
        r3[7] = r2[1];
    }

    private void populateTime(byte[] r7) {
        long r0 = new Date().getTime();
        long r4 = r0 / 1000;
        byte[] r6 = convertLongToFourByteBuffer(r4);
        r7[0] = r6[0];
        r7[1] = r6[1];
        r7[2] = r6[2];
        r7[3] = r6[3];
        byte[] r62 = convertLongToTwoByteBuffer(r0 % 1000);
        r7[4] = r62[0];
        r7[5] = r62[1];
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String toString() {
        return this.sessionId;
    }
}
