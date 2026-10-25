package androidx.media3.exoplayer;

import androidx.media3.common.Metadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.ExoPlayerImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda3 implements ListenerSet.Event {
    public final /* synthetic */ Metadata f$0;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda3(Metadata r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r1) {
        ExoPlayerImpl.ComponentListener.m776$r8$lambda$O8Jt3I_fy8KMT7aGrtn8Tn36mo(this.f$0, (Player.Listener) r1);
    }
}
