package com.android.dex;

import com.android.dex.Dex;
import com.android.dex.util.Unsigned;

/* loaded from: classes4.dex */
public final class FieldId implements Comparable<FieldId> {
    private final int declaringClassIndex;
    private final Dex dex;
    private final int nameIndex;
    private final int typeIndex;

    public FieldId(Dex r1, int r2, int r3, int r4) {
        this.dex = r1;
        this.declaringClassIndex = r2;
        this.typeIndex = r3;
        this.nameIndex = r4;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(FieldId r3) {
        int r0 = this.declaringClassIndex;
        int r1 = r3.declaringClassIndex;
        if (r0 != r1) goto L5;
        int r02 = this.nameIndex;
        int r12 = r3.nameIndex;
        if (r02 == r12) goto L11;
        return Unsigned.compare(r02, r12);
    L11:
        return Unsigned.compare(this.typeIndex, r3.typeIndex);
    L5:
        return Unsigned.compare(r0, r1);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(FieldId r1) {
        return compareTo2(r1);
    }

    public int getDeclaringClassIndex() {
        return this.declaringClassIndex;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public int getTypeIndex() {
        return this.typeIndex;
    }

    public String toString() {
        if (this.dex != null) goto L7;
        return this.declaringClassIndex + " " + this.typeIndex + " " + this.nameIndex;
    L7:
        return this.dex.typeNames().get(this.typeIndex) + "." + this.dex.strings().get(this.nameIndex);
    }

    public void writeTo(Dex.Section r2) {
        r2.writeUnsignedShort(this.declaringClassIndex);
        r2.writeUnsignedShort(this.typeIndex);
        r2.writeInt(this.nameIndex);
    }
}
