package androidx.mediarouter.media;

import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.RegisteredMediaRouteProvider;

/* loaded from: classes4.dex */
public final /* synthetic */ class RegisteredMediaRouteProviderWatcher$$ExternalSyntheticLambda0 implements RegisteredMediaRouteProvider.ControllerCallback {
    public final /* synthetic */ RegisteredMediaRouteProviderWatcher f$0;
    public final /* synthetic */ RegisteredMediaRouteProvider f$1;

    public /* synthetic */ RegisteredMediaRouteProviderWatcher$$ExternalSyntheticLambda0(RegisteredMediaRouteProviderWatcher r1, RegisteredMediaRouteProvider r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.mediarouter.media.RegisteredMediaRouteProvider.ControllerCallback
    public final void onControllerReleasedByProvider(MediaRouteProvider.RouteController r2) {
        RegisteredMediaRouteProviderWatcher.m931$r8$lambda$CMmJ9YW5HRKSl5DEoLqRbUmwTI(this.f$0, this.f$1, r2);
    }
}
