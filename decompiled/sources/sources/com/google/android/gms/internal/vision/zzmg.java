package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzmg extends IllegalArgumentException {
    public zzmg(int r3, int r4) {
        StringBuilder r0 = new StringBuilder(54);
        r0.append("Unpaired surrogate at index ");
        r0.append(r3);
        r0.append(" of ");
        r0.append(r4);
        super(r0.toString());
    }
}
