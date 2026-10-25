package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzw extends Cast.Listener {
    final /* synthetic */ CastSession zza;

    public /* synthetic */ zzw(CastSession r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onActiveInputStateChanged(int r2) {
        Iterator r1 = new HashSet(this.zza.zzh()).iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((Cast.Listener) r1.next()).onActiveInputStateChanged(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onApplicationDisconnected(int r2) {
        CastSession r1 = this.zza;
        r1.zzf(r2);
        r1.notifySessionEnded(r2);
        Iterator r12 = new HashSet(r1.zzh()).iterator();
    L4:
        if (r12.hasNext() == false) goto L6;
        ((Cast.Listener) r12.next()).onApplicationDisconnected(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onApplicationMetadataChanged(ApplicationMetadata r2) {
        Iterator r1 = new HashSet(this.zza.zzh()).iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((Cast.Listener) r1.next()).onApplicationMetadataChanged(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onApplicationStatusChanged() {
        Iterator r1 = new HashSet(this.zza.zzh()).iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((Cast.Listener) r1.next()).onApplicationStatusChanged();
        goto L4
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onStandbyStateChanged(int r2) {
        Iterator r1 = new HashSet(this.zza.zzh()).iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((Cast.Listener) r1.next()).onStandbyStateChanged(r2);
        goto L4
    }

    @Override // com.google.android.gms.cast.Cast.Listener
    public final void onVolumeChanged() {
        Iterator r1 = new HashSet(this.zza.zzh()).iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        ((Cast.Listener) r1.next()).onVolumeChanged();
        goto L4
    }
}
