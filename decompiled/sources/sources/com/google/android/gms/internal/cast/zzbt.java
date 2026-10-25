package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
public final class zzbt implements MediaRouter.OnPrepareTransferListener {
    private static final Logger zza = null;
    private final zzce zzb;
    private final Handler zzc;

    static {
        zza = new Logger("MediaRouterOPTListener");
    }

    public zzbt(zzce r2) {
        this.zzb = (zzce) Preconditions.checkNotNull(r2);
        this.zzc = new zzfk(Looper.getMainLooper());
    }

    @Override // androidx.mediarouter.media.MediaRouter.OnPrepareTransferListener
    public final ListenableFuture onPrepareTransfer(MediaRouter.RouteInfo r4, MediaRouter.RouteInfo r5) {
        zza.d("Prepare transfer from Route(%s) to Route(%s)", new Object[]{r4, r5});
        return CallbackToFutureAdapter.getFuture(new zzbs(this, r4, r5));
    }

    public final /* synthetic */ Object zza(MediaRouter.RouteInfo r2, MediaRouter.RouteInfo r3, CallbackToFutureAdapter.Completer r4) {
        zzbr r0 = new zzbr(this, r2, r3, r4);
        return Boolean.valueOf(this.zzc.post(r0));
    }

    public final /* synthetic */ void zzb(MediaRouter.RouteInfo r1, MediaRouter.RouteInfo r2, CallbackToFutureAdapter.Completer r3) {
        this.zzb.zze(r1, r2, r3);
    }
}
