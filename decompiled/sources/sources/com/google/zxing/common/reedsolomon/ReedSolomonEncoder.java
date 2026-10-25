package com.google.zxing.common.reedsolomon;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public final class ReedSolomonEncoder {
    private final List<GenericGFPoly> cachedGenerators;
    private final GenericGF field;

    public ReedSolomonEncoder(GenericGF r3) {
        this.field = r3;
        ArrayList r0 = new ArrayList();
        this.cachedGenerators = r0;
        r0.add(new GenericGFPoly(r3, new int[]{1}));
    }

    private GenericGFPoly buildGenerator(int r8) {
        if (r8 < this.cachedGenerators.size()) goto L8;
        List<GenericGFPoly> r0 = this.cachedGenerators;
        GenericGFPoly r02 = r0.get(r0.size() - 1);
        int r1 = this.cachedGenerators.size();
    L5:
        if (r1 > r8) goto L8;
        GenericGF r4 = this.field;
        r02 = r02.multiply(new GenericGFPoly(r4, new int[]{1, r4.exp((r1 - 1) + r4.getGeneratorBase())}));
        this.cachedGenerators.add(r02);
        r1 = r1 + 1;
    L8:
        return this.cachedGenerators.get(r8);
    }

    public void encode(int[] r6, int r7) {
        if (r7 == 0) goto L12;
        int r0 = r6.length - r7;
        if (r0 <= 0) goto L10;
        GenericGFPoly r1 = buildGenerator(r7);
        int[] r2 = new int[r0];
        System.arraycopy(r6, 0, r2, 0, r0);
        int[] r5 = new GenericGFPoly(this.field, r2).multiplyByMonomial(r7, 1).divide(r1)[1].getCoefficients();
        int r72 = r7 - r5.length;
        int r12 = 0;
    L6:
        if (r12 >= r72) goto L8;
        r6[r0 + r12] = 0;
        r12 = r12 + 1;
        goto L6
    L8:
        System.arraycopy(r5, 0, r6, r0 + r72, r5.length);
        return;
    L10:
        f$$ExternalSyntheticBUOutline1.m("No data bytes provided");
        return;
    L12:
        f$$ExternalSyntheticBUOutline1.m("No error correction bytes");
    }
}
