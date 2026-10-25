package com.google.android.gms.internal.mlkit_vision_label_common;

import java.util.Arrays;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes4.dex */
public final class zzd {
    private final String zza;
    private final zzc zzb;
    private zzc zzc;

    public /* synthetic */ zzd(String r2, zza r3) {
        zzc r32 = new zzc(null);
        this.zzb = r32;
        this.zzc = r32;
        r2.getClass();
        this.zza = r2;
    }

    private final zzd zzd(String r3, Object r4) {
        zzb r0 = new zzb(null);
        this.zzc.zzc = r0;
        this.zzc = r0;
        r0.zzb = r4;
        r0.zza = r3;
        return this;
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder(32);
        r0.append(this.zza);
        r0.append('{');
        zzc r4 = this.zzb.zzc;
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

    public final zzd zza(String r1, float r2) {
        zzd("confidence", String.valueOf(r2));
        return this;
    }

    public final zzd zzb(String r1, int r2) {
        zzd("index", String.valueOf(r2));
        return this;
    }

    public final zzd zzc(String r3, Object r4) {
        zzc r0 = new zzc(null);
        this.zzc.zzc = r0;
        this.zzc = r0;
        r0.zzb = r4;
        r0.zza = r3;
        return this;
    }
}
