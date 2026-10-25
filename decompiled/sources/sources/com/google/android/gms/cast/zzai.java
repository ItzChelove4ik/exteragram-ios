package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzai implements OnCompleteListener {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    public zzai(CastRemoteDisplayLocalService r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r3) {
        boolean r32 = r3.isSuccessful();
        CastRemoteDisplayLocalService r0 = this.zza;
        if (r32 == true) goto L7;
        r0.zzg("Unable to stop the remote display, result unsuccessful");
        CastRemoteDisplayLocalService.Callbacks r33 = (CastRemoteDisplayLocalService.Callbacks) r0.zzl().get();
        if (r33 == null) goto L8;
        r33.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_STOPPING_SERVICE_FAILED));
    L8:
        this.zza.zzn(null);
        return;
    L7:
        r0.zzg("remote display stopped");
        goto L8
    }
}
