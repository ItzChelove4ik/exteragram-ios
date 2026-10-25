package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.gms.internal.cast.zzxg$$ExternalSyntheticBUOutline0;
import com.google.android.gms.internal.cast.zzxg$$ExternalSyntheticBUOutline1;
import java.util.Arrays;

/* loaded from: classes4.dex */
final class zzawa extends zzavz {
    private final byte[] zzb;

    public zzawa(byte[] r2) {
        super(null);
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawb
    public final byte zza(int r1) {
        return this.zzb[r1];
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawb
    public final int zzb() {
        return this.zzb.length;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawb
    public final zzawb zzc(int r2, int r3) {
        byte[] r1 = this.zzb;
        int r22 = zzawb.zzk(0, r3, r1.length);
        if (r22 != 0) goto L7;
        return zzawb.zza;
    L7:
        return new zzavx(r1, 0, r22);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawb
    public final void zzd(byte[] r1, int r2, int r3, int r4) {
        System.arraycopy(this.zzb, 0, r1, 0, r4);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawb
    public final boolean zze(zzawb r7) {
        boolean r0 = r7 instanceof zzawa;
        if (r0 == true) goto L5;
        boolean r1 = r7 instanceof zzavx;
        if (r1 == false) goto L25;
        byte[] r2 = this.zzb;
        int r3 = r7.zzb();
        int r4 = r2.length;
        if (r4 <= r3) goto L11;
        int r6 = String.valueOf(r4).length();
        zzxg$$ExternalSyntheticBUOutline1.m((r6 + 18) + String.valueOf(r4).length(), r4);
        return false;
    L11:
        if (r4 > r7.zzb()) goto L20;
        if (r0 == true) goto L14;
        if (r1 == false) goto L19;
        zzavx r72 = (zzavx) r7;
        return zzawb.zzl(r2, 0, r72.zzg(), r72.zzh(), r4);
    L19:
        return r7.zzc(0, r4).equals(zzc(0, r4));
    L14:
        return zzawb.zzl(r2, 0, ((zzawa) r7).zzb, 0, r4);
    L20:
        int r62 = r7.zzb();
        int r73 = String.valueOf(r4).length();
        zzxg$$ExternalSyntheticBUOutline0.m((r73 + 27) + String.valueOf(r62).length(), "Ran off end of other: 0, ", r4, ", ", r62);
        return false;
    L25:
        return r7.zze(this);
    L5:
        return Arrays.equals(this.zzb, ((zzawa) r7).zzb);
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zzawb
    public final int zzf(int r1, int r2, int r3) {
        return zzaxi.zzb(r1, this.zzb, 0, r3);
    }

    public final /* synthetic */ byte[] zzg() {
        return this.zzb;
    }
}
