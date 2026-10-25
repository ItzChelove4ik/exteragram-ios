package com.android.dx.rop.cst;

import com.android.dx.util.ToHuman;

/* loaded from: classes.dex */
public abstract class Constant implements ToHuman, Comparable<Constant> {
    public Constant() {
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public final int compareTo2(Constant r3) {
        Class<?> r0 = getClass();
        Class<?> r1 = r3.getClass();
        if (r0 == r1) goto L7;
        return r0.getName().compareTo(r1.getName());
    L7:
        return compareTo0(r3);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Constant r1) {
        return compareTo2(r1);
    }

    public abstract int compareTo0(Constant r1);

    public abstract boolean isCategory2();

    public abstract String typeName();
}
