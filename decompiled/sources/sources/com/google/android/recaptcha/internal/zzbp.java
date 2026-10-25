package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.ProgressBar;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
public final class zzbp implements zzbk, zzbq {
    private final zzalc zza;
    private final zzbr zzb;
    private final CoroutineScope zzc;
    private final CompletableDeferred zzd;
    private WebView zze;
    private ProgressBar zzf;
    private Function0 zzg;

    public zzbp(zzalc r1, zzbr r2, CoroutineScope r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    }

    public static final /* synthetic */ void zzi(zzbp r0, WebView r1) {
        r0.zzo(r1);
    }

    private final void zzn(Exception r8, WebView r9) {
        this.zzd.completeExceptionally(r8);
        zzbm r4 = new zzbm(this, r9, null);
        BuildersKt.launch$default(this.zzc, null, null, r4, 3, null);
    }

    private final void zzo(WebView r4) {
        this.zzb.zzc();
        zzp();
        ViewGroup r0 = null;
        if (r4 == null) goto L25;
        Context r1 = r4.getContext();     // Catch: Throwable -> L7
        if ((r1 instanceof MutableContextWrapper) == false) goto L9;
        MutableContextWrapper r12 = (MutableContextWrapper) r1;     // Catch: Throwable -> L7
    L10:
        if (r12 == null) goto L12;
        r12.setBaseContext(r4.getContext().getApplicationContext());     // Catch: Throwable -> L7
        Unit r13 = Unit.INSTANCE;     // Catch: Throwable -> L7
    L12:
        ViewParent r14 = r4.getParent();
        if ((r14 instanceof ViewGroup) == false) goto L15;
        ViewGroup r15 = (ViewGroup) r14;
    L16:
        if (r15 == null) goto L25;
        r15.removeView(r4);
        goto L25
    L15:
        r15 = null;
        goto L16
    L9:
        r12 = null;
    L7:
        th = move-exception;
        ViewParent r16 = r4.getParent();
        if ((r16 instanceof ViewGroup) == false) goto L21;
        r0 = (ViewGroup) r16;
    L21:
        if (r0 == null) goto L23;
        r0.removeView(r4);
    L23:
        throw th;
    L25:
        if (r4 != this.zze) goto L27;
        this.zze = null;
    L27:
        Function0 r42 = this.zzg;
        if (r42 == null) goto L30;
        r42.invoke();
    L30:
        this.zzg = null;
    }

    private final void zzp() {
        ProgressBar r0 = this.zzf;
        if (r0 == null) goto L12;
        ViewParent r1 = r0.getParent();
        if ((r1 instanceof ViewGroup) == false) goto L7;
        ViewGroup r12 = (ViewGroup) r1;
    L8:
        if (r12 == null) goto L10;
        r12.removeView(r0);
    L10:
        this.zzf = null;
        return;
    L7:
        r12 = null;
        goto L8
    }

    @Override // com.google.android.recaptcha.internal.zzbk
    public final Object zza(Continuation r1) {
        return this.zzd.await(r1);
    }

    @Override // com.google.android.recaptcha.internal.zzbk
    public final void zzc() {
        zzo(this.zze);
    }

    @Override // com.google.android.recaptcha.internal.zzbq
    public final void zzg() {
        zzn(new zzhg(zzhf.zzb, zzhe.zzaC, "WebView render process is gone.", null, 8, null), this.zze);
    }
}
