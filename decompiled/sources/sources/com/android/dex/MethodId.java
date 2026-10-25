package com.android.dex;

import com.android.dex.Dex;
import com.android.dex.util.Unsigned;

/* loaded from: classes4.dex */
public final class MethodId implements Comparable<MethodId> {
    private final int declaringClassIndex;
    private final Dex dex;
    private final int nameIndex;
    private final int protoIndex;

    public MethodId(Dex r1, int r2, int r3, int r4) {
        this.dex = r1;
        this.declaringClassIndex = r2;
        this.protoIndex = r3;
        this.nameIndex = r4;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(MethodId r3) {
        int r0 = this.declaringClassIndex;
        int r1 = r3.declaringClassIndex;
        if (r0 != r1) goto L5;
        int r02 = this.nameIndex;
        int r12 = r3.nameIndex;
        if (r02 == r12) goto L11;
        return Unsigned.compare(r02, r12);
    L11:
        return Unsigned.compare(this.protoIndex, r3.protoIndex);
    L5:
        return Unsigned.compare(r0, r1);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(MethodId r1) {
        return compareTo2(r1);
    }

    public int getDeclaringClassIndex() {
        return this.declaringClassIndex;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public int getProtoIndex() {
        return this.protoIndex;
    }

    public String toString() {
        if (this.dex == null) goto L5;
        StringBuilder r0 = new StringBuilder();
        r0.append(this.dex.typeNames().get(this.declaringClassIndex));
        r0.append(".");
        r0.append(this.dex.strings().get(this.nameIndex));
        Dex r1 = this.dex;
        r0.append(r1.readTypeList(r1.protoIds().get(this.protoIndex).getParametersOffset()));
        return r0.toString();
    L5:
        return this.declaringClassIndex + " " + this.protoIndex + " " + this.nameIndex;
    }

    public void writeTo(Dex.Section r2) {
        r2.writeUnsignedShort(this.declaringClassIndex);
        r2.writeUnsignedShort(this.protoIndex);
        r2.writeInt(this.nameIndex);
    }
}
