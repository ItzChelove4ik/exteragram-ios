package com.google.android.gms.cast;

import android.content.Context;
import android.content.ServiceConnection;
import android.view.Display;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzah implements OnCompleteListener {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzah(CastRemoteDisplayLocalService r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r5) {
        if (r5.isSuccessful() == true) goto L6;
        Logger r0 = CastRemoteDisplayLocalService.zzi();
        r0.e("Connection was not successful", new Object[0]);
        this.zza.zzf();
        return;
    L6:
        Logger r2 = CastRemoteDisplayLocalService.zzi();
        r2.d("startRemoteDisplay successful", new Object[0]);
        Object r02 = CastRemoteDisplayLocalService.zzj();
        monitor-enter(r02);
    L13:
        th = move-exception;
        throw th;
    L9:
        if (CastRemoteDisplayLocalService.zzt() != null) goto L15;
        CastRemoteDisplayLocalService.zzi().d("Remote Display started but session already cancelled", new Object[0]);     // Catch: Throwable -> L13
        this.zza.zzf();     // Catch: Throwable -> L13
        monitor-exit(r02);     // Catch: Throwable -> L13
        return;
    L15:
        monitor-exit(r02);     // Catch: Throwable -> L13
        Display r52 = (Display) r5.getResult();
        CastRemoteDisplayLocalService r03 = this.zza;
        r03.zzd(r52);
        CastRemoteDisplayLocalService.zzk().set(false);
        Context r53 = r03.zzo();
        if (r53 == null) goto L23;
        ServiceConnection r04 = r03.zzq();
        if (r04 == null) goto L23;
        ConnectionTracker.getInstance().unbindService(r53, r04);     // Catch: IllegalArgumentException -> L22
    L22:
        Logger r05 = CastRemoteDisplayLocalService.zzi();
        r05.d("No need to unbind service, already unbound", new Object[0]);
    L23:
        CastRemoteDisplayLocalService r4 = this.zza;
        r4.zzr(null);
        r4.zzp(null);
    }
}
