package com.android.dex;

import com.android.dex.Dex;
import com.android.dex.util.Unsigned;

/* loaded from: classes4.dex */
public class CallSiteId implements Comparable<CallSiteId> {
    private final Dex dex;
    private final int offset;

    public CallSiteId(Dex r1, int r2) {
        this.dex = r1;
        this.offset = r2;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(CallSiteId r1) {
        return Unsigned.compare(this.offset, r1.offset);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(CallSiteId r1) {
        return compareTo2(r1);
    }

    public int getCallSiteOffset() {
        return this.offset;
    }

    public String toString() {
        Dex r0 = this.dex;
        if (r0 != null) goto L7;
        return String.valueOf(this.offset);
    L7:
        return r0.protoIds().get(this.offset).toString();
    }

    public void writeTo(Dex.Section r1) {
        r1.writeInt(this.offset);
    }
}
