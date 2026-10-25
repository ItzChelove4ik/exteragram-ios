package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Callables;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* loaded from: classes4.dex */
public final class zzto implements AsyncFunction {
    private final List zzb;
    private final Executor zzc;

    private zzto(List r1, Executor r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    public static zzto zza(List r1, Executor r2) {
        return new zzto(r1, r2);
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final /* bridge */ /* synthetic */ ListenableFuture apply(Object r5) {
        List r0 = this.zzb;
        zzth r52 = (zzth) r5;
        int r1 = r0.size();
        ArrayList r2 = new ArrayList(r1);
        Iterator r02 = r0.iterator();
        if (r02.hasNext() == false) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r02.next());
        throw null;
    L5:
        return Futures.transformAsync(r52.zza(zzxa.zzc(new zztn(this, r2, r1)), MoreExecutors.directExecutor()), zzxa.zzc(new zztk(this, r1, r2)), MoreExecutors.directExecutor());
    }

    public final /* synthetic */ ListenableFuture zzb(List r3, int r4, zzafc r5) {
        return Futures.whenAllComplete(r3).callAsync(zzxa.zzb(new zztl(this, r5, r4, r3)), this.zzc);
    }

    public final /* synthetic */ ListenableFuture zzc(int r4, List r5, Object r6) {
        ArrayList r62 = new ArrayList(r4);
        int r0 = 0;
    L4:
        if (r0 >= r4) goto L11;
        if (((Boolean) Futures.getDone((Future) r5.get(r0))).booleanValue() == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzb.get(r0));
        throw null;
    L11:
        return Futures.whenAllSucceed(r62).call(Callables.returning(null), MoreExecutors.directExecutor());
    }

    public final /* synthetic */ ListenableFuture zzd(zzafc r4, int r5, List r6) {
        ListenableFuture r42 = Futures.immediateFuture(r4);
        int r0 = 0;
    L3:
        if (r0 >= r5) goto L8;
        if (((Boolean) Futures.getDone((Future) r6.get(r0))).booleanValue() == false) goto L7;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzb.get(r0));
        r42 = Futures.transformAsync(r42, zzxa.zzc(new zztm(null)), MoreExecutors.directExecutor());
    L7:
        r0 = r0 + 1;
        goto L3
    L8:
        return r42;
    }
}
