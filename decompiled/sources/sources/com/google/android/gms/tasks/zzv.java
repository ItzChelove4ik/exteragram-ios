package com.google.android.gms.tasks;

import android.app.Activity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
final class zzv extends LifecycleCallback {
    private final List zza;

    private zzv(LifecycleFragment r2) {
        super(r2);
        this.zza = new ArrayList();
        this.mLifecycleFragment.addCallback("TaskOnStopCallback", this);
    }

    public static zzv zza(Activity r2) {
        LifecycleFragment r22 = LifecycleCallback.getFragment(r2);
        monitor-enter(r22);
        zzv r0 = (zzv) r22.getCallbackOrNull("TaskOnStopCallback", zzv.class);     // Catch: Throwable -> L7
        if (r0 != null) goto L9;
        r0 = new zzv(r22);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r22);     // Catch: Throwable -> L7
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        List r2 = this.zza;
        monitor-enter(r2);
        Iterator r0 = r2.iterator();     // Catch: Throwable -> L10
    L6:
        if (r0.hasNext() == false) goto L12;
        zzq r1 = (zzq) ((WeakReference) r0.next()).get();     // Catch: Throwable -> L10
        if (r1 == null) goto L6;
        r1.zzb();     // Catch: Throwable -> L10
        goto L6
    L12:
        r2.clear();     // Catch: Throwable -> L10
        monitor-exit(r2);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public final void zzb(zzq r2) {
        List r1 = this.zza;
        monitor-enter(r1);
        r1.add(new WeakReference(r2));     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
