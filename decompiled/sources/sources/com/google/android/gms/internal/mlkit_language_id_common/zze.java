package com.google.android.gms.internal.mlkit_language_id_common;

import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public final class zze {
    private final String zza;
    private final zzd zzb;
    private zzd zzc;

    public /* synthetic */ zze(String r2, zzb r3) {
        zzd r32 = new zzd(null);
        this.zzb = r32;
        this.zzc = r32;
        r2.getClass();
        this.zza = r2;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(32);
        r0.append(this.zza);
        r0.append('{');
        zzd r4 = this.zzb.zzc;
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

    public final zze zza(String r2, float r3) {
        String r22 = String.valueOf(r3);
        zzc r32 = new zzc(null);
        this.zzc.zzc = r32;
        this.zzc = r32;
        r32.zzb = r22;
        r32.zza = "confidence";
        return this;
    }

    public final zze zzb(String r2, Object r3) {
        zzd r22 = new zzd(null);
        this.zzc.zzc = r22;
        this.zzc = r22;
        r22.zzb = r3;
        r22.zza = "languageTag";
        return this;
    }
}
