package com.android.dex;

import com.android.dex.Dex;
import com.android.dex.util.ByteArrayByteInput;
import com.android.dex.util.ByteInput;
import kotlin.UByte;

/* loaded from: classes4.dex */
public final class EncodedValue implements Comparable<EncodedValue> {
    private final byte[] data;

    public EncodedValue(byte[] r1) {
        this.data = r1;
    }

    public ByteInput asByteInput() {
        return new ByteArrayByteInput(this.data);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(EncodedValue r5) {
        int r0 = Math.min(this.data.length, r5.data.length);
        int r1 = 0;
    L3:
        byte[] r2 = this.data;
        if (r1 >= r0) goto L11;
        byte r22 = r2[r1];
        byte r3 = r5.data[r1];
        if (r22 != r3) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        int r4 = r22 & UByte.MAX_VALUE;
        int r52 = r3 & UByte.MAX_VALUE;
    L9:
        return r4 - r52;
    L11:
        r4 = r2.length;
        r52 = r5.data.length;
        goto L9
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(EncodedValue r1) {
        return compareTo2(r1);
    }

    public String toString() {
        return Integer.toHexString(this.data[0] & UByte.MAX_VALUE) + "...(" + this.data.length + ")";
    }

    public void writeTo(Dex.Section r1) {
        r1.write(this.data);
    }
}
