package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.cast.zzxg$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.vision.zzid$$ExternalSyntheticBUOutline0;
import java.nio.charset.Charset;

/* loaded from: classes4.dex */
class zzbi extends zzbh {
    protected final byte[] zzfp;

    public zzbi(byte[] r1) {
        this.zzfp = r1;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof zzbb) == true) goto L9;
        return false;
    L9:
        if (size() == ((zzbb) r5).size()) goto L12;
        return false;
    L12:
        if (size() != 0) goto L15;
        return true;
    L15:
        if ((r5 instanceof zzbi) == false) goto L24;
        zzbi r52 = (zzbi) r5;
        int r0 = zzab();
        int r1 = r52.zzab();
        if (r0 == 0) goto L22;
        if (r1 == 0) goto L22;
        if (r0 == r1) goto L22;
        return false;
    L22:
        return zza(r52, 0, size());
    L24:
        return r5.equals(this);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public int size() {
        return this.zzfp.length;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final int zza(int r1, int r2, int r3) {
        return zzci.zza(r1, this.zzfp, zzac(), r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final zzbb zza(int r2, int r3) {
        int r22 = zzbb.zzb(0, r3, size());
        if (r22 != 0) goto L7;
        return zzbb.zzfi;
    L7:
        return new zzbe(this.zzfp, zzac(), r22);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final String zza(Charset r4) {
        return new String(this.zzfp, zzac(), size(), r4);
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final void zza(zzba r3) {
        r3.zza(this.zzfp, zzac(), size());
    }

    @Override // com.google.android.gms.internal.clearcut.zzbh
    public final boolean zza(zzbb r5, int r6, int r7) {
        if (r7 <= r5.size()) goto L5;
        zzid$$ExternalSyntheticBUOutline0.m(40, r7, size());
        return false;
    L5:
        if (r7 <= r5.size()) goto L7;
        zzxg$$ExternalSyntheticBUOutline0.m(59, "Ran off end of other: 0, ", r7, ", ", r5.size());
        return false;
    L7:
        if ((r5 instanceof zzbi) == false) goto L17;
        zzbi r52 = (zzbi) r5;
        byte[] r62 = this.zzfp;
        byte[] r1 = r52.zzfp;
        int r2 = zzac() + r7;
        int r4 = zzac();
        int r53 = r52.zzac();
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

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public final boolean zzaa() {
        int r0 = zzac();
        return zzff.zze(this.zzfp, r0, size() + r0);
    }

    public int zzac() {
        return 0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzbb
    public byte zzj(int r1) {
        return this.zzfp[r1];
    }
}
