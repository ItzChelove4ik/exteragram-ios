package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda11 implements ListenerSet.Event {
    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda11() {
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r1) {
        ((Player.Listener) r1).onRenderedFirstFrame();
    }
}
