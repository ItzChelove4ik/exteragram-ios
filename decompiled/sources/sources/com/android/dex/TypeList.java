package com.android.dex;

import com.android.dex.util.Unsigned;

/* loaded from: classes4.dex */
public final class TypeList implements Comparable<TypeList> {
    public static final TypeList EMPTY = null;
    private final Dex dex;
    private final short[] types;

    static {
        EMPTY = new TypeList(null, Dex.EMPTY_SHORT_ARRAY);
    }

    public TypeList(Dex r1, short[] r2) {
        this.dex = r1;
        this.types = r2;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(TypeList r5) {
        int r0 = 0;
    L3:
        short[] r1 = this.types;
        if (r0 >= r1.length) goto L13;
        short[] r2 = r5.types;
        if (r0 >= r2.length) goto L13;
        short r12 = r1[r0];
        short r22 = r2[r0];
        if (r12 != r22) goto L10;
        r0 = r0 + 1;
        goto L3
    L10:
        return Unsigned.compare(r12, r22);
    L13:
        return Unsigned.compare(r1.length, r5.types.length);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(TypeList r1) {
        return compareTo2(r1);
    }

    public short[] getTypes() {
        return this.types;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("(");
        int r1 = this.types.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        Dex r3 = this.dex;
        if (r3 == null) goto L7;
        String r32 = r3.typeNames().get(this.types[r2]);
    L8:
        r0.append(r32);
        r2 = r2 + 1;
        goto L3
    L7:
        r32 = Short.valueOf(this.types[r2]);
        goto L8
    L9:
        r0.append(")");
        return r0.toString();
    }
}
