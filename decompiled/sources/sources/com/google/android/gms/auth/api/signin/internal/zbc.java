package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.util.Log;
import androidx.loader.content.AsyncTaskLoader;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public final class zbc extends AsyncTaskLoader implements SignInConnectionListener {
    private final Semaphore zba;
    private final Set zbb;

    public zbc(Context r2, Set r3) {
        super(r2);
        this.zba = new Semaphore(0);
        this.zbb = r3;
    }

    @Override // androidx.loader.content.AsyncTaskLoader
    public final /* bridge */ /* synthetic */ Object loadInBackground() {
        Iterator r0 = this.zbb.iterator();
        int r1 = 0;
    L4:
        if (r0.hasNext() == true) goto L6;
        this.zba.tryAcquire(r1, 5, TimeUnit.SECONDS);     // Catch: InterruptedException -> L10
        return null;
    L10:
        e = move-exception;
        Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
        Thread.currentThread().interrupt();
        return null;
    L6:
        if (((GoogleApiClient) r0.next()).maybeSignIn(this) == false) goto L4;
        r1 = r1 + 1;
        goto L4
    }

    @Override // com.google.android.gms.common.api.internal.SignInConnectionListener
    public final void onComplete() {
        this.zba.release();
    }

    @Override // androidx.loader.content.Loader
    public final void onStartLoading() {
        this.zba.drainPermits();
        forceLoad();
    }
}
