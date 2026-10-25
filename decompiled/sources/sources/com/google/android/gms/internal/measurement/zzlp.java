package com.google.android.gms.internal.measurement;

import com.google.common.base.Supplier;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.Executors;

/* loaded from: classes4.dex */
final /* synthetic */ class zzlp implements Supplier {
    static final /* synthetic */ zzlp zza = null;

    static {
        zza = new zzlp();
    }

    private /* synthetic */ zzlp() {
    }

    @Override // com.google.common.base.Supplier
    public final /* synthetic */ Object get() {
        int r0 = zzlk.$r8$clinit;
        return MoreExecutors.listeningDecorator(Executors.newSingleThreadScheduledExecutor(zzlo.zza));
    }
}
