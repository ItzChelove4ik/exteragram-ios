package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzz extends com.google.android.gms.cast.zzp {
    final /* synthetic */ CastSession zza;

    public /* synthetic */ zzz(CastSession r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.zzp
    public final void zza() {
        CastSession r0 = this.zza;
        if (r0.zzi() == null) goto L20;
    L8:
        e = move-exception;
        Object[] r1 = {"onConnected", zzal.class.getSimpleName()};
        CastSession.zzg().d(e, "Unable to call %s on %s.", r1);
    L13:
        CastSession r4 = this.zza;
        if (r4.zzl() == null) goto L19;
        r4.zzl().zza();
        return;
    L19:
        return;
    L6:
        if (r0.zzk() == null) goto L10;
        r0.zzk().zzb();     // Catch: RemoteException -> L8
    L10:
        r0.zzi().zze(null);     // Catch: RemoteException -> L8
        goto L13
    }

    @Override // com.google.android.gms.cast.zzp
    public final void zzb(int r3) {
        CastSession r2 = this.zza;
        if (r2.zzi() != null) goto L10;
        return;
    L10:
        r2.zzi().zzg(new ConnectionResult(r3));     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        Object[] r32 = {"onConnectionFailed", zzal.class.getSimpleName()};
        CastSession.zzg().d(e, "Unable to call %s on %s.", r32);
    }

    @Override // com.google.android.gms.cast.zzp
    public final void zzc(int r3) {
        CastSession r2 = this.zza;
        if (r2.zzi() != null) goto L10;
        return;
    L10:
        r2.zzi().zzf(r3);     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        Object[] r32 = {"onConnectionSuspended", zzal.class.getSimpleName()};
        CastSession.zzg().d(e, "Unable to call %s on %s.", r32);
    }

    @Override // com.google.android.gms.cast.zzp
    public final void zzd(int r3) {
        CastSession r2 = this.zza;
        if (r2.zzi() != null) goto L10;
        return;
    L10:
        r2.zzi().zzg(new ConnectionResult(r3));     // Catch: RemoteException -> L7
        return;
    L7:
        e = move-exception;
        Object[] r32 = {"onDisconnected", zzal.class.getSimpleName()};
        CastSession.zzg().d(e, "Unable to call %s on %s.", r32);
    }
}
