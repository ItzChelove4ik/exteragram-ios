package com.google.android.gms.internal.mlkit_genai_prompt;

import java.io.Serializable;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zzmq extends zzlg implements Serializable {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzmq zzb = null;
    private static final zzmq zzc = null;
    private final transient zzml zzd;

    static {
        int r1 = zzml.$r8$clinit;
        zzb = new zzmq(zzng.zza, null);
        zzc = new zzmq(zzml.zzl(zzne.zza()), null);
    }

    public zzmq(zzml r1) {
        this.zzd = r1;
    }

    private zzmq(zzml r1, zzmq r2) {
        this.zzd = r1;
    }

    public static zzmq zza() {
        return zzb;
    }

    public static zzmq zzb() {
        return zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_genai_prompt.zznf
    public final /* bridge */ /* synthetic */ Set zzc() {
        zzml r2 = this.zzd;
        if (r2.isEmpty() == true) goto L5;
        int r1 = zzne.$r8$clinit;
        return new zznn(r2, zznd.zza);
    L5:
        return zznm.zza;
    }
}
