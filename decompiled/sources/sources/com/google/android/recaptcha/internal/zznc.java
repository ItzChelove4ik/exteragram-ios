package com.google.android.recaptcha.internal;

import java.nio.ByteBuffer;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import org.chromium.net.CronetException;
import org.chromium.net.UrlRequest;
import org.chromium.net.UrlResponseInfo;

/* loaded from: classes5.dex */
public final class zznc extends UrlRequest.Callback {
    private final CancellableContinuation zza;
    private final zzaeq zzb;

    public zznc(CancellableContinuation r1) {
        this.zza = r1;
        this.zzb = zzaer.zzA();
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public final void onFailed(UrlRequest r1, UrlResponseInfo r2, CronetException r3) {
        r2.getUrl();
        Result.Companion r12 = Result.Companion;
        Object r13 = Result.m2018constructorimpl(ResultKt.createFailure(r3));
        this.zza.resumeWith(r13);
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public final void onReadCompleted(UrlRequest r1, UrlResponseInfo r2, ByteBuffer r3) {
        r2.getUrl();
        r3.flip();
        byte[] r22 = new byte[r3.remaining()];
        r3.get(r22);
        this.zzb.write(r22);
        r3.clear();
        r1.read(r3);
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public final void onRedirectReceived(UrlRequest r1, UrlResponseInfo r2, String r3) {
        r2.getUrl();
        r1.followRedirect();
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public final void onResponseStarted(UrlRequest r1, UrlResponseInfo r2) {
        r2.getUrl();
        r1.read(ByteBuffer.allocateDirect(65536));
    }

    @Override // org.chromium.net.UrlRequest.Callback
    public final void onSucceeded(UrlRequest r2, UrlResponseInfo r3) {
        r3.getUrl();
        Object r22 = Result.m2018constructorimpl(new zznb(r3, this.zzb.zza().zzy()));
        this.zza.resumeWith(r22);
    }
}
