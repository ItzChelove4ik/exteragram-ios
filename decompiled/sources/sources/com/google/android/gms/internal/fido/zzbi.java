package com.google.android.gms.internal.fido;

import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public final class zzbi {
    private final String zza;
    private final zzbg zzb;
    private zzbg zzc;

    public /* synthetic */ zzbi(String r1, zzbh r2) {
        zzbg r22 = new zzbg();
        this.zzb = r22;
        this.zzc = r22;
        r1.getClass();
        this.zza = r1;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(32);
        r0.append(this.zza);
        r0.append('{');
        zzbg r4 = this.zzb.zzc;
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

    public final zzbi zza(String r2, int r3) {
        String r22 = String.valueOf(r3);
        zzbf r32 = new zzbf(null);
        this.zzc.zzc = r32;
        this.zzc = r32;
        r32.zzb = r22;
        r32.zza = "errorCode";
        return this;
    }

    public final zzbi zzb(String r3, Object r4) {
        zzbg r0 = new zzbg();
        this.zzc.zzc = r0;
        this.zzc = r0;
        r0.zzb = r4;
        r0.zza = r3;
        return this;
    }
}
