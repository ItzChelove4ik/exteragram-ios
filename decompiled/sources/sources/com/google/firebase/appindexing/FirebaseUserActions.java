package com.google.firebase.appindexing;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.internal.zzt;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public abstract class FirebaseUserActions {
    private static WeakReference<FirebaseUserActions> zza;

    public FirebaseUserActions() {
    }

    public static synchronized FirebaseUserActions getInstance(Context r2) {
        monitor-enter(FirebaseUserActions.class);
        Preconditions.checkNotNull(r2);     // Catch: Throwable -> L12
        WeakReference<FirebaseUserActions> r1 = zza;     // Catch: Throwable -> L12
        if (r1 != null) goto L7;
        FirebaseUserActions r12 = null;
    L8:
        if (r12 != null) goto L14;
        zzt r13 = new zzt(r2.getApplicationContext());     // Catch: Throwable -> L12
        zza = new WeakReference(r13);     // Catch: Throwable -> L12
        monitor-exit(FirebaseUserActions.class);
        return r13;
    L14:
        monitor-exit(FirebaseUserActions.class);
        return r12;
    L7:
        r12 = r1.get();     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    }

    public abstract Task<Void> end(Action r1);
}
