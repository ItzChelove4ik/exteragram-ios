package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda8 implements ListenerSet.Event {
    public final /* synthetic */ PlaybackInfo f$0;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda8(PlaybackInfo r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r1) {
        ExoPlayerImpl.$r8$lambda$dM6MzW20444NC795J4AFrMPPDQc(this.f$0, (Player.Listener) r1);
    }
}
