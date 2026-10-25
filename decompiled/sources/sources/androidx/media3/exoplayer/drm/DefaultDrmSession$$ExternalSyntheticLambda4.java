package androidx.media3.exoplayer.drm;

import androidx.media3.common.util.Consumer;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;

/* loaded from: classes4.dex */
public final /* synthetic */ class DefaultDrmSession$$ExternalSyntheticLambda4 implements Consumer {
    public /* synthetic */ DefaultDrmSession$$ExternalSyntheticLambda4() {
    }

    @Override // androidx.media3.common.util.Consumer
    public final void accept(Object r1) {
        ((DrmSessionEventListener.EventDispatcher) r1).drmKeysRestored();
    }
}
