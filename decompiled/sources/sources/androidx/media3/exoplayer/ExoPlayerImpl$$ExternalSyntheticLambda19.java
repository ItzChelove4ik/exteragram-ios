package androidx.media3.exoplayer;

import androidx.media3.common.FlagSet;
import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda19 implements ListenerSet.IterationFinishedEvent {
    public final /* synthetic */ ExoPlayerImpl f$0;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda19(ExoPlayerImpl r1) {
        this.f$0 = r1;
    }

    @Override // androidx.media3.common.util.ListenerSet.IterationFinishedEvent
    public final void invoke(Object r1, FlagSet r2) {
        ExoPlayerImpl.$r8$lambda$SA7gbpEMwf_Ft8S0LNQ1m_I4CcI(this.f$0, (Player.Listener) r1, r2);
    }
}
