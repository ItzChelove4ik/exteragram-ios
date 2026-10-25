package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.UUID;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
final class zzwa extends zzwb {
    public zzwa() {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzwb
    public final zzwi zza(String r10, zzxd r11) {
        zzwl r4 = zzwk.zza;
        Preconditions.checkNotNull(r11);
        zzwq r6 = zzvy.zzd();
        zzws r9 = r6.zzb;
        if (r9 != zzwg.zza) goto L5;
        zzvy.zzc(r6, null);
        boolean r112 = true;
        r9 = null;
    L6:
        if (r9 != null) goto L11;
        UUID r92 = zzvz.zza().zzc();
        String r2 = zzvn.zzcL(r92);
        zzvq r5 = zzwd.zza;
        ImmutableSet r0 = zzvy.zza();
        if (r0.isEmpty() == true) goto L10;
        r0.forEach(new zzwe(r92, null));
    L10:
        zzwq r8 = r6;
        zzws r02 = new zzwf(r92, r2, r10, r4, r5, false, false, r8);
    L15:
        zzvy.zzc(r8, r02);
        return new zzwi(r02, r112);
    L11:
        r8 = r6;
        if ((r9 instanceof zzvs) == false) goto L14;
        r02 = ((zzvs) r9).zzg(r10, r4, false, r8);
        goto L15
    L14:
        r02 = r9.zzj(r10, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, 0, r4, r8);
        goto L15
    L5:
        r112 = false;
        goto L6
    }
}
