package com.google.android.gms.internal.clearcut;

import java.util.Arrays;

/* loaded from: classes4.dex */
final class zzbd implements zzbf {
    private zzbd() {
    }

    public /* synthetic */ zzbd(zzbc r1) {
        this();
    }

    @Override // com.google.android.gms.internal.clearcut.zzbf
    public final byte[] zzc(byte[] r1, int r2, int r3) {
        return Arrays.copyOfRange(r1, r2, r3 + r2);
    }
}
