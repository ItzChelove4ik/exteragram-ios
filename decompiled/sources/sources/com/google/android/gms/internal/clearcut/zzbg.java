package com.google.android.gms.internal.clearcut;

import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class zzbg {
    private final byte[] buffer;
    private final zzbn zzfo;

    private zzbg(int r1) {
        byte[] r12 = new byte[r1];
        this.buffer = r12;
        this.zzfo = zzbn.zzc(r12);
    }

    public /* synthetic */ zzbg(int r1, zzbc r2) {
        this(r1);
    }

    public final zzbb zzad() {
        if (this.zzfo.zzag() == 0) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Did not write as much data as expected.");
        return null;
    L5:
        return new zzbi(this.buffer);
    }

    public final zzbn zzae() {
        return this.zzfo;
    }
}
