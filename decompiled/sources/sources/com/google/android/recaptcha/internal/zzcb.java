package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import kotlin.Unit;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;

/* loaded from: classes5.dex */
final class zzcb {
    private final Object zza;
    private WebView zzb;
    private CompletableDeferred zzc;
    private Job zzd;

    public zzcb() {
        this.zza = new Object();
    }

    public final void zza() {
        Object r0 = this.zza;
        monitor-enter(r0);
        WebView r1 = this.zzb;     // Catch: Throwable -> L9
        if (r1 == null) goto L21;
        Context r3 = r1.getContext();     // Catch: Throwable -> L9
        if ((r3 instanceof MutableContextWrapper) == false) goto L11;
        MutableContextWrapper r32 = (MutableContextWrapper) r3;     // Catch: Throwable -> L9
    L12:
        if (r32 == null) goto L14;
        r32.setBaseContext(r1.getContext().getApplicationContext());     // Catch: Throwable -> L9
    L14:
        r1.stopLoading();     // Catch: Throwable -> L9
        ViewParent r33 = r1.getParent();     // Catch: Throwable -> L9
        if ((r33 instanceof ViewGroup) == false) goto L17;
        ViewGroup r34 = (ViewGroup) r33;     // Catch: Throwable -> L9
    L18:
        if (r34 == null) goto L20;
        r34.removeView(r1);     // Catch: Throwable -> L9
    L20:
        r1.destroy();     // Catch: Throwable -> L9
        goto L21
    L17:
        r34 = null;
        goto L18
    L11:
        r32 = null;
    L21:
        this.zzb = null;     // Catch: Throwable -> L9
        CompletableDeferred r12 = this.zzc;     // Catch: Throwable -> L9
        if (r12 == null) goto L24;
        Job.cancel$default(r12, null, 1, null);     // Catch: Throwable -> L9
    L24:
        this.zzc = null;     // Catch: Throwable -> L9
        Job r13 = this.zzd;     // Catch: Throwable -> L9
        if (r13 == null) goto L27;
        Job.cancel$default(r13, null, 1, null);     // Catch: Throwable -> L9
    L27:
        this.zzd = null;     // Catch: Throwable -> L9
        Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public final zzcf zzb() {
        Object r0 = this.zza;
        monitor-enter(r0);
        WebView r1 = this.zzb;     // Catch: Throwable -> L7
        CompletableDeferred r2 = this.zzc;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        zzcf r3 = new zzcd(r1);     // Catch: Throwable -> L7
    L12:
        monitor-exit(r0);
        return r3;
    L9:
        if (r2 == null) goto L11;
        r3 = new zzcc(r2);     // Catch: Throwable -> L7
        goto L12
    L11:
        CompletableDeferred r12 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);     // Catch: Throwable -> L7
        this.zzc = r12;     // Catch: Throwable -> L7
        r3 = new zzce(r12);     // Catch: Throwable -> L7
    L7:
        th = move-exception;
        throw th;
    }

    public final void zzc(WebView r2) {
        Object r0 = this.zza;
        monitor-enter(r0);
        this.zzb = r2;     // Catch: Throwable -> L7
        this.zzd = null;     // Catch: Throwable -> L7
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void zzd(CompletableDeferred r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc != r3) goto L9;
        this.zzc = null;     // Catch: Throwable -> L7
        this.zzd = null;     // Catch: Throwable -> L7
    L9:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    public final void zze(Job r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc == null) goto L9;
        this.zzd = r3;     // Catch: Throwable -> L7
    L10:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L9:
        Job.cancel$default(r3, null, 1, null);     // Catch: Throwable -> L7
        goto L10
    }
}
