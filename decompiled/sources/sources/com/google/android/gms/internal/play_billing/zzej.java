package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
final class zzej extends zzel {
    private int zzb;
    private int zzc;
    private int zzd;

    public /* synthetic */ zzej(byte[] r1, int r2, int r3, boolean r4, zzek r5) {
        super(null);
        this.zzd = Integer.MAX_VALUE;
        this.zzb = 0;
    }

    public final int zza(int r4) {
        int r42 = this.zzd;
        this.zzd = 0;
        int r1 = this.zzb + this.zzc;
        this.zzb = r1;
        if (r1 <= 0) goto L6;
        this.zzc = r1;
        this.zzb = 0;
        return r42;
    L6:
        this.zzc = 0;
        return r42;
    }
}
