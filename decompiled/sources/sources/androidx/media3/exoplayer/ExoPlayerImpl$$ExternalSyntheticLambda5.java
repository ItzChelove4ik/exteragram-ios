package androidx.media3.exoplayer;

import androidx.media3.common.Player;
import androidx.media3.common.util.ListenerSet;

/* loaded from: classes4.dex */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda5 implements ListenerSet.Event {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ Player.PositionInfo f$1;
    public final /* synthetic */ Player.PositionInfo f$2;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda5(int r1, Player.PositionInfo r2, Player.PositionInfo r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public final void invoke(Object r3) {
        ExoPlayerImpl.$r8$lambda$qjFXF1Utq4esG9BDL7XKZtYQDpY(this.f$0, this.f$1, this.f$2, (Player.Listener) r3);
    }
}
