package com.google.android.gms.internal.vision;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzkg implements zzkh {
    public zzkg() {
    }

    @Override // com.google.android.gms.internal.vision.zzkh
    public final int zza(int r1, Object r2, Object r3) {
        zzke r22 = (zzke) r2;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
        if (r22.isEmpty() == false) goto L5;
        return 0;
    L5:
        Iterator r0 = r22.entrySet().iterator();
        if (r0.hasNext() == true) goto L8;
        return 0;
    L8:
        Map.Entry r02 = (Map.Entry) r0.next();
        r02.getKey();
        r02.getValue();
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.vision.zzkh
    public final Object zza(Object r1, Object r2) {
        zzke r12 = (zzke) r1;
        zzke r22 = (zzke) r2;
        if (r22.isEmpty() == false) goto L5;
    L8:
        return r12;
    L5:
        if (r12.zzd() == true) goto L7;
        r12 = r12.zzb();
    L7:
        r12.zza(r22);
        goto L8
    }

    @Override // com.google.android.gms.internal.vision.zzkh
    public final Map<?, ?> zza(Object r1) {
        return (zzke) r1;
    }

    @Override // com.google.android.gms.internal.vision.zzkh
    public final zzkf<?, ?> zzb(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.vision.zzkh
    public final Map<?, ?> zzc(Object r1) {
        return (zzke) r1;
    }

    @Override // com.google.android.gms.internal.vision.zzkh
    public final boolean zzd(Object r1) {
        if (((zzke) r1).zzd() == true) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzkh
    public final Object zze(Object r1) {
        ((zzke) r1).zzc();
        return r1;
    }

    @Override // com.google.android.gms.internal.vision.zzkh
    public final Object zzf(Object r1) {
        return zzke.zza().zzb();
    }
}
