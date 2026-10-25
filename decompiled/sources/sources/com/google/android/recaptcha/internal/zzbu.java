package com.google.android.recaptcha.internal;

import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import kotlinx.coroutines.CompletableDeferred;

/* loaded from: classes5.dex */
public final class zzbu extends WebViewClient {
    final /* synthetic */ CompletableDeferred zza;
    final /* synthetic */ WebView zzb;
    final /* synthetic */ zzby zzc;

    public zzbu(CompletableDeferred r1, WebView r2, zzby r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView r1, String r2) {
        super.onPageFinished(r1, r2);
        this.zza.complete(this.zzb);
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView r8, WebResourceRequest r9, WebResourceError r10) {
        if (r9 != null) goto L4;
        return;
    L4:
        if (r9.isForMainFrame() != true) goto L11;
        if (r10 == null) goto L7;
        int r82 = r10.getErrorCode();
    L8:
        this.zza.completeExceptionally(new zzhg(zzhf.zzc, new zzhe(r82), null, null, 12, null));
        return;
    L7:
        r82 = -1;
        goto L8
    }

    @Override // android.webkit.WebViewClient
    public final boolean onRenderProcessGone(WebView r8, RenderProcessGoneDetail r9) {
        CompletableDeferred r82 = this.zza;
        if (r82.isCompleted() == false) goto L5;
        zzby r7 = this.zzc;
        r7.zzd();
        zzby.zzg(r7).zzd();
        return true;
    L5:
        r82.completeExceptionally(new zzhg(zzhf.zzb, zzhe.zzaC, null, null, 12, null));
        return true;
    }
}
