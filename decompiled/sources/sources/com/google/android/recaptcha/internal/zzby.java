package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.MutableContextWrapper;
import android.webkit.WebView;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;

/* loaded from: classes5.dex */
public final class zzby implements zzbr {
    private final String zza;
    private final zzhq zzb;
    private final zzbi zzc;
    private final Context zzd;
    private final zzcb zze;
    private final AtomicBoolean zzf;
    private final zzhu zzg;

    public /* synthetic */ zzby(String r1, zzhu r2, zzhq r3, zzbi r4, Context r5, Function1 r6, int r7, DefaultConstructorMarker r8) {
        this.zza = r1;
        this.zzg = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = new zzcb();
        this.zzf = new AtomicBoolean(false);
    }

    public static final /* synthetic */ WebView zze(zzby r5, CompletableDeferred r6) {
        WebView r1 = new WebView(new MutableContextWrapper(r5.zzd.getApplicationContext()));
        r1.getSettings().setJavaScriptEnabled(true);
        r1.addJavascriptInterface(r5.zzc, "recaptchaMobileBridge");
        r1.setWebViewClient(new zzbu(r6, r1, r5));
        String r62 = Locale.getDefault().toLanguageTag();
        String r0 = r5.zzb.zzf();
        int r2 = r0.length();
        int r3 = String.valueOf(r62).length();
        String r52 = r5.zza;
        int r22 = ((r2 + 4) + r3) + 3;
        StringBuilder r32 = new StringBuilder(r22 + String.valueOf(r52).length());
        r32.append(r0);
        r32.append("?hl=");
        r32.append(r62);
        r32.append("&k=");
        r32.append(r52);
        r1.loadUrl(r32.toString());
        return r1;
    }

    public static final /* synthetic */ zzcb zzf(zzby r0) {
        return r0.zze;
    }

    public static final /* synthetic */ zzbi zzg(zzby r0) {
        return r0.zzc;
    }

    private final CompletableDeferred zzh() {
        if (this.zzf.compareAndSet(false, true) == false) goto L5;
        this.zzd.getApplicationContext().registerComponentCallbacks(new zzbt(this));
    L5:
        zzcb r0 = this.zze;
        zzcf r1 = r0.zzb();
        if ((r1 instanceof zzcd) == false) goto L10;
        return CompletableDeferredKt.CompletableDeferred(((zzcd) r1).zza());
    L10:
        if ((r1 instanceof zzcc) == false) goto L14;
        return ((zzcc) r1).zza();
    L14:
        if ((r1 instanceof zzce) == false) goto L17;
        zzce r12 = (zzce) r1;
        CompletableDeferred r2 = r12.zza();
        r0.zze(BuildersKt.launch$default(this.zzg.zza(), null, null, new zzbx(this, r2, null), 3, null));
        return r12.zza();
    L17:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzbr
    public final void zza() {
        zzh();
    }

    @Override // com.google.android.recaptcha.internal.zzbr
    public final void zzc() {
        this.zzc.zzb();
    }

    public final void zzd() {
        this.zze.zza();
    }
}
