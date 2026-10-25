package com.android.dex;

import com.android.dex.Dex;
import com.android.dex.util.Unsigned;

/* loaded from: classes4.dex */
public final class ProtoId implements Comparable<ProtoId> {
    private final Dex dex;
    private final int parametersOffset;
    private final int returnTypeIndex;
    private final int shortyIndex;

    public ProtoId(Dex r1, int r2, int r3, int r4) {
        this.dex = r1;
        this.shortyIndex = r2;
        this.returnTypeIndex = r3;
        this.parametersOffset = r4;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(ProtoId r3) {
        int r0 = this.returnTypeIndex;
        int r1 = r3.returnTypeIndex;
        if (r0 == r1) goto L7;
        return Unsigned.compare(r0, r1);
    L7:
        return Unsigned.compare(this.parametersOffset, r3.parametersOffset);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ProtoId r1) {
        return compareTo2(r1);
    }

    public int getParametersOffset() {
        return this.parametersOffset;
    }

    public int getReturnTypeIndex() {
        return this.returnTypeIndex;
    }

    public int getShortyIndex() {
        return this.shortyIndex;
    }

    public String toString() {
        if (this.dex != null) goto L7;
        return this.shortyIndex + " " + this.returnTypeIndex + " " + this.parametersOffset;
    L7:
        return this.dex.strings().get(this.shortyIndex) + ": " + this.dex.typeNames().get(this.returnTypeIndex) + " " + this.dex.readTypeList(this.parametersOffset);
    }

    public void writeTo(Dex.Section r2) {
        r2.writeInt(this.shortyIndex);
        r2.writeInt(this.returnTypeIndex);
        r2.writeInt(this.parametersOffset);
    }
}
