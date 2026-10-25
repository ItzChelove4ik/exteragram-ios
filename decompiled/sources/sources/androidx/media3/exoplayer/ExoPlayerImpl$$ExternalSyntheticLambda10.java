package androidx.media3.exoplayer;

import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda10 implements ListenerSet.Event {
    public final /* synthetic */ MediaMetadata f$0;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda10(MediaMetadata r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r1) {
        ExoPlayerImpl.$r8$lambda$6p_tLpVi3EtoSGIWeLjE6Rswo1I(this.f$0, (Player.Listener) r1);
    }
}
