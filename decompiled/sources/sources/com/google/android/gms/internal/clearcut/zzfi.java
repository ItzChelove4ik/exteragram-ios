package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
final class zzfi extends IllegalArgumentException {
    public zzfi(int r3, int r4) {
        StringBuilder r0 = new StringBuilder(54);
        r0.append("Unpaired surrogate at index ");
        r0.append(r3);
        r0.append(" of ");
        r0.append(r4);
        super(r0.toString());
    }
}
