package com.android.dx.io.instructions;

import java.util.HashMap;

/* loaded from: classes4.dex */
public final class AddressMap {
    private final HashMap<Integer, Integer> map;

    public AddressMap() {
        this.map = new HashMap();
    }

    public int get(int r1) {
        Integer r0 = this.map.get(Integer.valueOf(r1));
        if (r0 != null) goto L7;
        return -1;
    L7:
        return r0.intValue();
    }

    public void put(int r1, int r2) {
        this.map.put(Integer.valueOf(r1), Integer.valueOf(r2));
    }
}
