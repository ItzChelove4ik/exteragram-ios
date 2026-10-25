package com.android.dx.cf.iface;

import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public final class StdFieldList extends FixedSizeList implements FieldList {
    public StdFieldList(int r1) {
        super(r1);
    }

    @Override // com.android.dx.cf.iface.FieldList
    public Field get(int r1) {
        return (Field) get0(r1);
    }

    public void set(int r1, Field r2) {
        set0(r1, r2);
    }
}
