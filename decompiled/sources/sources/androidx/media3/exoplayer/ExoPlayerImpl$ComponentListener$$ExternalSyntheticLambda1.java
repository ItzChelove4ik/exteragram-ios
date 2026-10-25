package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.exoplayer.ExoPlayerImpl;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda1 implements ListenerSet.Event {
    public final /* synthetic */ CueGroup f$0;

    public /* synthetic */ ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda1(CueGroup r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r1) {
        ExoPlayerImpl.ComponentListener.m780$r8$lambda$nlI4je_u107LYLH60j4Wu21u58(this.f$0, (Player.Listener) r1);
    }
}
