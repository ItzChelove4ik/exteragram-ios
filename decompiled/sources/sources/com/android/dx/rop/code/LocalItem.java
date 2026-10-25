package com.android.dx.rop.code;

import com.android.dx.rop.cst.CstString;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class LocalItem implements Comparable<LocalItem> {
    private final CstString name;
    private final CstString signature;

    private LocalItem(CstString r1, CstString r2) {
        this.name = r1;
        this.signature = r2;
    }

    private static int compareHandlesNulls(CstString r0, CstString r1) {
        if (r0 != r1) goto L5;
        return 0;
    L5:
        if (r0 != null) goto L8;
        return -1;
    L8:
        if (r1 != null) goto L12;
        return 1;
    L12:
        return r0.compareTo2(r1);
    }

    public static LocalItem make(CstString r1, CstString r2) {
        if (r1 != null) goto L7;
        if (r2 != null) goto L7;
        return null;
    L7:
        return new LocalItem(r1, r2);
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(LocalItem r3) {
        int r0 = compareHandlesNulls(this.name, r3.name);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return compareHandlesNulls(this.signature, r3.signature);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(LocalItem r1) {
        return compareTo2(r1);
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof LocalItem) == true) goto L6;
        return false;
    L6:
        if (compareTo2((LocalItem) r3) != 0) goto L9;
        return true;
    L9:
        return false;
    }

    public CstString getName() {
        return this.name;
    }

    public CstString getSignature() {
        return this.signature;
    }

    public int hashCode() {
        CstString r0 = this.name;
        int r1 = 0;
        if (r0 != null) goto L5;
        int r02 = 0;
    L6:
        int r03 = r02 * 31;
        CstString r2 = this.signature;
        if (r2 == null) goto L11;
        r1 = r2.hashCode();
    L11:
        return r03 + r1;
    L5:
        r02 = r0.hashCode();
        goto L6
    }

    public String toString() {
        CstString r0 = this.name;
        if (r0 != null) goto L5;
    L8:
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r0 == null) goto L11;
    L13:
        StringBuilder r02 = new StringBuilder("[");
        CstString r2 = this.name;
        if (r2 != null) goto L16;
        String r22 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        r02.append(r22);
        r02.append("|");
        CstString r3 = this.signature;
        if (r3 == null) goto L21;
        r1 = r3.toQuoted();
    L21:
        r02.append(r1);
        return r02.toString();
    L16:
        r22 = r2.toQuoted();
        goto L17
    L11:
        if (this.signature != null) goto L13;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if (this.signature != null) goto L8;
        return r0.toQuoted();
    }
}
