package com.android.dx.rop.annotation;

import c.f$$ExternalSyntheticBUOutline2;
import com.android.dx.rop.cst.Constant;
import com.android.dx.rop.cst.CstString;

/* loaded from: classes4.dex */
public final class NameValuePair implements Comparable<NameValuePair> {
    private final CstString name;
    private final Constant value;

    public NameValuePair(CstString r2, Constant r3) {
        if (r2 == null) goto L9;
        if (r3 == null) goto L7;
        this.name = r2;
        this.value = r3;
        return;
    L7:
        f$$ExternalSyntheticBUOutline2.m("value == null");
        throw null;
    L9:
        f$$ExternalSyntheticBUOutline2.m("name == null");
        throw null;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(NameValuePair r3) {
        int r0 = this.name.compareTo2(r3.name);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return this.value.compareTo2(r3.value);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(NameValuePair r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof NameValuePair) == true) goto L5;
        return false;
    L5:
        NameValuePair r42 = (NameValuePair) r4;
        if (this.name.equals(r42.name) == true) goto L8;
    L11:
        return false;
    L8:
        if (this.value.equals(r42.value) == false) goto L11;
        return true;
    }

    public CstString getName() {
        return this.name;
    }

    public Constant getValue() {
        return this.value;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return this.name.toHuman() + ":" + this.value;
    }
}
