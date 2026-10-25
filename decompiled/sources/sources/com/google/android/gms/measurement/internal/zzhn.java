package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzhn implements com.google.android.gms.internal.measurement.zzr {
    final /* synthetic */ zzht zza;

    public zzhn(zzht r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.measurement.zzr
    public final void zza(int r4, String r5, List r6, boolean r7, boolean r8) {
        int r42 = r4 - 1;
        if (r42 == 0) goto L22;
        if (r42 == 1) goto L16;
        if (r42 != 3) goto L7;
        zzgs r3 = this.zza.zzu.zzaW().zzk();
    L23:
        int r43 = r6.size();
        if (r43 != 1) goto L26;
        r3.zzb(r5, r6.get(0));
        return;
    L26:
        if (r43 == 2) goto L32;
        if (r43 == 3) goto L30;
        r3.zza(r5);
        return;
    L30:
        r3.zzd(r5, r6.get(0), r6.get(1), r6.get(2));
        return;
    L32:
        r3.zzc(r5, r6.get(0), r6.get(1));
        return;
    L7:
        if (r42 == 4) goto L9;
        r3 = this.zza.zzu.zzaW().zzi();
        goto L23
    L9:
        if (r7 == false) goto L11;
        r3 = this.zza.zzu.zzaW().zzf();
        goto L23
    L11:
        zzht r32 = this.zza;
        if (r8 == true) goto L14;
        r3 = r32.zzu.zzaW().zzh();
        goto L23
    L14:
        r3 = r32.zzu.zzaW().zze();
        goto L23
    L16:
        if (r7 == false) goto L18;
        r3 = this.zza.zzu.zzaW().zzc();
        goto L23
    L18:
        zzht r33 = this.zza;
        if (r8 == true) goto L21;
        r3 = r33.zzu.zzaW().zzd();
        goto L23
    L21:
        r3 = r33.zzu.zzaW().zzb();
        goto L23
    L22:
        r3 = this.zza.zzu.zzaW().zzj();
        goto L23
    }
}
