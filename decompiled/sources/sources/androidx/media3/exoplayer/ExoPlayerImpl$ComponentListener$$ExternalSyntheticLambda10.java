package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.ExoPlayerImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda10 implements ListenerSet.Event {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda10(int r1, boolean r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r2) {
        ExoPlayerImpl.ComponentListener.$r8$lambda$5bKSpLOBVovtxWFwv9J8xn5mLMc(this.f$0, this.f$1, (Player.Listener) r2);
    }
}
