package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.cast.zzxg$$ExternalSyntheticBUOutline0;
import java.nio.charset.Charset;

/* loaded from: classes5.dex */
class zzid extends zzia {
    protected final byte[] zzb;

    public zzid(byte[] r1) {
        r1.getClass();
        this.zzb = r1;
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzht) == true) goto L9;
        return false;
    L9:
        if (zza() == ((zzht) r5).zza()) goto L12;
        return false;
    L12:
        if (zza() != 0) goto L15;
        return true;
    L15:
        if ((r5 instanceof zzid) == false) goto L24;
        zzid r52 = (zzid) r5;
        int r0 = zzd();
        int r1 = r52.zzd();
        if (r0 == 0) goto L22;
        if (r1 == 0) goto L22;
        if (r0 == r1) goto L22;
        return false;
    L22:
        return zza(r52, 0, zza());
    L24:
        return r5.equals(this);
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public byte zza(int r1) {
        return this.zzb[r1];
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public int zza() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public final int zza(int r1, int r2, int r3) {
        return zzjf.zza(r1, this.zzb, zze(), r3);
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public final zzht zza(int r2, int r3) {
        int r22 = zzht.zzb(0, r3, zza());
        if (r22 != 0) goto L7;
        return zzht.zza;
    L7:
        return new zzhw(this.zzb, zze(), r22);
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public final String zza(Charset r4) {
        return new String(this.zzb, zze(), zza(), r4);
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public final void zza(zzhq r3) {
        r3.zza(this.zzb, zze(), zza());
    }

    @Override // com.google.android.gms.internal.vision.zzia
    public final boolean zza(zzht r5, int r6, int r7) {
        if (r7 <= r5.zza()) goto L5;
        zzid$$ExternalSyntheticBUOutline0.m(40, r7, zza());
        return false;
    L5:
        if (r7 <= r5.zza()) goto L7;
        zzxg$$ExternalSyntheticBUOutline0.m(59, "Ran off end of other: 0, ", r7, ", ", r5.zza());
        return false;
    L7:
        if ((r5 instanceof zzid) == false) goto L17;
        zzid r52 = (zzid) r5;
        byte[] r62 = this.zzb;
        byte[] r1 = r52.zzb;
        int r2 = zze() + r7;
        int r4 = zze();
        int r53 = r52.zze();
    L9:
        if (r4 >= r2) goto L14;
        if (r62[r4] != r1[r53]) goto L12;
        r4 = r4 + 1;
        r53 = r53 + 1;
        goto L9
    L12:
        return false;
    L14:
        return true;
    L17:
        return r5.zza(0, r7).equals(zza(0, r7));
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public byte zzb(int r1) {
        return this.zzb[r1];
    }

    @Override // com.google.android.gms.internal.vision.zzht
    public final boolean zzc() {
        int r0 = zze();
        return zzmd.zza(this.zzb, r0, zza() + r0);
    }

    public int zze() {
        return 0;
    }
}
