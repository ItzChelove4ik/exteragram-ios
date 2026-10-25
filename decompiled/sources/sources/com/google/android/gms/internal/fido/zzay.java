package com.google.android.gms.internal.fido;

import com.google.android.apps.common.proguard.SideEffectFree;
import java.util.Set;

/* loaded from: classes4.dex */
public final class zzay {
    private final String zza;
    private final Set zzb;
    private final boolean zzc;

    public zzay(String r9) {
        this("com.google.android.gms.fido", zzcf.zzk(), false, false, false, false, false);
    }

    private zzay(String r1, Set r2, boolean r3, boolean r4, boolean r5, boolean r6, boolean r7) {
        this.zza = "com.google.android.gms.fido";
        this.zzb = r2;
        this.zzc = r6;
    }

    @SideEffectFree
    public final zzaq zza(String r14, long r15) {
        Long r3 = Long.valueOf(r15);
        zzav r11 = zzav.zza;
        zzaw r12 = new zzaw(Long.class);
        zzak r4 = new zzak(false, false, false, this.zzc, false, this.zzb, r11, r12);
        return new zzaq(this.zza, r14, r3, r4, true);
    }

    @SideEffectFree
    public final zzaq zzb(String r11, String r12) {
        zzat r8 = zzat.zza;
        zzau r9 = new zzau(String.class);
        zzak r1 = new zzak(false, false, false, this.zzc, false, this.zzb, r8, r9);
        return new zzaq(this.zza, r11, r12, r1, true);
    }

    @SideEffectFree
    public final zzaq zzc(String r14, boolean r15) {
        Boolean r3 = Boolean.valueOf(r15);
        zzar r11 = zzar.zza;
        zzas r12 = new zzas(Boolean.class);
        zzak r4 = new zzak(false, false, false, this.zzc, false, this.zzb, r11, r12);
        return new zzaq(this.zza, r14, r3, r4, true);
    }

    public final zzay zzd() {
        return new zzay(this.zza, this.zzb, false, false, false, true, false);
    }

    public final zzay zze(Set r9) {
        boolean r6 = this.zzc;
        return new zzay(this.zza, r9, false, false, false, r6, false);
    }
}
