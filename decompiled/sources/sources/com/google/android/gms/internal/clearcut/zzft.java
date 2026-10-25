package com.google.android.gms.internal.clearcut;

import java.io.IOException;

/* loaded from: classes4.dex */
public final class zzft extends IOException {
    public zzft(int r3, int r4) {
        StringBuilder r0 = new StringBuilder(108);
        r0.append("CodedOutputStream was writing to a flat byte array and ran out of space (pos ");
        r0.append(r3);
        r0.append(" limit ");
        r0.append(r4);
        r0.append(").");
        super(r0.toString());
    }
}
