package com.google.common.io;

import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/* loaded from: classes5.dex */
public abstract class ByteStreams {
    private static final OutputStream NULL_OUTPUT_STREAM = null;

    /* renamed from: com.google.common.io.ByteStreams$1, reason: invalid class name */
    public class AnonymousClass1 extends OutputStream {
        public AnonymousClass1() {
        }

        public String toString() {
            return "ByteStreams.nullOutputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int r1) {
        }

        @Override // java.io.OutputStream
        public void write(byte[] r1) {
            Preconditions.checkNotNull(r1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] r1, int r2, int r3) {
            Preconditions.checkNotNull(r1);
            Preconditions.checkPositionIndexes(r2, r3 + r2, r1.length);
        }
    }

    static {
        NULL_OUTPUT_STREAM = new AnonymousClass1();
    }

    private static byte[] combineBuffers(Queue<byte[]> r6, int r7) {
        if (r6.isEmpty() == true) goto L5;
        byte[] r0 = r6.remove();
        if (r0.length != r7) goto L9;
        return r0;
    L9:
        int r2 = r7 - r0.length;
        byte[] r02 = Arrays.copyOf(r0, r7);
    L10:
        if (r2 <= 0) goto L12;
        byte[] r3 = r6.remove();
        int r4 = Math.min(r2, r3.length);
        System.arraycopy(r3, 0, r02, r7 - r2, r4);
        r2 = r2 - r4;
        goto L10
    L12:
        return r02;
    L5:
        return new byte[0];
    }

    public static byte[] toByteArray(InputStream r2) {
        Preconditions.checkNotNull(r2);
        return toByteArrayInternal(r2, new ArrayDeque(20), 0);
    }

    private static byte[] toByteArrayInternal(InputStream r7, Queue<byte[]> r8, int r9) {
        int r0 = Math.min(8192, Math.max(128, Integer.highestOneBit(r9) * 2));
    L4:
        if (r9 >= 2147483639) goto L18;
        int r3 = Math.min(r0, 2147483639 - r9);
        byte[] r4 = new byte[r3];
        r8.add(r4);
        int r5 = 0;
    L6:
        if (r5 >= r3) goto L13;
        int r6 = r7.read(r4, r5, r3 - r5);
        if (r6 == (-1)) goto L10;
        r5 = r5 + r6;
        r9 = r9 + r6;
        goto L6
    L10:
        return combineBuffers(r8, r9);
    L13:
        if (r0 >= 4096) goto L15;
        int r2 = 4;
    L16:
        r0 = IntMath.saturatedMultiply(r0, r2);
        goto L4
    L15:
        r2 = 2;
        goto L16
    L18:
        if (r7.read() != (-1)) goto L22;
        return combineBuffers(r8, 2147483639);
    L22:
        throw new OutOfMemoryError("input is too large to fit in a byte array");
    }
}
