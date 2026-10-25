package com.android.dx.cf.iface;

import com.android.dx.util.FixedSizeList;

/* loaded from: classes4.dex */
public final class StdMethodList extends FixedSizeList implements MethodList {
    public StdMethodList(int r1) {
        super(r1);
    }

    @Override // com.android.dx.cf.iface.MethodList
    public Method get(int r1) {
        return (Method) get0(r1);
    }

    public void set(int r1, Method r2) {
        set0(r1, r2);
    }
}
