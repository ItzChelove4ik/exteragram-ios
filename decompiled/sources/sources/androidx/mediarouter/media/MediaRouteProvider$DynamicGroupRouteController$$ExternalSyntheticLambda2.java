package androidx.mediarouter.media;

import androidx.mediarouter.media.MediaRouteProvider;
import java.util.Collection;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaRouteProvider$DynamicGroupRouteController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ MediaRouteProvider.DynamicGroupRouteController f$0;
    public final /* synthetic */ MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener f$1;
    public final /* synthetic */ Collection f$2;

    public /* synthetic */ MediaRouteProvider$DynamicGroupRouteController$$ExternalSyntheticLambda2(MediaRouteProvider.DynamicGroupRouteController r1, MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener r2, Collection r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaRouteProvider.DynamicGroupRouteController.$r8$lambda$CXMYDRMDED4ym91bYunTbpwOul8(this.f$0, this.f$1, this.f$2);
    }
}
