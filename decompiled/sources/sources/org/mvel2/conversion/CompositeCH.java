package org.mvel2.conversion;

import org.mvel2.ConversionHandler;

/* loaded from: classes5.dex */
public class CompositeCH implements ConversionHandler {
    private final ConversionHandler[] converters;

    public CompositeCH(ConversionHandler... r1) {
        this.converters = r1;
    }

    @Override // org.mvel2.ConversionHandler
    public boolean canConvertFrom(Class r5) {
        ConversionHandler[] r4 = this.converters;
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2].canConvertFrom(r5) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    @Override // org.mvel2.ConversionHandler
    public Object convertFrom(Object r5) {
        ConversionHandler[] r4 = this.converters;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        ConversionHandler r2 = r4[r1];
        if (r2.canConvertFrom(r5.getClass()) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r2.convertFrom(r5);
    L9:
        return null;
    }
}
