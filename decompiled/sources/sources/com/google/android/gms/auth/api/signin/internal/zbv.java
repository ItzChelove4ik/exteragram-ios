package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zbv implements LoaderManager.LoaderCallbacks {
    final /* synthetic */ SignInHubActivity zba;

    public /* synthetic */ zbv(SignInHubActivity r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zba = r1;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public final Loader onCreateLoader(int r1, Bundle r2) {
        return new zbc(this.zba, GoogleApiClient.getAllClients());
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public final /* bridge */ /* synthetic */ void onLoadFinished(Loader r1, Object r2) {
        Void r22 = (Void) r2;
        SignInHubActivity r0 = this.zba;
        r0.setResult(r0.zba(), r0.zbb());
        r0.finish();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public final void onLoaderReset(Loader r1) {
    }
}
