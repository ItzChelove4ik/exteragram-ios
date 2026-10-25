package com.android.dx.cf.iface;

import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public final class StdAttributeList extends FixedSizeList implements AttributeList {
    public StdAttributeList(int r1) {
        super(r1);
    }

    @Override // com.android.dx.cf.iface.AttributeList
    public int byteLength() {
        int r0 = size();
        int r1 = 2;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        r1 = r1 + get(r2).byteLength();
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    @Override // com.android.dx.cf.iface.AttributeList
    public Attribute findFirst(String r5) {
        int r0 = size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        Attribute r2 = get(r1);
        if (r2.getName().equals(r5) == true) goto L6;
        r1 = r1 + 1;
        goto L3
    L6:
        return r2;
    L8:
        return null;
    }

    @Override // com.android.dx.cf.iface.AttributeList
    public Attribute findNext(Attribute r6) {
        int r0 = size();
        int r1 = 0;
    L4:
        if (r1 >= r0) goto L15;
        if (get(r1) == r6) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        String r62 = r6.getName();
    L8:
        r1 = r1 + 1;
        if (r1 >= r0) goto L13;
        Attribute r3 = get(r1);
        if (r3.getName().equals(r62) == false) goto L8;
        return r3;
    L13:
        return null;
    L15:
        return null;
    }

    @Override // com.android.dx.cf.iface.AttributeList
    public Attribute get(int r1) {
        return (Attribute) get0(r1);
    }

    public void set(int r1, Attribute r2) {
        set0(r1, r2);
    }
}
