package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import kotlin.UByte;

/* loaded from: classes5.dex */
public abstract class HashCode {
    private static final char[] hexDigits = null;

    public static final class BytesHashCode extends HashCode implements Serializable {
        final byte[] bytes;

        public BytesHashCode(byte[] r1) {
            this.bytes = (byte[]) Preconditions.checkNotNull(r1);
        }

        @Override // com.google.common.hash.HashCode
        public byte[] asBytes() {
            return (byte[]) this.bytes.clone();
        }

        @Override // com.google.common.hash.HashCode
        public int asInt() {
            byte[] r0 = this.bytes;
            if (r0.length < 4) goto L5;
            boolean r1 = true;
        L6:
            Preconditions.checkState(r1, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", r0.length);
            byte[] r5 = this.bytes;
            return ((r5[3] & UByte.MAX_VALUE) << 24) | (((r5[0] & UByte.MAX_VALUE) | ((r5[1] & UByte.MAX_VALUE) << 8)) | ((r5[2] & UByte.MAX_VALUE) << 16));
        L5:
            r1 = false;
            goto L6
        }

        @Override // com.google.common.hash.HashCode
        public int bits() {
            return this.bytes.length * 8;
        }

        @Override // com.google.common.hash.HashCode
        public boolean equalsSameBits(HashCode r7) {
            if (this.bytes.length == r7.getBytesInternal().length) goto L5;
            return false;
        L5:
            boolean r3 = true;
            int r1 = 0;
        L6:
            byte[] r4 = this.bytes;
            if (r1 >= r4.length) goto L13;
            if (r4[r1] != r7.getBytesInternal()[r1]) goto L11;
            boolean r42 = true;
        L12:
            r3 = r3 & r42;
            r1 = r1 + 1;
            goto L6
        L11:
            r42 = false;
            goto L12
        L13:
            return r3;
        }

        @Override // com.google.common.hash.HashCode
        public byte[] getBytesInternal() {
            return this.bytes;
        }
    }

    static {
        hexDigits = "0123456789abcdef".toCharArray();
    }

    public HashCode() {
    }

    public static HashCode fromBytesNoCopy(byte[] r1) {
        return new BytesHashCode(r1);
    }

    public abstract byte[] asBytes();

    public abstract int asInt();

    public abstract int bits();

    public final boolean equals(Object r4) {
        if ((r4 instanceof HashCode) == false) goto L10;
        HashCode r42 = (HashCode) r4;
        if (bits() != r42.bits()) goto L10;
        if (equalsSameBits(r42) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public abstract boolean equalsSameBits(HashCode r1);

    public abstract byte[] getBytesInternal();

    public final int hashCode() {
        if (bits() >= 32) goto L5;
        byte[] r4 = getBytesInternal();
        int r0 = r4[0] & UByte.MAX_VALUE;
        int r1 = 1;
    L8:
        if (r1 >= r4.length) goto L10;
        r0 = r0 | ((r4[r1] & UByte.MAX_VALUE) << (r1 * 8));
        r1 = r1 + 1;
        goto L8
    L10:
        return r0;
    L5:
        return asInt();
    }

    public final String toString() {
        byte[] r6 = getBytesInternal();
        StringBuilder r0 = new StringBuilder(r6.length * 2);
        int r1 = r6.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        byte r3 = r6[r2];
        char[] r4 = hexDigits;
        r0.append(r4[(r3 >> 4) & 15]);
        r0.append(r4[r3 & 15]);
        r2 = r2 + 1;
        goto L3
    L6:
        return r0.toString();
    }
}
