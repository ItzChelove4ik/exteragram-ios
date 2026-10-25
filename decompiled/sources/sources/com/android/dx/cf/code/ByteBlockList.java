package com.android.dx.cf.code;

import com.android.dx.util.Hex;
import com.android.dx.util.LabeledList;
import okio.Buffer$$ExternalSyntheticBUOutline4;

/* loaded from: classes4.dex */
public final class ByteBlockList extends LabeledList {
    public ByteBlockList(int r1) {
        super(r1);
    }

    public ByteBlock get(int r1) {
        return (ByteBlock) get0(r1);
    }

    public ByteBlock labelToBlock(int r2) {
        int r0 = indexOfLabel(r2);
        if (r0 >= 0) goto L5;
        Buffer$$ExternalSyntheticBUOutline4.m("no such label: ", Hex.u2(r2));
        return null;
    L5:
        return get(r0);
    }

    public void set(int r1, ByteBlock r2) {
        super.set(r1, r2);
    }
}
