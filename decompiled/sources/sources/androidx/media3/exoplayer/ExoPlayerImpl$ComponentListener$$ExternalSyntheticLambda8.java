package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.ExoPlayerImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda8 implements ListenerSet.Event {
    public final /* synthetic */ VideoSize f$0;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda8(VideoSize r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r1) {
        ExoPlayerImpl.ComponentListener.m777$r8$lambda$VYq7qrUd8IW0Tgcj2t7txIoREo(this.f$0, (Player.Listener) r1);
    }
}
