package com.google.android.recaptcha.internal;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes5.dex */
public final class zzlq {
    private final zzln zza;

    public /* synthetic */ zzlq(Context r1, zzln r2, int r3, DefaultConstructorMarker r4) {
        zzlm r22 = null;
        zzlm r32 = zzlm.zze();     // Catch: Exception -> L10
        if (r32 != null) goto L6;
        r32 = new zzlm(r1, null);     // Catch: Exception -> L10
    L6:
        zzlm.zzf(r32);     // Catch: Exception -> L10
        r22 = r32;
    L8:
        this.zza = r22;
    }

    public final void zza(List r3) {
        monitor-enter(zzlm.class);
        zzln r2 = this.zza;     // Catch: Throwable -> L10 Exception -> L20
        if (r2 == null) goto L15;
        Iterator r32 = r3.iterator();     // Catch: Throwable -> L10 Exception -> L20
    L8:
        if (r32.hasNext() == false) goto L12;
        r2.zza((zzlo) r32.next());     // Catch: Throwable -> L10 Exception -> L20
        goto L8
    L12:
        int r33 = r2.zzb() - 500;
        if (r33 <= 0) goto L15;
        r2.zzd(CollectionsKt.take(r2.zzc(), r33));     // Catch: Throwable -> L10 Exception -> L20
    L10:
        th = move-exception;
        throw th;
    L15:
        Unit r22 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(zzlm.class);
    }

    public final void zzb(zzamk r5) {
        byte[] r52 = r5.zzw();
        int r1 = r52.length;
        zza(CollectionsKt.listOf(new zzlo(zzrv.zzi().zzg(r52, 0, r1), System.currentTimeMillis(), 0)));
    }

    public final List zzc() {
        monitor-enter(zzlm.class);
        zzln r1 = this.zza;     // Catch: Throwable -> L9 Exception -> L13
        if (r1 == null) goto L11;
        List r12 = r1.zzc();     // Catch: Throwable -> L9 Exception -> L13
        if (r12 == null) goto L11;
    L14:
        monitor-exit(zzlm.class);
        return r12;
    L11:
        r12 = CollectionsKt.emptyList();     // Catch: Throwable -> L9 Exception -> L13
    L13:
        r12 = CollectionsKt.emptyList();     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public final void zzd(List r2) {
        monitor-enter(zzlm.class);
        zzln r1 = this.zza;     // Catch: Throwable -> L8 Exception -> L10
        if (r1 == null) goto L11;
        r1.zzd(r2);     // Catch: Throwable -> L8 Exception -> L10
    L11:
        monitor-exit(zzlm.class);
        return;
    L10:
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }
}
