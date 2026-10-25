package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public final class zzkx {
    private final String zza;
    private final zzkw zzb;
    private zzkw zzc;

    public /* synthetic */ zzkx(String r1, byte[] r2) {
        zzkw r22 = new zzkw();
        this.zzb = r22;
        this.zzc = r22;
        r1.getClass();
        this.zza = r1;
    }

    private final zzkw zzd() {
        zzkw r0 = new zzkw();
        this.zzc.zzc = r0;
        this.zzc = r0;
        return r0;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(32);
        r0.append(this.zza);
        r0.append('{');
        zzkw r4 = this.zzb.zzc;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L3:
        if (r4 == null) goto L13;
        Object r2 = r4.zzb;
        r0.append(r1);
        String r12 = r4.zza;
        if (r12 == null) goto L7;
        r0.append(r12);
        r0.append(SignatureVisitor.INSTANCEOF);
    L7:
        if (r2 != null) goto L9;
    L11:
        r0.append(r2);
    L12:
        r4 = r4.zzc;
        r1 = ", ";
        goto L3
    L9:
        if (r2.getClass().isArray() == false) goto L11;
        r0.append(Arrays.deepToString(new Object[]{r2}), 1, r1.length() - 1);
        goto L12
    L13:
        r0.append('}');
        return r0.toString();
    }

    public final zzkx zza(String r2, Object r3) {
        zzkw r0 = zzd();
        r0.zzb = r3;
        r0.zza = r2;
        return this;
    }

    public final zzkx zzb(String r3, int r4) {
        String r42 = String.valueOf(r4);
        zzkv r0 = new zzkv(null);
        this.zzc.zzc = r0;
        this.zzc = r0;
        r0.zzb = r42;
        r0.zza = r3;
        return this;
    }

    public final zzkx zzc(Object r2) {
        zzd().zzb = r2;
        return this;
    }
}
