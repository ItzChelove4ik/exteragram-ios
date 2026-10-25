package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzafr extends zzafv {
    public zzafr() {
        super(null);
    }

    @Override // com.google.android.gms.internal.measurement.zzafv
    public final void zza() {
        if (zzb() == false) goto L5;
    L13:
        super.zza();
        return;
    L5:
        if (zzc() > 0) goto L11;
        Iterator r0 = zze().iterator();
        if (r0.hasNext() == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) r0.next()).getKey());
        throw null;
    L11:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((zzafs) zzd(0)).zza());
        throw null;
    }
}
