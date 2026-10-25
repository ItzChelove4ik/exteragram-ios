package com.google.android.gms.internal.measurement;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
final /* synthetic */ class zzqy implements Supplier {
    private final /* synthetic */ Supplier zza;

    public /* synthetic */ zzqy(Supplier r1) {
        this.zza = r1;
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        return ((ListeningScheduledExecutorService) Preconditions.checkNotNull((ListeningScheduledExecutorService) this.zza.get())).schedule(zzrd.zza, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, TimeUnit.MILLISECONDS);
    }
}
