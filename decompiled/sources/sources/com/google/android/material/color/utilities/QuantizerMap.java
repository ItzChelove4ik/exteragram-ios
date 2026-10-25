package com.google.android.material.color.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class QuantizerMap implements Quantizer {
    Map<Integer, Integer> colorToCount;

    public QuantizerMap() {
    }

    public Map<Integer, Integer> getColorToCount() {
        return this.colorToCount;
    }

    @Override // com.google.android.material.color.utilities.Quantizer
    public QuantizerResult quantize(int[] r6, int r7) {
        LinkedHashMap r72 = new LinkedHashMap();
        int r0 = r6.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        int r2 = r6[r1];
        Integer r3 = (Integer) r72.get(Integer.valueOf(r2));
        int r4 = 1;
        if (r3 == null) goto L8;
        r4 = 1 + r3.intValue();
    L8:
        r72.put(Integer.valueOf(r2), Integer.valueOf(r4));
        r1 = r1 + 1;
        goto L3
    L9:
        this.colorToCount = r72;
        return new QuantizerResult(r72);
    }
}
