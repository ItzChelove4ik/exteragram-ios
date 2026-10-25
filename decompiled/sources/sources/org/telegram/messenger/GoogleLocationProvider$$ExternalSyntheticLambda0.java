package org.telegram.messenger;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import org.telegram.messenger.ILocationServiceProvider;

/* loaded from: classes5.dex */
public final /* synthetic */ class GoogleLocationProvider$$ExternalSyntheticLambda0 implements GoogleApiClient.OnConnectionFailedListener {
    public final /* synthetic */ ILocationServiceProvider.IAPIOnConnectionFailedListener f$0;

    public /* synthetic */ GoogleLocationProvider$$ExternalSyntheticLambda0(ILocationServiceProvider.IAPIOnConnectionFailedListener r1) {
        this.f$0 = r1;
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult r1) {
        GoogleLocationProvider.m4111$r8$lambda$vkutDO8gzoLBU7Z7WyI5UKFw2I(this.f$0, r1);
    }
}
