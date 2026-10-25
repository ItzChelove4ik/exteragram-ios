package com.google.android.material.color.utilities;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public final class QuantizerCelebi {
    private QuantizerCelebi() {
    }

    public static Map<Integer, Integer> quantize(int[] r5, int r6) {
        Set<Integer> r0 = new QuantizerWu().quantize(r5, r6).colorToCount.keySet();
        int[] r1 = new int[r0.size()];
        Iterator<Integer> r02 = r0.iterator();
        int r2 = 0;
    L4:
        if (r02.hasNext() == false) goto L7;
        r1[r2] = r02.next().intValue();
        r2 = r2 + 1;
        goto L4
    L7:
        return QuantizerWsmeans.quantize(r5, r1, r6);
    }
}
