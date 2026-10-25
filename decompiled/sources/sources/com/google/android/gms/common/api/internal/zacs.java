package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public final class zacs<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private zacs zab;
    private PendingResult zad;
    private final Object zae;
    private Status zaf;
    private final WeakReference zag;
    private final zacr zah;
    private boolean zai;

    public zacs(WeakReference r3) {
        this.zab = null;
        this.zad = null;
        this.zae = new Object();
        this.zaf = null;
        this.zai = false;
        Preconditions.checkNotNull(r3, "GoogleApiClient reference must not be null");
        this.zag = r3;
        GoogleApiClient r32 = (GoogleApiClient) r3.get();
        if (r32 == null) goto L5;
        Looper r33 = r32.getLooper();
    L6:
        this.zah = new zacr(this, r33);
        return;
    L5:
        r33 = Looper.getMainLooper();
        goto L6
    }

    private final void zaj() {
    }

    private final void zak(Status r2) {
        Object r0 = this.zae;
        monitor-enter(r0);
        this.zaf = r2;     // Catch: Throwable -> L7
        zal(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private final void zal(Status r2) {
        Object r22 = this.zae;
        monitor-enter(r22);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (zam() == true) goto L10;
        monitor-exit(r22);     // Catch: Throwable -> L8
        return;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Preconditions.checkNotNull(null));     // Catch: Throwable -> L8
        throw null;     // Catch: Throwable -> L8
    }

    private final boolean zam() {
        GoogleApiClient r0 = (GoogleApiClient) this.zag.get();
        return false;
    }

    private static final void zan(Result r3) {
        if ((r3 instanceof Releasable) == false) goto L11;
        ((Releasable) r3).release();     // Catch: RuntimeException -> L6
        return;
    L6:
        e = move-exception;
        Log.w("TransformedResultImpl", "Unable to release ".concat(String.valueOf(r3)), e);
        return;
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public final void onResult(Result r3) {
        Object r0 = this.zae;
        monitor-enter(r0);
    L11:
        th = move-exception;
        throw th;
    L5:
        if (r3.getStatus().isSuccess() == true) goto L7;
        zak(r3.getStatus());     // Catch: Throwable -> L11
        zan(r3);     // Catch: Throwable -> L11
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        if (zam() == false) goto L14;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Preconditions.checkNotNull(null));     // Catch: Throwable -> L11
        throw null;     // Catch: Throwable -> L11
    }

    public final <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> r3) {
        Object r32 = this.zae;
        monitor-enter(r32);
        Preconditions.checkState(true, "Cannot call then() twice.");     // Catch: Throwable -> L7
        Preconditions.checkState(true, "Cannot call then() and andFinally() on the same TransformedResult.");     // Catch: Throwable -> L7
        zacs r0 = new zacs(this.zag);     // Catch: Throwable -> L7
        this.zab = r0;     // Catch: Throwable -> L7
        zaj();     // Catch: Throwable -> L7
        monitor-exit(r32);     // Catch: Throwable -> L7
        return r0;
    L7:
        th = move-exception;
        throw th;
    }

    public final void zaa(PendingResult r2) {
        Object r0 = this.zae;
        monitor-enter(r0);
        this.zad = r2;     // Catch: Throwable -> L7
        zaj();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void zab() {
    }

    public final /* synthetic */ void zac(Status r1) {
        zak(r1);
    }

    public final /* synthetic */ zacs zae() {
        return this.zab;
    }

    public final /* synthetic */ Object zaf() {
        return this.zae;
    }
}
