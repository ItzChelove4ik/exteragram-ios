package androidx.mediarouter.media;

import androidx.mediarouter.media.MediaRouteProvider;
import java.util.Collection;

/* loaded from: classes4.dex */
public final /* synthetic */ class MediaRouteProvider$DynamicGroupRouteController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ MediaRouteProvider.DynamicGroupRouteController f$0;
    public final /* synthetic */ MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener f$1;
    public final /* synthetic */ MediaRouteDescriptor f$2;
    public final /* synthetic */ Collection f$3;

    public /* synthetic */ MediaRouteProvider$DynamicGroupRouteController$$ExternalSyntheticLambda1(MediaRouteProvider.DynamicGroupRouteController r1, MediaRouteProvider.DynamicGroupRouteController.OnDynamicRoutesChangedListener r2, MediaRouteDescriptor r3, Collection r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MediaRouteProvider.DynamicGroupRouteController.$r8$lambda$GkvzHmFi7J0hPJ2hkNMUQ5T3hOI(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
