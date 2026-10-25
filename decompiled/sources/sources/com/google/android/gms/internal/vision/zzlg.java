package com.google.android.gms.internal.vision;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes5.dex */
final class zzlg extends zzlh {
    public zzlg(int r2) {
        super(r2, null);
    }

    @Override // com.google.android.gms.internal.vision.zzlh
    public final void zza() {
        if (zzb() == false) goto L5;
    L13:
        super.zza();
        return;
    L5:
        if (zzc() > 0) goto L11;
        Iterator r0 = zzd().iterator();
        if (r0.hasNext() == false) goto L13;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(((Map.Entry) r0.next()).getKey());
        throw null;
    L11:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(zzb(0).getKey());
        throw null;
    }
}
